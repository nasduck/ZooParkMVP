package com.zoopark.zooparkmvp;

import android.content.Context;

import com.zoopark.lib.ConfigModule;
import com.zoopark.lib.inject.module.GlobalConfigModule;

public class GlobalConfiguration implements ConfigModule {
    @Override
    public void applyOptions(Context context, GlobalConfigModule.Builder builder) {
        builder.baseurl("http://www.baidu.com");
    }
}
