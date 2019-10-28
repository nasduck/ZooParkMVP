package com.zoopark.lib.inject.component;

import android.app.Application;

import com.zoopark.lib.app.AppDelegate;
import com.zoopark.lib.inject.module.AppModule;
import com.zoopark.lib.inject.module.CacheModule;
import com.zoopark.lib.inject.module.ClientModule;
import com.zoopark.lib.inject.module.GlobalConfigModule;
import com.zoopark.lib.repository.IRepositoryManager;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, ClientModule.class, CacheModule.class, GlobalConfigModule.class})
public interface AppComponent {

    // 暴露出来给需要依赖的其他 Components 使用
    Application application();
    IRepositoryManager repositoryManager();

    void inject(AppDelegate appDelegate);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        Builder globalConfigModule(GlobalConfigModule globalConfigModule);
        AppComponent build();
    }

}