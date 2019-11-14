![banner](https://github.com/nasduck/ZooParkMVP/blob/develop/art/zoopark%20banner.png?raw=true)

[![API](https://img.shields.io/badge/ZooParkMVP-v1.0.5-brightgreen.svg?style=flat)](https://github.com/nasduck/ZooParkMVP/releases)&ensp;
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
* 可使用 PM 层的方式来实现 Model 的复用
* 通过代理模式设置 Application/Activity 额外的生命周期配置. 无需额外创建 Application 与 BaseActivity.

# 添加依赖

步骤一：在项目的build.gradle中添加jitpack

```
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```
步骤二：添加依赖项

```
  implementation 'com.github.nasduck:ZooParkMVP:1.0.5'
```

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
        return new AppLifecycle(); // 用户自定义, 教程稍后讲解. 不使用则返回 NULL.
    }

    /**
     * 配置全局 Activity 的生命周期
     */
    @Override
    public IActivityLifecycle getActivityLifecycle() {
        return new ActivityLifecycle(); // 用户自定义, 教程稍后讲解. 不使用则返回 NULL.
    }
}

```

# 配置 Application 的生命周期

无需添加额外的 `Application` 来继承框架的 `ZooApplication`. 只需实现 `IAppLifecycle` 接口并加入到 `GlobalConfiguration` 中即可.

```java
/**
 * 用户在 Application 生命周期中的额外配置
 */
public class AppLifecycle implements IAppLifecycle {

    @Override
    public void attachBaseContext(Context context) {
        // Do anything you want
    }

    @Override
    public void onCreate(Application app) {
        // Do anything you want
    }

    @Override
    public void onTerminate(Application app) {
        // Do anything you want
    }
}
```

# 配置全局 Activity 的生命周期

同理, 无需添加额外的基类 `Activity` 来继承框架的 `BaseActivity`. 只需实现 `IActivityLifecycle` 接口并加入到 `GlobalConfiguration` 中即可.

```java
/**
 * 用户在全局 Activity 生命周期中的额外配置
 */
public class ActivityLifecycle implements IActivityLifecycle {

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        // Do anything you want
    }

    @Override
    public void onActivityStarted(Activity activity) {
        // Do anything you want
    }

    @Override
    public void onActivityResumed(Activity activity) {
        // Do anything you want
    }

    @Override
    public void onActivityPaused(Activity activity) {
        // Do anything you want
    }

    @Override
    public void onActivityStopped(Activity activity) {
        // Do anything you want
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        // Do anything you want
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        // Do anything you want
    }
}
```

# 实现 MVP

由几部分构成:

* `Contract` - 视图层 `IView` 与数据层 `IModel` 的沟通合约. **约定**视图层显示UI的方法以及数据层获得数据的方法.
* `DependencyInject` - 依赖注入层. 提供视图层/数据层. 以及其它依赖, 比如 `RecycleView Adapter`.
* `Model` - 数据层. 实现`Contract`中约定的数据层合约. 注入 `IRepositoryManager` 提供 API 调用以及缓存层实现 `RxCache`.
* `Presenter` - 业务层逻辑实现. 具体的页面逻辑. 注入数据层与视图层. 负责从数据层获取逻辑并更新视图层
* `View` - 视图层. `Activity`/`Fragment` 等. 实现`Contract`中约定的视图层合约. 注入 `Presenter`.

以 Demo 中的代码为例, 从 Github Api 获取数据并以列表展示:

### Contract 合约

```java
public interface GithubUserListContract {

    interface View extends IView {
        // 视图层提供了一个获取自身的方法
        BaseActivity getSelf();
    }

    interface Model extends IModel {
        // 调用 API 获取用户信息
        Observable<List<GithubUserBean>> getUserInfo();
        
        // 从缓存获取用户信息
        Observable<Reply<List<GithubUserBean>>> getUserListCache(Observable<List<GithubUserBean>> observable, EvictProvider evictProvider);
    }

}
```

### DependencyInject 依赖注入层

```java
@Module
public class GithubUserListModule {

    private GithubUserListContract.View view;

    public GithubUserListModule(GithubUserListContract.View view) {
        this.view = view;
    }
    
    /**
     * 提供视图层
     */
    @ActivityScope
    @Provides
    GithubUserListContract.View provideGithubUserListView() {
        return this.view;
    }

    /**
     * 提供数据层
     */
    @ActivityScope
    @Provides
    GithubUserListContract.Model provideGithubUserListModel(GithubUserListModel model) {
        return model;
    }

    /**
     * 提供列表需要的 Adapter
     */
    @ActivityScope
    @Provides
    GithubUserAdapter providerGithubUserListAdapter() {
        return new GithubUserAdapter(view.getSelf()); // 调用了约定的视图层的 getSelf 方法
    }
}
```

```java
@ActivityScope
@Component(modules = GithubUserListModule.class, dependencies = AppComponent.class)
public interface GithubUserListComponent {
    void inject(GithubUserListActivity activity);
}
```

### Model 数据层

数据层继承 `BaseModel` 类并实现合约接口.

```java
@ActivityScope
public class GithubUserListModel extends BaseModel implements GithubUserListContract.Model {

    @Inject
    public GithubUserListModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    /**
     * 调用 API 获得数据
     */
    @Override
    public Observable<List<GithubUserBean>> getUserInfo() {
        return mRepositoryManager.obtainRetrofitService(GithubUserService.class)
                .getUserList();
    }

    /**
     * 从缓存层获得数据
     */
    @Override
    public Observable<Reply<List<GithubUserBean>>> getUserListCache(Observable<List<GithubUserBean>> observable, EvictProvider evictProvider) {
        return mRepositoryManager.obtainCacheService(GithubUserCacheService.class)
                .getUserListCache(observable, evictProvider);
    }


}
```

通过上述这种方式实现的 Model 是和 P 层其实是业务相关的. (如果你的项目中不需要对 Model 进行复用，那么就不需要进行下面的拆分)

比如这个页面需要调用用户信息和登录的 API, 那这个 Model 层就必须混合这两种不同的 API. 如果我们希望达到复用的目的, 即根据 Retrofit 的特点, 有一个专门管理用户登录注册的 LoginService, 一个专门管理用户信息的 UserService, 它们分别对应 LoginModel 和 UserModel, 和业务接耦, 这样就可以在不同的地方复用它们.

具体实现:

* 核心即再加一层数据层, 因为和业务关联, 我们简称 PM 层.
* 在 PM 层中通过 Dagger 将需要复用的 Model 注入进来.

下面的例子为复用 GithubUserModel 与 GithubLoginModel:

GithubUserService 相关的 Model:

```java
@ActivityScope
public class GithubUserModel extends BaseModel {

    @Inject
    public GithubUserModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    public Observable<GithubUserBean> getUserInfo(String username) {
        return mRepositoryManager.obtainRetrofitService(GithubUserService.class)
                .getUserInfo(username);
    }
}
```

GithubLoginService 相关的 Model:

```java
@ActivityScope
public class GithubLoginModel extends BaseModel {

    @Inject
    public GithubLoginModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

    public Observable<GithubLoginBean> getLogin(String phone) {
        return mRepositoryManager.obtainRetrofitService(GithubLoginService.class)
                .getLogin(phone);
    }
}
```

与 P 层逻辑相关的 PM 层：

```java
@ActivityScope
public class GithubUserInfoModel implements GithubUserInfoContract.Model {

    // 引用与业务相关的 model
    private GithubUserModel mUserModel;
    private GithubLoginModel mLoginModel;

    @Inject
    public GithubUserInfoModel(GithubUserModel userModel, GithubLoginModel loginModel) {
        this.mUserModel = userModel;
	this.mLoginModel = loginModel;
    }

    @Override
    public Observable<GithubUserBean> getUserInfo(String username) {
        return mUserModel.getUserInfo(username);
    }
    
    @Override
    public Observable<GithubLoginBean> getLogin(String phone) {
        return mLoginModel.getLogin(phone);
    }

    @Override
    public void onDestroy() {
        mUserModel = null;
	mLoginModel = null;
    }
}
```

### Presenter 业务层

继承 `BasePresenter` 并指定数据层和视图层的合约. 注入数据层与视图层:

```java
public class GithubUserInfoPresenter extends BasePresenter<GithubUserInfoContract.Model, GithubUserInfoContract.View> {

    @Inject
    public GithubUserInfoPresenter(GithubUserInfoContract.Model model, GithubUserInfoContract.View rootView) {
        super(model, rootView);
    }

    /**
     * 实际的 API 调用逻辑
     */
    public void callGetGithubUserInfo(String userName) {
        DisposableObserver<GithubUserBean> observer = mModel.getUserInfo(userName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<GithubUserBean>() {
                    @Override
                    public void onNext(GithubUserBean githubUserBean) {
                        mRootView.configUI(githubUserBean); // 更新视图
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
        addDispose(observer);
    }
}
```

### 视图层

继承 `BaseActivity`. 并指定业务 `Presenter`:

```java
public class GithubUserInfoActivity extends BaseActivity<GithubUserInfoPresenter>
        implements GithubUserInfoContract.View {
        
    @Override
    public void initComponent() {
        DaggerGithubUserInfoComponent
                .builder()
                .appComponent(((ZooApplication)this.getApplicationContext()).getAppComponent())
                .githubUserInfoModule(new GithubUserInfoModule(this))
                .build()
                .inject(this);
    }
    
    ...其它代码
    
    // 调用业务层逻辑
    // mPresenter.callGetGithubUserInfo(mUserName);

}
```

# 使用 EventBus

如果要使用 EventBus, 需要在 Activity/Fragment 中覆写 `useEventBus`, 返回 `true` (默认 `false`): 

```
@Override
public boolean useEventBus() {
    return false;
}
```    

## Suggestion&Question

Welcome to send emails to dongchuanyz@163.com

## Contributors

* [Chuan DONG](https://github.com/DONGChuan)
* [Lihao Zhou](https://github.com/redrain39)
* [Xiaoliang Yang](https://github.com/sohnyi)

## LICENSE
```
   Copyright (2019) Chuan Dong, Lihao Zhou, Si Cheng

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
```
