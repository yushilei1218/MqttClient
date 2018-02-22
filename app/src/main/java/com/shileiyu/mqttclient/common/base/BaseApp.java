package com.shileiyu.mqttclient.common.base;

import android.app.Application;
import android.content.Context;

/**
 * @author shilei.yu
 * @since on 2018/2/22.
 */

public class BaseApp extends Application {
    public static Context AppContext;

    @Override
    public void onCreate() {
        super.onCreate();
        AppContext = this;
    }
}
