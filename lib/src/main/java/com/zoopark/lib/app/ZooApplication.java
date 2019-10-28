package com.zoopark.lib.app;

import android.app.Application;
import android.content.Context;

import com.zoopark.lib.inject.component.AppComponent;

public class ZooApplication extends Application {

    private AppDelegate mAppDelegate;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        if (mAppDelegate == null)
            this.mAppDelegate = new AppDelegate(base);
        this.mAppDelegate.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (mAppDelegate != null)
            this.mAppDelegate.onCreate(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        if (mAppDelegate != null)
            this.mAppDelegate.onTerminate(this);
    }

    public AppComponent getAppComponent() {
        return mAppDelegate.getAppComponent();
    }

}
