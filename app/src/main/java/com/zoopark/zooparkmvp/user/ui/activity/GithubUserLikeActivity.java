package com.zoopark.zooparkmvp.user.ui.activity;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.google.gson.annotations.SerializedName;
import com.zoopark.lib.BaseActivity;
import com.zoopark.zooparkmvp.R;

import butterknife.BindView;

import static com.zoopark.zooparkmvp.base.RouterCenter.GithubUser.GITHUB_USER_LIKE;

@Route(path = GITHUB_USER_LIKE)
public class GithubUserLikeActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.iv_avatar)
    ImageView mIvAvatar;
    @BindView(R.id.tv_name)
    TextView mTvName;

    @Autowired(name = "userName")
    public String mUserName;
    @Autowired(name = "avatar")
    public String mAvatarUrl;


    @Override
    public void initComponent() {

    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_github_user_like;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        ARouter.getInstance().inject(this);
        setSupportActionBar(mToolbar);

        mTvName.setText(mUserName);
        Glide.with(this).load(mAvatarUrl).into(mIvAvatar);
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



}
