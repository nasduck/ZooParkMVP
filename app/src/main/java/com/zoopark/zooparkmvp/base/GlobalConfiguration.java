package com.zoopark.zooparkmvp.base;

import android.content.Context;

import com.zoopark.lib.base.ConfigModule;
import com.zoopark.lib.inject.config.GlobalHttpHandler;
import com.zoopark.lib.inject.module.GlobalConfigModule;

import okhttp3.Request;
import okhttp3.Response;

public class GlobalConfiguration implements ConfigModule {

    @Override
    public void applyOptions(Context context, GlobalConfigModule.Builder builder) {
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
}
