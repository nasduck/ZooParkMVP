package com.zoopark.demo.base;

import android.content.Context;

import com.zoopark.lib.ConfigModule;
import com.zoopark.lib.inject.module.GlobalConfigModule;

public class GlobalConfiguration implements ConfigModule {
    @Override
    public void applyOptions(Context context, GlobalConfigModule.Builder builder) {
        builder.baseurl("https://api.github.com");
    }
}
