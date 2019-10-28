package com.zoopark.demo.base;

import android.app.Activity;
import android.os.Bundle;

import com.zoopark.lib.app.IActivityLifecycle;

/**
 * 用户在全局 Activity 生命周期中的额外配置
 */
public class ActivityLifecycle implements IActivityLifecycle {

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        // Do anything you want
    }

    @Override
    public void onActivityStarted(Activity activity) {
        // Do anything you want
    }

    @Override
    public void onActivityResumed(Activity activity) {
        // Do anything you want
    }

    @Override
    public void onActivityPaused(Activity activity) {
        // Do anything you want
    }

    @Override
    public void onActivityStopped(Activity activity) {
        // Do anything you want
    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        // Do anything you want
    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        // Do anything you want
    }
}
