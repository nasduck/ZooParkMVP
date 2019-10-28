package com.zoopark.lib.inject.config;

import android.content.Context;

import okhttp3.OkHttpClient;

public interface OkHttpConfig {
    void configOkHttp(Context context, OkHttpClient.Builder builder);
}
