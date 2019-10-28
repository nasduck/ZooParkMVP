package com.zoopark.zooparkmvp.user.presenter;

import com.zoopark.lib.ok.BasePresenter;
import com.zoopark.zooparkmvp.user.contract.GithubUserInfoContract;
import com.zoopark.zooparkmvp.user.contract.GithubUserListContract;
import com.zoopark.zooparkmvp.user.model.GithubUserListModel;
import com.zoopark.zooparkmvp.user.model.entity.GithubUserBean;

import javax.inject.Inject;

import io.reactivex.Scheduler;
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