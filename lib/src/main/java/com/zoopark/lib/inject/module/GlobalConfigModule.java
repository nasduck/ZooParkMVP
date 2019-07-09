package com.zoopark.lib.inject.module;

import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.zoopark.lib.inject.iconfig.BaseUrl;
import com.zoopark.lib.inject.iconfig.GlobalHttpHandler;
import com.zoopark.lib.inject.iconfig.OkhttpConfiguration;
import com.zoopark.lib.inject.iconfig.RetrofitConfiguration;
import com.zoopark.lib.utils.Preconditions;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;

@Module
public class GlobalConfigModule {

    private HttpUrl mApiUrl;
    private BaseUrl mBaseUrl;
    private GlobalHttpHandler mHandler;
    private RetrofitConfiguration mRetrofitConfiguration;
    private OkhttpConfiguration mOkhttpConfiguration;

    private GlobalConfigModule(Builder builder) {
        this.mApiUrl = builder.apiUrl;
        this.mBaseUrl = builder.baseUrl;
        this.mHandler = builder.handler;
        this.mRetrofitConfiguration = builder.retrofitConfiguration;
        this.mOkhttpConfiguration = builder.okhttpConfiguration;
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
    RetrofitConfiguration provideRetrofitConfiguration() {
        return mRetrofitConfiguration;
    }

    @Singleton
    @Provides
    @Nullable
    OkhttpConfiguration provideOkhttpConfiguration() {
        return mOkhttpConfiguration;
    }

    public static class Builder {

        private HttpUrl apiUrl;
        private BaseUrl baseUrl;
        private GlobalHttpHandler handler;
        private RetrofitConfiguration retrofitConfiguration;
        private OkhttpConfiguration okhttpConfiguration;

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

        public Builder retrofitConfiguration(RetrofitConfiguration retrofitConfiguration) {
            this.retrofitConfiguration = retrofitConfiguration;
            return this;
        }

        public Builder okhttpConfiguration(OkhttpConfiguration okhttpConfiguration) {
            this.okhttpConfiguration = okhttpConfiguration;
            return this;
        }

    }
}
