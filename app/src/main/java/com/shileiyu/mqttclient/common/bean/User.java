package com.shileiyu.mqttclient.common.bean;

import android.text.TextUtils;

/**
 * @author shilei.yu
 * @since on 2018/2/23.
 */

public class User {
    public String name;
    public String psw;

    public User(String name, String psw) {
        this.name = name;
        this.psw = psw;
    }

    public boolean isValid() {
        return (!TextUtils.isEmpty(name)) && (!TextUtils.isEmpty(psw));
    }
}
