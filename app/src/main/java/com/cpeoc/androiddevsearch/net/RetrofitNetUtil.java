package com.cpeoc.androiddevsearch.net;

import android.util.Log;

import com.cpeoc.androiddevsearch.bean.Contributor;
import com.cpeoc.androiddevsearch.net.service.RetrofitService;
import com.google.gson.Gson;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * FIXME
 *
 * @author lincanye (silverever07@gmail.com)
 * @version AndroidDevSearch
 * @Datetime 2018-03-06 14:51
 * @Copyright (c) 2018 全国邮政电子商务运营中心. All rights reserved.
 * @since AndroidDevSearch
 */
public class RetrofitNetUtil implements NetUtil {
    public static final String TAG = "RetrofitNetUtil";
    private String baseUrl = "https://api.github.com";
    private OkHttpClient mOkHttpClient;
    private Retrofit mRetrofit;

    @Override
    public void init() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        mOkHttpClient = new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .build();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Override
    public void run() {
        runWithRxJava();
//        runWithOutRxJava();
    }

    public void runWithRxJava() {
        RetrofitService retrofitService = mRetrofit.create(RetrofitService.class);
        Observable<List<Contributor>> observable = retrofitService.getContributorsObs("square", "retrofit");
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<Contributor>>() {
                    @Override
                    public void accept(List<Contributor> list) throws Exception {
                        Log.e(TAG, "WithRxJava success");
                        for (Contributor contributor : list) {
                            Log.e(contributor.login, contributor.html_url);
                        }
                    }
                });
    }

    public void runWithOutRxJava() {
        RetrofitService retrofitService = mRetrofit.create(RetrofitService.class);
        Call<List<Contributor>> call = retrofitService.getContributors("square", "retrofit");
        call.enqueue(new Callback<List<Contributor>>() {
            @Override
            public void onResponse(Call<List<Contributor>> call, Response<List<Contributor>> response) {
                for (Contributor contributor : response.body()) {
                    Log.e(contributor.login, contributor.html_url);
                }
            }

            @Override
            public void onFailure(Call<List<Contributor>> call, Throwable t) {
                Log.e(TAG, "fail");
            }
        });
    }
}
