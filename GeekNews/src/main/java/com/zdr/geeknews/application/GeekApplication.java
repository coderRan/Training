package com.zdr.geeknews.application;

import android.app.Application;

import utils.VolleyUtil;

/**
 * Created by zdr on 16-8-8.
 */
public class GeekApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        VolleyUtil.init(this);

    }
}
