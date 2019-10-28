package com.zoopark.lib.mvp;

import android.arch.lifecycle.LifecycleObserver;

public interface IPresenter extends LifecycleObserver {

    void onCreate();
    void onDestroy();

    /**
     * whether to use EventBus
     *
     * @return
     */
    boolean useEventBus();

}
