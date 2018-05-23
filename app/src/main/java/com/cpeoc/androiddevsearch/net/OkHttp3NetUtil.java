package com.cpeoc.androiddevsearch.net;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import okio.BufferedSink;
import okio.Okio;
import static okhttp3.internal.Util.closeQuietly;

/**
 * FIXME
 *
 * @author lincanye (silverever07@gmail.com)
 * @version AndroidDevSearch
 * @Datetime 2018-03-06 14:36
 * @Copyright (c) 2018 全国邮政电子商务运营中心. All rights reserved.
 * @since AndroidDevSearch
 */
public class OkHttp3NetUtil implements NetUtil {
    public static final String TAG = "OkHttp3NetUtil";
    private String url = "https://www.baidu.com";
    private OkHttpClient mClient;
    private Request mRequest;
    private View mView;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            ((TextView)mView).setText((String)msg.obj);
        }
    };
    public OkHttp3NetUtil(View view) {
        super();
        mView = view;
    }

    @Override
    public void init() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        mClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .cache(new Cache(new File("cache/okhttp"), 2*1024l))
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
        Headers headers = new Headers.Builder()
                .add("", "")
                .build();
        FormBody formBody = new FormBody.Builder()
                .add("", "")
                .build();
        mRequest = new Request.Builder()
                .headers(headers)
                .put(formBody)
                .method("GET", null)
                .url(url)
                .build();
    }

    @Override
    public void run() {
        mClient.newCall(mRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "fail");
                ((TextView)mView).setText("fail");
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
//                Log.e(TAG, response.body().toString());
//                Message msg = Message.obtain();
//                msg.obj = response.body().toString();
//                mHandler.sendMessage(msg);
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        ((TextView)mView).setText(response.body().toString());
                    }
                });
            }
        });
    }
}
