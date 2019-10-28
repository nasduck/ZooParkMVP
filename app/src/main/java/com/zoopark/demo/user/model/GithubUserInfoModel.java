package com.zoopark.demo.user.model;

import com.zoopark.lib.BaseModel;
import com.zoopark.lib.inject.scope.ActivityScope;
import com.zoopark.lib.repository.IRepositoryManager;
import com.zoopark.demo.service.GithubUserService;
import com.zoopark.demo.user.contract.GithubUserInfoContract;
import com.zoopark.demo.user.model.entity.GithubUserBean;

import javax.inject.Inject;

import io.reactivex.Observable;

@ActivityScope
public class GithubUserInfoModel extends BaseModel implements GithubUserInfoContract.Model {

    @Inject
    public GithubUserInfoModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    @Override
    public Observable<GithubUserBean> getUserInfo(String username) {
        return mRepositoryManager.obtainRetrofitService(GithubUserService.class)
                .getUserInfo(username);
    }
}
