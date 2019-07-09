package com.zoopark.lib.inject.iconfig;

import android.content.Context;

import retrofit2.Retrofit;

public interface RetrofitConfiguration {
    void configRetrofit(Context context, Retrofit.Builder builder);
}
