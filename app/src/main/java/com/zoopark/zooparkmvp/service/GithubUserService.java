package com.zoopark.zooparkmvp.service;

import com.zoopark.zooparkmvp.user.model.entity.GithubUserBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubUserService {

    @GET("/users")
    Observable<List<GithubUserBean>> getUserList();

    @GET("/users/{username}")
    Observable<GithubUserBean> getUserInfo(@Path("username") String username);

}
