package com.zoopark.lib;

import com.zoopark.lib.ok.BasePresenter;
import com.zoopark.lib.ok.IModel;
import com.zoopark.lib.repository.IRepositoryManager;

public class BaseModel implements IModel {

    protected IRepositoryManager mRepositoryManager;

    public BaseModel(IRepositoryManager repositoryManager) {
        this.mRepositoryManager = repositoryManager;
    }

    /**
     * 在框架中 {@link BasePresenter#onDestroy()} 时会默认调用 {@link IModel#onDestroy()}
     */
    @Override
    public void onDestroy() {
        mRepositoryManager = null;
    }
}
