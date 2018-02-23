package com.shileiyu.mqttclient.ui.third.msg;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shilei.yu
 * @since on 2018/2/23.
 */

public class MqttListenerCenter {
    private final static List<MqttListener> data = new ArrayList<>();

    public static synchronized void add(MqttListener listener) {
        if (!data.contains(listener)) {
            data.add(listener);
        }
    }

    public static synchronized void remove(MqttListener listener) {
        data.remove(listener);
    }

    public static synchronized List<MqttListener> get() {
        return data;
    }
}
