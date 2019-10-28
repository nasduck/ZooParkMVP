package com.zoopark.zooparkmvp.user.contract;

import com.zoopark.lib.BaseActivity;
import com.zoopark.lib.ok.IModel;
import com.zoopark.lib.ok.IView;
import com.zoopark.zooparkmvp.user.model.entity.GithubUserBean;

import java.util.List;

import io.reactivex.Observable;
import io.rx_cache2.EvictProvider;
import io.rx_cache2.Reply;

public interface GithubUserListContract {

    interface View extends IView {
        BaseActivity getSelf();
    }

    interface Model extends IModel {
        Observable<List<GithubUserBean>> getUserInfo();

        Observable<Reply<List<GithubUserBean>>> getUserListCache(Observable<List<GithubUserBean>> observable, EvictProvider evictProvider);

    }

}