package com.zoopark.lib.app;

import android.content.Context;

import com.zoopark.lib.app.IActivityLifecycle;
import com.zoopark.lib.app.IAppLifecycle;
import com.zoopark.lib.inject.module.GlobalConfigModule;

public interface IConfigModule {

    /**
     * 使用 GlobalConfigModule.Builder 给配置框架
     *
     * @param context
     * @param builder
     */
    void configZoo(Context context, GlobalConfigModule.Builder builder);

    IAppLifecycle getAppLifecycle();

    IActivityLifecycle getActivityLifecycle();
}
