package com.zoopark.lib.inject.iconfig;

import android.content.Context;

import io.rx_cache2.internal.RxCache;

public interface RxCacheConfig {
    void configRxCache(Context context, RxCache.Builder builder);
}
