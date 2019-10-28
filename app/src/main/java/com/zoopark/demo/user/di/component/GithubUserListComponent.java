package com.zoopark.demo.user.di.component;

import com.zoopark.lib.inject.component.AppComponent;
import com.zoopark.lib.inject.scope.ActivityScope;
import com.zoopark.demo.user.di.module.GithubUserListModule;
import com.zoopark.demo.user.ui.activity.GithubUserListActivity;

import dagger.Component;

@ActivityScope
@Component(modules = GithubUserListModule.class, dependencies = AppComponent.class)
public interface GithubUserListComponent {
    void inject(GithubUserListActivity activity);
}
