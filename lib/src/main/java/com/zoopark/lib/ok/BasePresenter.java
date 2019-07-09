package com.zoopark.lib.ok;

import org.greenrobot.eventbus.EventBus;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class BasePresenter<M extends IModel, V extends IView> implements IPresenter {

    protected final String TAG = this.getClass().getSimpleName();
    protected CompositeDisposable mCompositeDisposable;

    protected M mModel;
    protected V mRootView;

    public BasePresenter(V rootView) {
        this.mRootView = rootView;
        onCreate();
    }

    public BasePresenter(M model, V rootView) {
        this.mModel = model;
        this.mRootView = rootView;
        onCreate();
    }

    @Override
    public void onCreate() {

        // Register EventBus
        if (useEventBus()) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onDestroy() {

        // Destroy Model
        if (mModel != null) {
            mModel.onDestroy();
            mModel = null;
        }

        mRootView = null;

        if (mCompositeDisposable != null) {
            unDispose();
            mCompositeDisposable = null;
        }

        // Unregister EventBus
        if (useEventBus())
            EventBus.getDefault().unregister(this);
    }

    @Override
    public boolean useEventBus() {
        return false;
    }

    public void addDispose(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        mCompositeDisposable.add(disposable);
    }

    public void unDispose() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();// 保证 Activity 结束时取消所有正在执行的订阅
        }
    }
}
