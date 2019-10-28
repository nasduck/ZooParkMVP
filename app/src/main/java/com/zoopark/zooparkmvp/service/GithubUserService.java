package com.zoopark.zooparkmvp.service;

import com.zoopark.zooparkmvp.user.model.entity.GithubUserBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface GithubUserService {

    @GET("/users")
    Observable<List<GithubUserBean>> getUserList();



}
