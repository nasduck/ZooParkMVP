package com.zoopark.zooparkmvp.user.di.module;

import com.zoopark.lib.inject.scope.ActivityScope;
import com.zoopark.zooparkmvp.user.contract.GithubUserInfoContract;
import com.zoopark.zooparkmvp.user.model.GithubUserInfoModel;

import dagger.Module;
import dagger.Provides;

@Module
public class GithubUserInfoModule {

    private GithubUserInfoContract.View view;

    public GithubUserInfoModule(GithubUserInfoContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    GithubUserInfoContract.View providerGithubUserInfo() {
        return this.view;
    }

    @ActivityScope
    @Provides
    GithubUserInfoContract.Model provideGithubUserInfo(GithubUserInfoModel model) {
        return model;
    }
}
