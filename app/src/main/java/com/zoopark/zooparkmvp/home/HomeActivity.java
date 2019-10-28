package com.zoopark.zooparkmvp.home;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.zoopark.lib.BaseActivity;
import com.zoopark.zooparkmvp.R;

import butterknife.BindView;
import butterknife.OnClick;

import static com.zoopark.zooparkmvp.base.RouterCenter.GithubUser.GITHUB_USER_LIST;

public class HomeActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;

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

    @OnClick(R.id.btn_github_user)
    public void onGithubUserClick() {
        ARouter.getInstance()
                .build(GITHUB_USER_LIST)
                .navigation();
    }
}
