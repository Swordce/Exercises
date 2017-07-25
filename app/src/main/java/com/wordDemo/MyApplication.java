package com.wordDemo;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by zwj on 2017/7/25.
 */

public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(this);
    }
}
