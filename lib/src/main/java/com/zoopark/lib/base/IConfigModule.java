package com.zoopark.lib.base;

import android.content.Context;

import com.zoopark.lib.application.IAppLifecycle;
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
}
