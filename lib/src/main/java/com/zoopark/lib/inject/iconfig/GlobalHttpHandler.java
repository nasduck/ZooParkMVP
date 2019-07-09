package com.zoopark.lib.inject.iconfig;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 处理 Http 请求和响应结果的处理类
 */
public interface GlobalHttpHandler {

    Response onHttpResponse(String httpResult, Interceptor.Chain chain, Response response);

    Request onBeforeHttpRequest(Interceptor.Chain chain, Request request);

    // 空实现
    GlobalHttpHandler EMPTY = new GlobalHttpHandler() {
        @Override
        public Response onHttpResponse(String httpResult, Interceptor.Chain chain, Response response) {
            // 不管是否处理,都必须将response返回出去
            return response;
        }

        @Override
        public Request onBeforeHttpRequest(Interceptor.Chain chain, Request request) {
            // 不管是否处理,都必须将request返回出去
            return request;
        }
    };
}
