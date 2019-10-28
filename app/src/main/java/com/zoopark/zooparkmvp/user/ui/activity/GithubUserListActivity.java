package com.zoopark.zooparkmvp.user.ui.activity;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zoopark.lib.BaseActivity;
import com.zoopark.lib.BaseApplication;
import com.zoopark.zooparkmvp.R;
import com.zoopark.zooparkmvp.user.contract.GithubUserListContract;
import com.zoopark.zooparkmvp.user.di.component.DaggerGithubUserListComponent;
import com.zoopark.zooparkmvp.user.di.module.GithubUserListModule;
import com.zoopark.zooparkmvp.user.presenter.GithubUserListPresenter;
import com.zoopark.zooparkmvp.user.ui.adapter.GithubUserAdapter;

import javax.inject.Inject;

import butterknife.BindView;

import static com.zoopark.zooparkmvp.base.RouterCenter.GithubUser.GITHUB_USER_LIST;

@Route(path = GITHUB_USER_LIST)
public class GithubUserListActivity extends BaseActivity<GithubUserListPresenter>
        implements GithubUserListContract.View, OnRefreshListener {

    @Inject
    GithubUserAdapter mAdapter;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh_layout)
    RefreshLayout mRefreshLayout;

    @Override
    public void initComponent() {
        DaggerGithubUserListComponent
                .builder()
                .appComponent(((BaseApplication)this.getApplicationContext()).getAppComponent())
                .githubUserListModule(new GithubUserListModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_github_user;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setSupportActionBar(mToolbar);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);

        mRefreshLayout.setOnRefreshListener(this);

        mPresenter.callGetGithubUserList(false, null);
    }

    @Override
    public BaseActivity getSelf() {
        return this;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mPresenter.callGetGithubUserList(true, refreshLayout);
    }
}
