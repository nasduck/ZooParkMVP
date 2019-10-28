package com.zoopark.demo.user.di.component;

import com.zoopark.lib.inject.component.AppComponent;
import com.zoopark.lib.inject.scope.ActivityScope;
import com.zoopark.demo.user.di.module.GithubUserInfoModule;
import com.zoopark.demo.user.ui.activity.GithubUserInfoActivity;

import dagger.Component;

@ActivityScope
@Component(modules = GithubUserInfoModule.class, dependencies = AppComponent.class)
public interface GithubUserInfoComponent {
    void inject(GithubUserInfoActivity activity);
}
