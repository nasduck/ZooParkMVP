package com.zoopark.zooparkmvp.user.di.component;

import com.zoopark.lib.inject.component.AppComponent;
import com.zoopark.lib.inject.scope.ActivityScope;
import com.zoopark.zooparkmvp.user.di.module.GithubUserInfoModule;
import com.zoopark.zooparkmvp.user.ui.activity.GithubUserInfoActivity;

import dagger.Component;

@ActivityScope
@Component(modules = GithubUserInfoModule.class, dependencies = AppComponent.class)
public interface GithubUserInfoComponent {
    void inject(GithubUserInfoActivity activity);
}
