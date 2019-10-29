[![API](https://img.shields.io/badge/ZooParkMVP-v1.0.1-brightgreen.svg?style=flat)](https://github.com/nasduck/ZooParkMVP/releases)&ensp;
[![API](https://img.shields.io/badge/API-14%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=14)&ensp;
[![API](https://img.shields.io/badge/License-Apche2.0-brightgreen.svg?style=flat)](https://github.com/nasduck/ZooParkMVP/blob/master/LICENSE)

**ZooParkMVP 是一个轻量级的 MVP 框架. 整合了大量常用第三方库, 通过 Dagger 方法注入到项目中**. 具有以下特点.

* 极简使用
* 支持 [Butterknife](http://jakewharton.github.io/butterknife/)
* 支持 [ARouter](https://github.com/alibaba/ARouter) 路由 
* 支持 [Dagger2](https://dagger.dev/) 依赖注入
* 支持 [RxJava](https://github.com/ReactiveX/RxJava)/[Retrofit](https://square.github.io/retrofit/)/[OkHttp](https://square.github.io/okhttp/) 形式的网络调用
* 支持 [RxCache](https://github.com/VictorAlbertos/RxCache) 缓存层
* 支持 [EventBus](https://github.com/greenrobot/EventBus) 事件通信
* 支持全局捕获 Http 请求和返回
* 通过代理模式设置 Application/Activity 额外的生命周期配置. 无需额外创建 Application 与 BaseActivity.

# 添加依赖

# 配置框架

`AndroidManifest.xml` 文件使用框架中预先定义的 `ZooApplication`. 然后创建全局配置文件 `GlobalConfiguration.java`, 在 `AndroidManifest.xml` 中以 `meta-data` 的方法声明该文件:

```xml
<application
  android:name="com.zoopark.lib.app.ZooApplication"
  ...其它属性>
  
  <!-- 全局配置文件 -->
  <meta-data
    android:name="com.zoopark.demo.base.GlobalConfiguration"
    android:value="ConfigModule" />
  
</application>        
```

`GlobalConfiguration.java` 文件, 在其中用户可以设置全局的框架配置:

* API 域名
* 全局 Http 拦截配置
* 额外的 RxCache 配置
* 额外的 Retrofit 配置
* 额外的 OkHttp 配置
* 配置 Application 的生命周期
* 配置全局 Activity 的生命周期

```java
public class GlobalConfiguration implements IConfigModule {

    /**
     * 初始化动物园 (初始化框架)
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
                })
                .cacheConfig(RxCacheConfig)
                .retrofitConfig(RetrofitConfig)
                .okhttpConfig(OkHttpConfig);
    }

    /**
     * 配置 Application 的生命周期
     */
    @Override
    public IAppLifecycle getAppLifecycle() {
        return new AppLifecycle(); // 用户自定义, 教程稍后讲解
    }

    /**
     * 配置全局 Activity 的生命周期
     */
    @Override
    public IActivityLifecycle getActivityLifecycle() {
        return new ActivityLifecycle(); // 用户自定义, 教程稍后讲解
    }
}

```


