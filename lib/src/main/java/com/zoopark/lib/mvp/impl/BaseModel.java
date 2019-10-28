package com.zoopark.lib.mvp.impl;

import com.zoopark.lib.mvp.IModel;
import com.zoopark.lib.repository.IRepositoryManager;

public class BaseModel implements IModel {

    protected IRepositoryManager mRepositoryManager;

    public BaseModel(IRepositoryManager repositoryManager) {
        this.mRepositoryManager = repositoryManager;
    }

    /**
     * 在框架中 BasePresenter.onDestroy() 时会默认调用 IModel.onDestroy()
     */
    @Override
    public void onDestroy() {
        mRepositoryManager = null;
    }
}
