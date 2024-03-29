package com.zoopark.demo.user.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bumptech.glide.Glide;
import com.zoopark.demo.R;
import com.zoopark.demo.base.event.AddFavoriteUserEvent;
import com.zoopark.demo.user.contract.GithubUserInfoContract;
import com.zoopark.demo.user.di.component.DaggerGithubUserInfoComponent;
import com.zoopark.demo.user.di.module.GithubUserInfoModule;
import com.zoopark.demo.user.model.entity.GithubUserBean;
import com.zoopark.demo.user.presenter.GithubUserInfoPresenter;
import com.zoopark.lib.app.ZooApplication;
import com.zoopark.lib.base.impl.BaseActivity;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

import static com.zoopark.demo.base.RouterCenter.GithubUser.GITHUB_USER_INFO;

@Route(path = GITHUB_USER_INFO)
public class GithubUserInfoActivity extends BaseActivity<GithubUserInfoPresenter>
        implements GithubUserInfoContract.View {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.iv_avatar)
    ImageView mIvAvatar;
    @BindView(R.id.tv_name)
    TextView mTvName;

    @Autowired(name = "UserName")
    public String mUserName;
    private String mAvatarUrl;

    private String mPersonalUrl;

    @Override
    public void initComponent() {
        DaggerGithubUserInfoComponent
                .builder()
                .appComponent(((ZooApplication)this.getApplicationContext()).getAppComponent())
                .githubUserInfoModule(new GithubUserInfoModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_github_user_info;
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        ARouter.getInstance().inject(this);
        setSupportActionBar(mToolbar);

        mPresenter.callGetGithubUserInfo(mUserName);
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
    public void configUI(GithubUserBean userInfo) {
        Glide.with(this).load(userInfo.getAvatarUrl()).into(mIvAvatar);
        mTvName.setText(userInfo.getLogin());
        mAvatarUrl = userInfo.getAvatarUrl();
        mPersonalUrl = userInfo.getHtmlUrl();
    }

    @OnClick(R.id.layout_personal)
    public void onPersonalClick() {
        Intent intent = new Intent();
        Uri uri = Uri.parse(mPersonalUrl);
        intent.setData(uri);
        startActivity(intent);
    }

    @OnClick(R.id.btn_like)
    public void onLikeClick() {
        Toast.makeText(this, getResources().getText(R.string.add_favorite_tip), Toast.LENGTH_SHORT).show();
        EventBus.getDefault().post(new AddFavoriteUserEvent(mUserName, mAvatarUrl));
    }
}
