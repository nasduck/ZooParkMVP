package com.zoopark.lib.app;

import android.app.Application;
import android.content.Context;

public interface IAppLifecycle {

    void attachBaseContext(Context context);

    void onCreate(Application app);

    void onTerminate(Application app);

}
