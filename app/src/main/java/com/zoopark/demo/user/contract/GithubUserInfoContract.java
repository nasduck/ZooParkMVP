package com.zoopark.demo.user.contract;

import com.zoopark.lib.ok.IModel;
import com.zoopark.lib.ok.IView;
import com.zoopark.demo.user.model.entity.GithubUserBean;

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
