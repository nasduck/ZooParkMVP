package com.zoopark.lib.inject.module;

import android.app.Application;
import android.support.annotation.Nullable;

import com.zoopark.lib.inject.config.GlobalHttpHandler;
import com.zoopark.lib.inject.config.OkHttpConfig;
import com.zoopark.lib.inject.config.RetrofitConfig;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
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
     * @param httpHandler 全局 Http 拦截器
     * @param builder     OkHttp 构造器
     * @return
     */
    @Singleton
    @Provides
    static OkHttpClient provideClient(Application app,
                                      @Nullable OkHttpConfig config,
                                      HttpLoggingInterceptor interceptor,
                                      @Nullable final GlobalHttpHandler httpHandler,
                                      OkHttpClient.Builder builder) {

        builder.connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(interceptor);

        // 全局 Http 拦截
        if (httpHandler != null) {
            builder.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request request = httpHandler.onBeforeHttpRequest(chain.request());
                    Response response = chain.proceed(request);
                    return  httpHandler.onHttpResponse(response);
                }
            });
        }

        // OkHttp 配置
        if (config != null) {
            config.configOkHttp(app, builder);
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


