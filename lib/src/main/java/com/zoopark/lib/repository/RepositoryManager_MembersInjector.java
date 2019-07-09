package com.zoopark.lib.repository;

import android.app.Application;

import javax.annotation.Generated;
import javax.inject.Provider;

import dagger.Lazy;
import dagger.MembersInjector;
import dagger.internal.DoubleCheck;
import retrofit2.Retrofit;

@Generated(
  value = "dagger.internal.codegen.ComponentProcessor",
  comments = "https://google.github.io/dagger"
)
public final class RepositoryManager_MembersInjector implements MembersInjector<RepositoryManager> {
  private final Provider<Retrofit> mRetrofitProvider;

  private final Provider<Application> mApplicationProvider;

  public RepositoryManager_MembersInjector(
      Provider<Retrofit> mRetrofitProvider, Provider<Application> mApplicationProvider) {
    this.mRetrofitProvider = mRetrofitProvider;
    this.mApplicationProvider = mApplicationProvider;
  }

  public static MembersInjector<RepositoryManager> create(
      Provider<Retrofit> mRetrofitProvider, Provider<Application> mApplicationProvider) {
    return new RepositoryManager_MembersInjector(mRetrofitProvider, mApplicationProvider);
  }

  @Override
  public void injectMembers(RepositoryManager instance) {
    injectMRetrofit(instance, DoubleCheck.lazy(mRetrofitProvider));
    injectMApplication(instance, mApplicationProvider.get());
  }

  public static void injectMRetrofit(RepositoryManager instance, Lazy<Retrofit> mRetrofit) {
    instance.mRetrofit = mRetrofit;
  }

  public static void injectMApplication(RepositoryManager instance, Application mApplication) {
    instance.mApplication = mApplication;
  }
}
