package com.zoopark.lib.app;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.zoopark.lib.BuildConfig;
import com.zoopark.lib.inject.component.AppComponent;
import com.zoopark.lib.inject.component.DaggerAppComponent;
import com.zoopark.lib.inject.module.GlobalConfigModule;
import com.zoopark.lib.utils.ManifestParser;

public class AppDelegate implements IAppLifecycle {

    private Application mApp;
    private AppComponent mAppComponent;
    private IConfigModule mConfigModule;
    private IAppLifecycle mAppLifecycle;
    private IActivityLifecycle mActivityLifecycle;

    public AppDelegate(Context context) {

        // 通过反射, 取 Manifest 文件中配置的 ConfigModule
        this.mConfigModule = new ManifestParser(context).parse().get(0);

        // 获得用户对生命周期的额外配置
        mAppLifecycle = mConfigModule.getAppLifecycle();
        mActivityLifecycle = mConfigModule.getActivityLifecycle();
    }

    @Override
    public void attachBaseContext(Context context) {
        if (mAppLifecycle != null) mAppLifecycle.attachBaseContext(context);

        MultiDex.install(mApp);
    }

    @Override
    public void onCreate(Application app) {
        this.mApp = app;

        if (BuildConfig.DEBUG) {
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }

        // 初始化 ARouter
        ARouter.init(app);

        // 初始化全局配置
        GlobalConfigModule.Builder builder = GlobalConfigModule.builder();
        mConfigModule.configZoo(app, builder);

        mAppComponent = DaggerAppComponent.builder()
                .application(app)
                .globalConfigModule(builder.build())
                .build();

        mAppComponent.inject(this);

        // 配置用户定义的 Activity 生命周期
        if (mActivityLifecycle != null) mApp.registerActivityLifecycleCallbacks(mActivityLifecycle);

        if (mAppLifecycle != null) mAppLifecycle.onCreate(app);
    }

    @Override
    public void onTerminate(Application app) {
        this.mApp = app;

        if (mActivityLifecycle != null) app.unregisterActivityLifecycleCallbacks(mActivityLifecycle);

        if (mAppLifecycle != null) mAppLifecycle.onTerminate(app);

        this.mApp = null;
        this.mAppComponent = null;
        this.mConfigModule = null;
        this.mAppLifecycle = null;
        this.mActivityLifecycle = null;
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
