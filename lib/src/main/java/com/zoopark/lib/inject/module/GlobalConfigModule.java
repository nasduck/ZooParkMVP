package com.zoopark.lib.inject.module;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.zoopark.lib.inject.iconfig.BaseUrl;
import com.zoopark.lib.inject.iconfig.GlobalHttpHandler;
import com.zoopark.lib.inject.iconfig.OkhttpConfig;
import com.zoopark.lib.inject.iconfig.RetrofitConfig;
import com.zoopark.lib.inject.iconfig.RxCacheConfig;
import com.zoopark.lib.utils.Preconditions;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;

@Module
public class GlobalConfigModule {

    private HttpUrl mApiUrl;
    private BaseUrl mBaseUrl;
    private GlobalHttpHandler mHandler;
    private RetrofitConfig mRetrofitConfig;
    private OkhttpConfig mOkHttpConfig;
    private RxCacheConfig mRxCacheConfig;

    private GlobalConfigModule(Builder builder) {
        this.mApiUrl = builder.apiUrl;
        this.mBaseUrl = builder.baseUrl;
        this.mHandler = builder.handler;
        this.mRetrofitConfig = builder.retrofitConfig;
        this.mOkHttpConfig = builder.okhttpConfig;
    }

    public static Builder builder() {
        return new Builder();
    }

    @Singleton
    @Provides
    HttpUrl provideBaseUrl() {
        if (mBaseUrl != null) {
            HttpUrl httpUrl = mBaseUrl.url();
            if (httpUrl != null) {
                return httpUrl;
            }
        }
        return mApiUrl == null ? HttpUrl.parse("https://api.github.com/") : mApiUrl;
    }

    @Singleton
    @Provides
    @Nullable
    GlobalHttpHandler provideGlobalHttpHandler() {
        return mHandler;
    }

    @Singleton
    @Provides
    @Nullable
    RetrofitConfig provideRetrofitConfig() {
        return mRetrofitConfig;
    }

    @Singleton
    @Provides
    @Nullable
    OkhttpConfig provideOkhttpConfig() {
        return mOkHttpConfig;
    }

    @Singleton
    @Provides
    @Nullable
    RxCacheConfig provideRxCacheConfig() {
        return mRxCacheConfig;
    }

    public static class Builder {

        private HttpUrl apiUrl;
        private BaseUrl baseUrl;
        private GlobalHttpHandler handler;
        private RetrofitConfig retrofitConfig;
        private OkhttpConfig okhttpConfig;
        private RxCacheConfig rxCacheConfig;

        private Builder() {}

        public GlobalConfigModule build() {
            return new GlobalConfigModule(this);
        }

        public Builder baseurl(String baseUrl) {//基础url
            if (TextUtils.isEmpty(baseUrl)) {
                throw new NullPointerException("BaseUrl can not be empty");
            }
            this.apiUrl = HttpUrl.parse(baseUrl);
            return this;
        }

        public Builder baseurl(BaseUrl baseUrl) {
            this.baseUrl = Preconditions.checkNotNull(baseUrl, BaseUrl.class.getCanonicalName() + "can not be null.");
            return this;
        }

        public Builder globalHttpHandler(GlobalHttpHandler handler) {//用来处理http响应结果
            this.handler = handler;
            return this;
        }

        public Builder retrofitConfig(RetrofitConfig retrofitConfig) {
            this.retrofitConfig = retrofitConfig;
            return this;
        }

        public Builder okhttpConfig(OkhttpConfig okhttpConfig) {
            this.okhttpConfig = okhttpConfig;
            return this;
        }

        public Builder cacheConfig(RxCacheConfig rxCacheConfig) {
            this.rxCacheConfig = rxCacheConfig;
            return this;
        }

    }
}
