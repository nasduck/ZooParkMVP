package com.zoopark.lib.inject;

import android.app.Application;
import android.content.Context;

import com.zoopark.lib.AppLifecycles;
import com.zoopark.lib.ConfigModule;
import com.zoopark.lib.ManifestParser;
import com.zoopark.lib.inject.component.AppComponent;
import com.zoopark.lib.inject.module.GlobalConfigModule;

import java.util.List;

public class AppDelegate implements AppLifecycles {

    private Application mApplication;
    private AppComponent mAppComponent;

    @Override
    public void attachBaseContext(Context base) {

    }

    @Override
    public void onCreate(Application application) {
        this.mApplication = application;

    }

    @Override
    public void onTerminate(Application application) {
        this.mApplication = application;
        //mAppComponent = DaggerAppComponent.builder();
    }


}
