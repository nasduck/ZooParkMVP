package com.zoopark.zooparkmvp;


import com.zoopark.lib.inject.scope.ActivityScope;
import com.zoopark.lib.ok.BasePresenter;


import javax.inject.Inject;


@ActivityScope
public class AnalysePresenter extends BasePresenter<AnalyseContract.Model, AnalyseContract.View> {

    @Inject
    public AnalysePresenter(AnalyseContract.Model model, AnalyseContract.View rootView) {
        super(model, rootView);

    }


}
