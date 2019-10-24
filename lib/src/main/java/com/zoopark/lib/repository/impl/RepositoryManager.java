package com.zoopark.lib.repository.impl;

import android.app.Application;
import android.content.Context;

import com.zoopark.lib.repository.IRepositoryManager;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Lazy;
import io.rx_cache2.internal.RxCache;
import retrofit2.Retrofit;

/**
 * 管理:
 * 1. 网络请求层
 * 2. 数据缓存层
 */
@Singleton
public class RepositoryManager implements IRepositoryManager {

    @Inject
    Lazy<Retrofit> mRetrofit;

    @Inject
    Lazy<RxCache> mRxCache;

    @Inject
    Application mApplication;

    @Inject
    public RepositoryManager() {}

    /**
     * 根据传入的 Api Service 获得对应的 Retrofit Service
     *
     * @param service Api Service Class
     * @param <T>     Api Service Class
     * @return Retrofit Service Class
     */
    @Override
    public <T> T obtainRetrofitService(Class<T> service) {
        T retrofitService = mRetrofit.get().create(service);
        return retrofitService;
    }

    /**
     * 根据传入的 Cache Provider 获得对应的 RxCache Service
     *
     * @param cache Cache Provider Class
     * @param <T>   Cache Provider Class
     * @return RxCache Service Class
     */
    @Override
    public <T> T obtainCacheService(Class<T> cache) {
        T classProviders = mRxCache.get().using(cache);
        return classProviders;
    }

    /**
     * Clear all cache
     */
    @Override
    public void clearAllCache() {
        mRxCache.get().evictAll().subscribe();
    }

    @Override
    public Context getContext() {
        return mApplication;
    }
}
