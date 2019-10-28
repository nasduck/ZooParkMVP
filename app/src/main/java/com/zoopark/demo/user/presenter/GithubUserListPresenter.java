package com.zoopark.demo.user.presenter;

import android.widget.Toast;

import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.zoopark.demo.user.contract.GithubUserListContract;
import com.zoopark.demo.user.model.entity.GithubUserBean;
import com.zoopark.demo.user.ui.adapter.GithubUserAdapter;
import com.zoopark.lib.mvp.impl.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.rx_cache2.EvictProvider;
import io.rx_cache2.Reply;

public class GithubUserListPresenter extends BasePresenter<GithubUserListContract.Model, GithubUserListContract.View> {

    @Inject
    GithubUserAdapter mAdapter;

    @Inject
    public GithubUserListPresenter(GithubUserListContract.Model model, GithubUserListContract.View rootView) {
        super(model, rootView);
    }

    public void callGetGithubUserList(boolean isUpdateCache, final RefreshLayout refreshLayout) {
        Disposable observer = mModel.getUserListCache(mModel.getUserInfo(), new EvictProvider(isUpdateCache))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Reply<List<GithubUserBean>>>() {
                    @Override
                    public void accept(Reply<List<GithubUserBean>> listReply) throws Exception {
                        Toast.makeText(mRootView.getSelf(), "Access to data：" + listReply.getSource(), Toast.LENGTH_SHORT).show();
                        mAdapter.setData(listReply.getData());
                        mAdapter.notifyDataSetChanged();
                        if (refreshLayout != null) refreshLayout.finishRefresh();
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(mRootView.getSelf(), "Data acquisition failed", Toast.LENGTH_SHORT).show();
                        if (refreshLayout != null) refreshLayout.finishRefresh();
                    }
                });
        addDispose(observer);
    }
}
