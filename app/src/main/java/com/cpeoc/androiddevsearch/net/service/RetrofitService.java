package com.cpeoc.androiddevsearch.net.service;

import com.cpeoc.androiddevsearch.bean.Contributor;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * FIXME
 *
 * @author lincanye (silverever07@gmail.com)
 * @version AndroidDevSearch
 * @Datetime 2018-03-06 14:59
 * @Copyright (c) 2018 全国邮政电子商务运营中心. All rights reserved.
 * @since AndroidDevSearch
 */
public interface RetrofitService {
    @GET("/repos/{owner}/{repo}/contributors")
    Call<List<Contributor>> getContributors(
            @Path("owner") String owner,
            @Path("repo") String repo);

    @GET("/repos/{owner}/{repo}/contributors")
    Observable<List<Contributor>> getContributorsObs(
            @Path("owner") String owner,
            @Path("repo") String repo);
}
