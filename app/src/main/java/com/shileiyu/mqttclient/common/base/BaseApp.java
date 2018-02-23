package com.shileiyu.mqttclient.common.base;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import com.shileiyu.mqttclient.common.bean.User;
import com.shileiyu.mqttclient.common.third.MqttStub;
import com.shileiyu.mqttclient.common.util.Constant;
import com.shileiyu.mqttclient.common.util.SpUtil;
import com.shileiyu.mqttclient.common.util.Util;

import org.eclipse.paho.android.service.MqttService;

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
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                e.printStackTrace();
            }
        });
//        startService(new Intent(BaseApp.AppContext, MqttService.class));
        boolean login = Util.isLogin();
        if (login) {
            MqttStub stub = MqttStub.instance();
            stub.setUser(SpUtil.get(Constant.USER, User.class));
            stub.connect(null);
        }
    }
}
