package com.zoopark.lib.base;

import android.app.Application;
import android.content.Context;

public interface AppLifecycles {

    void attachBaseContext(Context base);

    void onCreate(Application application);

    void onTerminate(Application application);

}
