package com.zoopark.lib.base;

import android.app.Application;
import android.content.Context;

import com.zoopark.lib.inject.component.AppComponent;

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
