package com.zoopark.lib.base;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.alibaba.android.arouter.launcher.ARouter;
import com.zoopark.lib.BuildConfig;
import com.zoopark.lib.inject.component.AppComponent;

import com.zoopark.lib.inject.component.DaggerAppComponent;
import com.zoopark.lib.inject.module.GlobalConfigModule;
import com.zoopark.lib.utils.ManifestParser;

import java.util.List;

public class BaseApplication extends Application {

    public static Context context;

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;

        if (BuildConfig.DEBUG) {
            ARouter.openLog();     // 打印日志
            ARouter.openDebug();   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this);
        initComponent();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public void initComponent() {
        mAppComponent = DaggerAppComponent.builder()
                .application(this)
                .globalConfigModule(getGlobalConfigModule(this, new ManifestParser(context).parse()))
                .build();

        mAppComponent.inject(this);
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    private GlobalConfigModule getGlobalConfigModule(Context context, List<ConfigModule> modules) {

        GlobalConfigModule.Builder builder = GlobalConfigModule.builder();

        //遍历 ConfigModule 集合, 给全局配置 GlobalConfigModule 添加参数
        for (ConfigModule module : modules) {
            module.applyOptions(context, builder);
        }

        return builder.build();
    }

}
