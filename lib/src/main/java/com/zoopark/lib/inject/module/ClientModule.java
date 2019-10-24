package com.zoopark.lib.inject.module;

import android.app.Application;
import android.support.annotation.Nullable;

import com.zoopark.lib.BaseApplication;
import com.zoopark.lib.inject.iconfig.OkhttpConfig;
import com.zoopark.lib.inject.iconfig.RetrofitConfig;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public abstract class ClientModule {

    /**
     * 提供 Retrofit
     *
     * @param app     Application
     * @param client  OKHttp
     * @param config  用户自定义的 Retrofit 配置
     * @param httpUrl 域名
     * @return
     */
    @Singleton
    @Provides
    static Retrofit provideRetrofit(Application app,
                                    OkHttpClient client,
                                    @Nullable RetrofitConfig config,
                                    HttpUrl httpUrl) {
        Retrofit.Builder builder = new Retrofit.Builder();

        builder.baseUrl(httpUrl)
                .client(client);

        if (config != null)
            config.configRetrofit(app, builder);

        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // RxJava
                .addConverterFactory(GsonConverterFactory.create()); // Gson

        return builder.build();
    }

    /**
     * 提供 OkHttpClient
     *
     * @param config      用户自定义的 OkHttp 配置
     * @param interceptor Http Logging 拦截器
     * @param builder     OkHttp 构造器
     * @return
     */
    @Singleton
    @Provides
    static OkHttpClient provideClient(@Nullable OkhttpConfig config,
                                      HttpLoggingInterceptor interceptor,
                                      OkHttpClient.Builder builder) {

        builder.connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(interceptor);

        if (config != null) {
            config.configOkhttp(BaseApplication.context, builder);
        }

        return builder.build();

    }

    /**
     * 提供 Http Logging 拦截器
     *
     * @return HttpLoggingInterceptor
     */
    @Singleton
    @Provides
    static HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return interceptor;
    }

    /**
     * 提供 Retrofit 构造器
     *
     * @return Retrofit Builder
     */
    @Singleton
    @Provides
    static Retrofit.Builder provideRetrofitBuilder() {
        return new Retrofit.Builder();
    }

    /**
     * 提供 OkHttp 构造器
     *
     * @return OkHttp Builder
     */
    @Singleton
    @Provides
    static OkHttpClient.Builder provideOkHttpBuilder() {
        return new OkHttpClient.Builder();
    }

}


