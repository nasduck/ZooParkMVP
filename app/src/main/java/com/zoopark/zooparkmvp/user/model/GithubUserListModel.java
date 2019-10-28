package com.zoopark.zooparkmvp.user.model;

import com.zoopark.lib.mvp.impl.BaseModel;
import com.zoopark.lib.inject.scope.ActivityScope;
import com.zoopark.lib.repository.IRepositoryManager;
import com.zoopark.zooparkmvp.service.GithubUserCacheService;
import com.zoopark.zooparkmvp.service.GithubUserService;
import com.zoopark.zooparkmvp.user.contract.GithubUserListContract;
import com.zoopark.zooparkmvp.user.model.entity.GithubUserBean;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.rx_cache2.EvictProvider;
import io.rx_cache2.Reply;

@ActivityScope
public class GithubUserListModel extends BaseModel implements GithubUserListContract.Model {

    @Inject
    public GithubUserListModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }


    @Override
    public Observable<List<GithubUserBean>> getUserInfo() {
        return mRepositoryManager.obtainRetrofitService(GithubUserService.class)
                .getUserList();
    }

    @Override
    public Observable<Reply<List<GithubUserBean>>> getUserListCache(Observable<List<GithubUserBean>> observable, EvictProvider evictProvider) {
        return mRepositoryManager.obtainCacheService(GithubUserCacheService.class)
                .getUserListCache(observable, evictProvider);
    }


}
