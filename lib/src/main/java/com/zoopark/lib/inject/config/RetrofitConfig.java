package com.zoopark.lib.inject.config;

import android.content.Context;

import retrofit2.Retrofit;

public interface RetrofitConfig {
    void configRetrofit(Context context, Retrofit.Builder builder);
}
