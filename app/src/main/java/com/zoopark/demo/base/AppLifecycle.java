package com.zoopark.demo.base;

import android.app.Application;
import android.content.Context;

import com.zoopark.lib.app.IAppLifecycle;

/**
 * 用户在 Application 生命周期中的额外配置
 */
public class AppLifecycle implements IAppLifecycle {

    @Override
    public void attachBaseContext(Context context) {
        // Do anything you want
    }

    @Override
    public void onCreate(Application app) {
        // Do anything you want
    }

    @Override
    public void onTerminate(Application app) {
        // Do anything you want
    }
}
