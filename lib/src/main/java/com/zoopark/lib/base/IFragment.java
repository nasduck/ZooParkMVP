package com.zoopark.lib.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

public interface IFragment {

    void initComponent();

    int initView(@Nullable Bundle savedInstanceState);

    void initData(@Nullable Bundle savedInstanceState);

    boolean useEventBus();
}
