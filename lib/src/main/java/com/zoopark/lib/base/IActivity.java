package com.zoopark.lib.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

public interface IActivity {

    void initComponent();

    /**
     * Init View
     *
     * @param savedInstanceState
     * @return
     */
    int initView(@Nullable Bundle savedInstanceState);

    /**
     * Init Data
     *
     * @param savedInstanceState
     */
    void initData(@Nullable Bundle savedInstanceState);

    /**
     * whether to use EventBus
     *
     * @return
     */
    boolean useEventBus();
}
