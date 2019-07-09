package com.zoopark.lib.ok;

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
