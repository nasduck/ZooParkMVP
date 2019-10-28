package com.zoopark.demo.user.contract;

import com.zoopark.demo.user.model.entity.GithubUserBean;
import com.zoopark.lib.mvp.IModel;
import com.zoopark.lib.mvp.IView;

import io.reactivex.Observable;
import retrofit2.http.Path;

public interface GithubUserInfoContract {

    interface View extends IView {
        void configUI(GithubUserBean userInfo);
    }

    interface Model extends IModel {
        Observable<GithubUserBean> getUserInfo(@Path("username") String username);
    }
}
