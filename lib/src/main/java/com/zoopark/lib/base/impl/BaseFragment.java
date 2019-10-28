package com.zoopark.lib.base.impl;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.zoopark.lib.base.IFragment;
import com.zoopark.lib.mvp.IPresenter;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseFragment<P extends IPresenter> extends Fragment implements IFragment {

    protected final String TAG = this.getClass().getSimpleName();

    private Unbinder mUnbinder;

    @Inject
    @Nullable
    protected P mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Register EventBus
        if (useEventBus()){
            EventBus.getDefault().register(this);
        }

        initComponent();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = null;
        try {
            int layoutResID = initView(savedInstanceState);
            if (layoutResID != 0) {
                view = inflater.inflate(layoutResID, container, false);

                // Bind ButterKnife
                mUnbinder = ButterKnife.bind(BaseFragment.this, view);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        // Unbind ButterKnife
        if (mUnbinder != null && mUnbinder != Unbinder.EMPTY) {
            mUnbinder.unbind();
            mUnbinder = null;
        }

        // Unregister EventBus
        if (useEventBus())
            EventBus.getDefault().unregister(this);

        if (mPresenter != null) {
            mPresenter.onDestroy();
            mPresenter = null;
        }
    }

    /**
     * By default, EventBus is not used
     *
     * @return
     */
    @Override
    public boolean useEventBus() {
        return false;
    }

}