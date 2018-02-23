package com.shileiyu.mqttclient.common.util;

import android.net.ConnectivityManager;
import android.provider.Settings;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.shileiyu.mqttclient.common.base.BaseApp;
import com.shileiyu.mqttclient.common.bean.User;

import static android.content.Context.CONNECTIVITY_SERVICE;

/**
 * @author shilei.yu
 * @since on 2018/2/22.
 */

public class Util {
    public static boolean isOnLine() {
        ConnectivityManager cm = (ConnectivityManager) BaseApp.AppContext.getSystemService(CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null &&
                cm.getActiveNetworkInfo().isAvailable() &&
                cm.getActiveNetworkInfo().isConnected();
    }

    public static String getClinetId() {
        return Settings.Secure.getString(BaseApp.AppContext.getContentResolver(), Settings.Secure.ANDROID_ID);
    }

    public static String toJson(Object t) {
        return new Gson().toJson(t);
    }

    public static <T> T toObj(String json, Class<T> c) {
        return new Gson().fromJson(json, c);
    }

    public static boolean isLogin() {
        User user = SpUtil.get(Constant.USER, User.class);
        return user != null && user.isValid();
    }

    public static void showLog(String msg) {
        msg = "Thread=" + Thread.currentThread().getName() + " " + msg;
        Log.d("MqttStub", msg);
        Toast.makeText(BaseApp.AppContext, msg, Toast.LENGTH_SHORT).show();
    }
}
