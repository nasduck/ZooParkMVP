package com.zoopark.zooparkmvp.home;

import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.launcher.ARouter;
import com.zoopark.lib.BaseActivity;
import com.zoopark.zooparkmvp.R;
import com.zoopark.zooparkmvp.base.event.AddFavoriteUserEvent;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;

import static com.zoopark.zooparkmvp.base.RouterCenter.GithubUser.GITHUB_USER_LIKE;
import static com.zoopark.zooparkmvp.base.RouterCenter.GithubUser.GITHUB_USER_LIST;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.btn_user_like)
    Button mBtnUserLike;

    private String mUserName;
    private String mAvatarUrl;

    @Override
    public void initComponent() {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_home;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        setSupportActionBar(mToolbar);
    }

    @Override
    public boolean useEventBus() {
        return true;
    }

    @OnClick(R.id.btn_github_user)
    public void onGithubUserClick() {
        ARouter.getInstance()
                .build(GITHUB_USER_LIST)
                .navigation();
    }

    @OnClick(R.id.btn_user_like)
    public void onFavoriteUserClick() {
        ARouter.getInstance()
                .build(GITHUB_USER_LIKE)
                .withString("userName", mUserName)
                .withString("avatar", mAvatarUrl)
                .navigation();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(AddFavoriteUserEvent event) {
        mBtnUserLike.setVisibility(View.VISIBLE);
        mUserName = event.getUserName();
        mAvatarUrl = event.getAvatar();
    }


}
