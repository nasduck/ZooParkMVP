package com.zoopark.lib;

import android.app.Application;
import android.content.Context;

public interface AppLifecycles {

    void attachBaseContext(Context base);

    void onCreate(Application application);

    void onTerminate(Application application);

}
