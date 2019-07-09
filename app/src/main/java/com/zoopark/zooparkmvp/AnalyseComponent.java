package com.zoopark.zooparkmvp;

import com.zoopark.lib.inject.component.AppComponent;
import com.zoopark.lib.inject.scope.ActivityScope;

import dagger.Component;

@ActivityScope
@Component(modules = AnalyseModule.class, dependencies = AppComponent.class)
public interface AnalyseComponent {
    void inject(AnalyseActivity activity);
}