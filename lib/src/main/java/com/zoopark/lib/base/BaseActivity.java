package com.zoopark.lib.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.nasduck.rafikipermissions.RafikiPermissions;
import com.zoopark.lib.mvp.IPresenter;

import org.greenrobot.eventbus.EventBus;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity<P extends IPresenter> extends AppCompatActivity implements IActivity {

    protected final String TAG = this.getClass().getSimpleName();

    private Unbinder mUnbinder;

    @Inject
    protected P mPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initComponent();

        try {
            int layoutResID = initView(savedInstanceState);
            if (layoutResID != 0) {
                setContentView(layoutResID);

                // Bind ButterKnife
                mUnbinder = ButterKnife.bind(this);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Register EventBus
        if (useEventBus()){
            EventBus.getDefault().register(this);
        }

        // Init Data
        initData(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
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

    // 权限请求回调
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		RafikiPermissions.getInstance(this).onResult(requestCode, permissions, grantResults);
    }
}
