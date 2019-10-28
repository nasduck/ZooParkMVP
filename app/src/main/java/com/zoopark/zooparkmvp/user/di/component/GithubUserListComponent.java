package com.zoopark.zooparkmvp.user.di.component;

import com.zoopark.lib.inject.component.AppComponent;
import com.zoopark.lib.inject.scope.ActivityScope;
import com.zoopark.zooparkmvp.user.di.module.GithubUserListModule;
import com.zoopark.zooparkmvp.user.ui.activity.GithubUserListActivity;

import dagger.Component;

@ActivityScope
@Component(modules = GithubUserListModule.class, dependencies = AppComponent.class)
public interface GithubUserListComponent {
    void inject(GithubUserListActivity activity);
}
