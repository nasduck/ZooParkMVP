package com.zoopark.zooparkmvp.base;

import android.content.Context;

import com.zoopark.lib.base.IConfigModule;
import com.zoopark.lib.application.IAppLifecycle;
import com.zoopark.lib.inject.config.GlobalHttpHandler;
import com.zoopark.lib.inject.module.GlobalConfigModule;

import okhttp3.Request;
import okhttp3.Response;

public class GlobalConfiguration implements IConfigModule {

    /**
     * 初始化动物园 (初始化框架)
     *
     * @param context
     * @param builder
     */
    @Override
    public void configZoo(Context context, GlobalConfigModule.Builder builder) {
        builder.baseurl("https://api.github.com")
                .globalHttpHandler(new GlobalHttpHandler() {
                    @Override
                    public Response onHttpResponse(Response response) {
                        // Intercept global response
                        // You could check expired token to jump to Login page.
                        return response;
                    }

                    @Override
                    public Request onBeforeHttpRequest(Request request) {
                        // Intercept global response
                        // You may add header/token to all the requests
                        return request;
                    }
                });
    }

    /**
     * 配置 Application 生命周期
     */
    @Override
    public IAppLifecycle getAppLifecycle() {
        return new AppLifecycle();
    }
}
