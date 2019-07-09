package com.zoopark.lib.inject.module;

import com.zoopark.lib.repository.IRepositoryManager;
import com.zoopark.lib.repository.RepositoryManager;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class AppModule {

    @Binds
    abstract IRepositoryManager bindRepositoryManager(RepositoryManager repositoryManager);

}
