package com.zoopark.demo.service;

import com.zoopark.demo.user.model.entity.GithubUserBean;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.rx_cache2.EvictProvider;
import io.rx_cache2.LifeCache;
import io.rx_cache2.Reply;

public interface GithubUserCacheService {

    @LifeCache(duration = 2, timeUnit = TimeUnit.MINUTES)
    Observable<Reply<List<GithubUserBean>>> getUserListCache(Observable<List<GithubUserBean>> observable, EvictProvider evictProvider);
}
