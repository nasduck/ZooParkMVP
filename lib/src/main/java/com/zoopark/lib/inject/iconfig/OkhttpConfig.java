package com.zoopark.lib.inject.iconfig;

import android.content.Context;

import okhttp3.OkHttpClient;

public interface OkhttpConfig {
    void configOkhttp(Context context, OkHttpClient.Builder builder);
}
