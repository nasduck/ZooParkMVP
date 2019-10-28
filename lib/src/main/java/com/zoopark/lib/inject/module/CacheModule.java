package com.zoopark.lib.inject.module;

import android.app.Application;
import android.support.annotation.Nullable;

import com.zoopark.lib.inject.iconfig.RxCacheConfig;
import com.zoopark.lib.utils.FileUtil;

import java.io.File;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.rx_cache2.internal.RxCache;
import io.victoralbertos.jolyglot.GsonSpeaker;

@Module
public abstract class CacheModule {

    /**
     * 提供 RxCache
     *
     * @param app      Application
     * @param config   用户自定义的 RxCache 配置
     * @param cacheDir RxCache 缓存路径
     * @return RxCache
     */
    @Singleton
    @Provides
    static RxCache provideRxCache(Application app,
                                  @Nullable RxCacheConfig config,
                                  @Named("RxCacheDir") File cacheDir) {
        RxCache.Builder builder = new RxCache.Builder();
        if (config != null) {
            config.configRxCache(app, builder);
        }
        return builder.persistence(cacheDir, new GsonSpeaker());
    }

    /**
     * 提供单独的子缓存文件给 RxCache
     *
     * @param cacheDir 框架缓存文件
     * @return RxCache 子缓存文件
     */
    @Singleton
    @Provides
    @Named("RxCacheDir")
    static File provideRxCacheDir(File cacheDir) {
        File file = new File(cacheDir, "RxCache");
        return FileUtil.makeDirs(file);
    }

    /**
     * 提供缓存文件
     *
     * @param app Application
     * @return
     */
    @Singleton
    @Provides
    static File provideCacheFile(Application app) {
        return FileUtil.getCacheFile(app);
    }

}
