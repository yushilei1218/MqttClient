package com.shileiyu.mqttclient.common.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.ibm.mqtt.MqttClient;

public class MqttService extends Service {
    public MqttService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MqttController();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    private final class MqttController extends Binder {
        public void conncet() {

        }
    }

    private void connect() {
    }
}
