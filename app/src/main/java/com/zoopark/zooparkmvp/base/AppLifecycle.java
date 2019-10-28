package com.zoopark.zooparkmvp.base;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.zoopark.lib.application.IAppLifecycle;

public class AppLifecycle implements IAppLifecycle {

    @Override
    public void attachBaseContext(Context context) {
        Log.d("cao", "app attachBaseContext");
    }

    @Override
    public void onCreate(Application app) {
        Log.d("cao", "app onCreate");
    }

    @Override
    public void onTerminate(Application app) {

    }
}
