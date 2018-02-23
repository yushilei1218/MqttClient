package com.shileiyu.mqttclient.common.util;

import android.net.ConnectivityManager;
import android.provider.Settings;

import com.shileiyu.mqttclient.common.base.BaseApp;

import static android.content.Context.CONNECTIVITY_SERVICE;

/**
 * @author shilei.yu
 * @since on 2018/2/22.
 */

public class Util {
    public static boolean isOnLine() {
        {
            ConnectivityManager cm = (ConnectivityManager) BaseApp.AppContext.getSystemService(CONNECTIVITY_SERVICE);
            return cm.getActiveNetworkInfo() != null &&
                    cm.getActiveNetworkInfo().isAvailable() &&
                    cm.getActiveNetworkInfo().isConnected();
        }
    }

    public static String getClinetId() {
        return Settings.Secure.getString(
                BaseApp.AppContext.getContentResolver(), Settings.Secure.ANDROID_ID);
    }
}
