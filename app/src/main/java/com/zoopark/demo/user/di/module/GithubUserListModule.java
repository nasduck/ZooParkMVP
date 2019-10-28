package com.zoopark.demo.user.di.module;

import com.zoopark.lib.inject.scope.ActivityScope;
import com.zoopark.demo.user.contract.GithubUserListContract;
import com.zoopark.demo.user.model.GithubUserListModel;
import com.zoopark.demo.user.ui.adapter.GithubUserAdapter;

import dagger.Module;
import dagger.Provides;

@Module
public class GithubUserListModule {

    private GithubUserListContract.View view;

    public GithubUserListModule(GithubUserListContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    GithubUserListContract.View provideGithubUserListView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    GithubUserListContract.Model provideGithubUserListModel(GithubUserListModel model) {
        return model;
    }

    @ActivityScope
    @Provides
    GithubUserAdapter providerGithubUserListAdapter() {
        return new GithubUserAdapter(view.getSelf());
    }
}
