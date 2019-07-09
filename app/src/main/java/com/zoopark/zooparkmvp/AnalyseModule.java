package com.zoopark.zooparkmvp;

import com.zoopark.lib.inject.scope.ActivityScope;

import dagger.Module;
import dagger.Provides;

@Module
public class AnalyseModule {
    private AnalyseContract.View view;

    /**
     * 构建AnalyseModule时,将View的实现类传进来,这样就可以提供View的实现类给presenter
     *
     * @param view
     */
    public AnalyseModule(AnalyseContract.View view) {
        this.view = view;
    }

    @ActivityScope
    @Provides
    AnalyseContract.View provideAnalyseView() {
        return this.view;
    }

    @ActivityScope
    @Provides
    AnalyseContract.Model provideAnalyseModel(AnalyseModel model) {
        return model;
    }


}