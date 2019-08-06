package com.zoopark.lib.inject.module;

import android.app.Application;
import android.support.annotation.Nullable;

import com.zoopark.lib.BaseApplication;
import com.zoopark.lib.inject.iconfig.OkhttpConfiguration;
import com.zoopark.lib.inject.iconfig.RetrofitConfiguration;

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
     * 提供 {@link Retrofit}
     *
     * @param client
     * @return {@link Retrofit}
     */
    @Singleton
    @Provides
    static Retrofit provideRetrofit(Application application, OkHttpClient client,
                                    @Nullable RetrofitConfiguration configuration, HttpUrl httpUrl) {
        Retrofit.Builder builder = new Retrofit.Builder();

        builder.baseUrl(httpUrl)
                .client(client);

        if (configuration != null) configuration.configRetrofit(application, builder);

        builder.addCallAdapterFactory(RxJava2CallAdapterFactory.create()) // RxJava
                .addConverterFactory(GsonConverterFactory.create()); // Gson

        return builder.build();
    }

    /**
     * 提供 {@link OkHttpClient}
     *
     * @return {@link OkHttpClient}
     */
    @Singleton
    @Provides
    static OkHttpClient provideClient(@Nullable OkhttpConfiguration configuration, OkHttpClient.Builder builder) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        builder.connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(interceptor);
        if (configuration != null) {
            configuration.configOkhttp(BaseApplication.context, builder);
        }

        return builder.build();

    }


    @Singleton
    @Provides
    static OkHttpClient.Builder provideClientBuilder() {
        return new OkHttpClient.Builder();
    }

}


