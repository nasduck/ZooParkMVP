[![API](https://img.shields.io/badge/ZooParkMVP-v1.0.1-brightgreen.svg?style=flat)](https://github.com/nasduck/ZooParkMVP/releases)&ensp;
[![API](https://img.shields.io/badge/API-14%2B-brightgreen.svg?style=flat)](https://android-arsenal.com/api?level=14)&ensp;
[![API](https://img.shields.io/badge/License-Apche2.0-brightgreen.svg?style=flat)](https://github.com/nasduck/ZooParkMVP/blob/master/LICENSE)

ZooParkMVP 是一个轻量级的 MVP 框架. 整合了大量常用第三方库, 通过 Dagger 方法注入到项目中. 具有以下特点.

* 支持 [Butterknife](http://jakewharton.github.io/butterknife/)
* 支持 [ARouter](https://github.com/alibaba/ARouter) 路由 
* 支持 [Dagger2](https://dagger.dev/) 依赖注入
* 支持 [RxJava](https://github.com/ReactiveX/RxJava)/[Retrofit](https://square.github.io/retrofit/)/[OkHttp](https://square.github.io/okhttp/) 形式的网络调用
* 支持 [RxCache](https://github.com/VictorAlbertos/RxCache) 缓存层
* 支持 [EventBus](https://github.com/greenrobot/EventBus) 事件通信
* 支持全局捕获 Http 请求和返回
* 通过代理模式设置 Application/Activity 额外的生命周期配置. 无需额外创建 Application 与 BaseActivity.

