package com.zoopark.lib.repository;

import android.content.Context;

public interface IRepositoryManager {

    /**
     * 根据传入的 Api Service 获得对应的 Retrofit Service
     *
     * @param service Api Service Class
     * @param <T>     Api Service Class
     * @return Retrofit Service Class
     */
    <T> T obtainRetrofitService(Class<T> service);

    /**
     * 根据传入的 Cache Provider 获得对应的 RxCache Service
     *
     * @param cache Cache Provider Class
     * @param <T>   Cache Provider Class
     * @return RxCache Service Class
     */
    <T> T obtainCacheService(Class<T> cache);

    /**
     * Clear all cache
     */
    void clearAllCache();

    Context getContext();

}
