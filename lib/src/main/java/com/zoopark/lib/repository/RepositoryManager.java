package com.zoopark.lib.repository;

import android.app.Application;
import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Lazy;
import retrofit2.Retrofit;

@Singleton
public class RepositoryManager implements IRepositoryManager {

    @Inject
    Lazy<Retrofit> mRetrofit;

    @Inject
    Application mApplication;

    @Inject
    public RepositoryManager() {}

    @Override
    public <T> T obtainRetrofitService(Class<T> service) {
        T retrofitService = mRetrofit.get().create(service);
        return retrofitService;
    }

    @Override
    public <T> T obtainCacheService(Class<T> cache) {
        return null;
    }

    @Override
    public void clearAllCache() {

    }

    @Override
    public Context getContext() {
        return mApplication;
    }
}
