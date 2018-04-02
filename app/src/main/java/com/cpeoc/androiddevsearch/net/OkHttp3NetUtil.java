package com.cpeoc.androiddevsearch.net;

import android.util.Log;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

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

    @Override
    public void init() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        mClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();
        mRequest = new Request.Builder()
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
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e(TAG, response.body().toString());
            }
        });
    }
}
