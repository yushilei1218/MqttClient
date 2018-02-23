package com.shileiyu.mqttclient.common.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.shileiyu.mqttclient.common.base.BaseApp;

/**
 * @author shilei.yu
 * @since on 2018/2/23.
 */

public class SpUtil {
    private static final String SP = "sp";

    public static void save(String key, Object t) {
        SharedPreferences.Editor edit = getSp().edit();
        edit.putString(key, Util.toJson(t));
        edit.apply();
    }

    public static <T> T get(String key, Class<T> c) {
        String json = getSp().getString(key, null);
        return Util.toObj(json, c);
    }

    public static void remove(String key) {
        getSp().edit().remove(key).apply();
    }

    private static synchronized SharedPreferences getSp() {
        return BaseApp.AppContext.getSharedPreferences(SP, Context.MODE_PRIVATE);
    }
}
