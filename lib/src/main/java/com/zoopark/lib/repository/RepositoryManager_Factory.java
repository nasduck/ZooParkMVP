package com.zoopark.lib.repository;

import android.app.Application;

import javax.annotation.Generated;
import javax.inject.Provider;

import dagger.internal.DoubleCheck;
import dagger.internal.Factory;
import retrofit2.Retrofit;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class RepositoryManager_Factory implements Factory<RepositoryManager> {
  private final Provider<Retrofit> mRetrofitProvider;

  private final Provider<Application> mApplicationProvider;

  public RepositoryManager_Factory(
      Provider<Retrofit> mRetrofitProvider, Provider<Application> mApplicationProvider) {
    this.mRetrofitProvider = mRetrofitProvider;
    this.mApplicationProvider = mApplicationProvider;
  }

  @Override
  public RepositoryManager get() {
    RepositoryManager instance = new RepositoryManager();
    RepositoryManager_MembersInjector.injectMRetrofit(
        instance, DoubleCheck.lazy(mRetrofitProvider));
    RepositoryManager_MembersInjector.injectMApplication(instance, mApplicationProvider.get());
    return instance;
  }

  public static RepositoryManager_Factory create(
      Provider<Retrofit> mRetrofitProvider, Provider<Application> mApplicationProvider) {
    return new RepositoryManager_Factory(mRetrofitProvider, mApplicationProvider);
  }

  public static RepositoryManager newRepositoryManager() {
    return new RepositoryManager();
  }
}
