package com.zoopark.zooparkmvp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.zoopark.lib.BaseActivity;
import com.zoopark.lib.BaseApplication;


public class AnalyseActivity extends BaseActivity<AnalysePresenter> implements AnalyseContract.View {

    @Override
    public void initComponent() {
        DaggerAnalyseComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(((BaseApplication) this.getApplicationContext()).getAppComponent())
                .analyseModule(new AnalyseModule(this))
                .build()
                .inject(this);
    }

    @Override
    public int initView(@Nullable Bundle savedInstanceState) {
        return R.layout.activity_main; //如果你不需要框架帮你设置 setContentView(id) 需要自行设置,请返回 0
    }


    @Override
    public void initData(@Nullable Bundle savedInstanceState) {

    }


}
