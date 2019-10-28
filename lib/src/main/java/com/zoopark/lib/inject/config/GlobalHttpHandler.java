package com.zoopark.lib.inject.config;

import okhttp3.Request;
import okhttp3.Response;

/**
 * 处理 Http 请求和响应结果的处理类
 */
public interface GlobalHttpHandler {

    /**
     * 在客户端拿到数据之前拦截返回结果. 做统一处理. 比如 Token 过期.
     *
     * @param response 返回
     * @return 处理后的返回
     */
    Response onHttpResponse(Response response);

    /**
     * 在请求服务器之前拦截修改 Request, 比如统一添加 token 或 header.
     *
     * @param request 请求
     * @return 处理后的请求
     */
    Request onBeforeHttpRequest(Request request);


}
