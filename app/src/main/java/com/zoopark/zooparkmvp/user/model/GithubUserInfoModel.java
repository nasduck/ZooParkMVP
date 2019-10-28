package com.zoopark.zooparkmvp.user.model;

import com.zoopark.lib.BaseModel;
import com.zoopark.lib.inject.scope.ActivityScope;
import com.zoopark.lib.repository.IRepositoryManager;
import com.zoopark.zooparkmvp.service.GithubUserService;
import com.zoopark.zooparkmvp.user.contract.GithubUserInfoContract;
import com.zoopark.zooparkmvp.user.model.entity.GithubUserBean;

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
