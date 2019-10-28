package com.zoopark.lib.inject.module;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.zoopark.lib.inject.config.BaseUrl;
import com.zoopark.lib.inject.config.GlobalHttpHandler;
import com.zoopark.lib.inject.config.OkHttpConfig;
import com.zoopark.lib.inject.config.RetrofitConfig;
import com.zoopark.lib.inject.config.RxCacheConfig;
import com.zoopark.lib.utils.Preconditions;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;

@Module
public class GlobalConfigModule {

    private HttpUrl mApiUrl;
    private BaseUrl mBaseUrl;
    private GlobalHttpHandler mHttpHandler;
    private RetrofitConfig mRetrofitConfig;
    private OkHttpConfig mOkHttpConfig;
    private RxCacheConfig mRxCacheConfig;

    private GlobalConfigModule(Builder builder) {
        this.mApiUrl = builder.apiUrl;
        this.mBaseUrl = builder.baseUrl;
        this.mHttpHandler = builder.httpHandler;
        this.mRetrofitConfig = builder.retrofitConfig;
        this.mOkHttpConfig = builder.okhttpConfig;
        this.mRxCacheConfig = builder.rxCacheConfig;
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

    /**
     * 提供全局 Http 请求和响应拦截
     */
    @Singleton
    @Provides
    @Nullable
    GlobalHttpHandler provideGlobalHttpInterceptor() {
        return mHttpHandler;
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
    OkHttpConfig provideOkhttpConfig() {
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
        private GlobalHttpHandler httpHandler;
        private RetrofitConfig retrofitConfig;
        private OkHttpConfig okhttpConfig;
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
            this.httpHandler = handler;
            return this;
        }

        public Builder retrofitConfig(RetrofitConfig retrofitConfig) {
            this.retrofitConfig = retrofitConfig;
            return this;
        }

        public Builder okhttpConfig(OkHttpConfig okhttpConfig) {
            this.okhttpConfig = okhttpConfig;
            return this;
        }

        public Builder cacheConfig(RxCacheConfig rxCacheConfig) {
            this.rxCacheConfig = rxCacheConfig;
            return this;
        }

    }
}
