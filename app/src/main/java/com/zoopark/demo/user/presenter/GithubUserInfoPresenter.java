package com.zoopark.demo.user.presenter;

import com.zoopark.demo.user.contract.GithubUserInfoContract;
import com.zoopark.demo.user.model.entity.GithubUserBean;
import com.zoopark.lib.mvp.impl.BasePresenter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class GithubUserInfoPresenter extends BasePresenter<GithubUserInfoContract.Model, GithubUserInfoContract.View> {

    @Inject
    public GithubUserInfoPresenter(GithubUserInfoContract.Model model, GithubUserInfoContract.View rootView) {
        super(model, rootView);
    }

    public void callGetGithubUserInfo(String userName) {
        DisposableObserver<GithubUserBean> observer = mModel.getUserInfo(userName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<GithubUserBean>() {
                    @Override
                    public void onNext(GithubUserBean githubUserBean) {
                        mRootView.configUI(githubUserBean);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        addDispose(observer);
    }
}
