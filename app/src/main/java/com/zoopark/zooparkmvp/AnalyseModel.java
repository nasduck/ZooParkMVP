package com.zoopark.zooparkmvp;


import com.zoopark.lib.BaseModel;
import com.zoopark.lib.common.Resp;
import com.zoopark.lib.inject.scope.ActivityScope;
import com.zoopark.lib.repository.IRepositoryManager;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;


@ActivityScope
public class AnalyseModel extends BaseModel implements AnalyseContract.Model {

    @Inject
    public AnalyseModel(IRepositoryManager repositoryManager) {
        super(repositoryManager);
    }

}