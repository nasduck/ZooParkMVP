package com.zoopark.lib.base;

import android.content.Context;

import com.zoopark.lib.inject.module.GlobalConfigModule;

public interface ConfigModule {

    /**
     * 使用 GlobalConfigModule.Builder 给框架配置一些配置参数
     *
     * @param context
     * @param builder
     */
    void applyOptions(Context context, GlobalConfigModule.Builder builder);
}
