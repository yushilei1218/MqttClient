package com.shileiyu.mqttclient.common.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.IntDef;

import com.ibm.mqtt.IMqttClient;
import com.ibm.mqtt.MqttClient;
import com.ibm.mqtt.MqttException;
import com.ibm.mqtt.MqttSimpleCallback;

public class MqttService extends Service implements IMqttImpl {
    private static final String TAG = "MqttService";

    private IMqttClient mClient;

    public MqttService() {
    }

    @Override
    public void onCreate() {

        //host为主机名，test为clientid即连接MQTT的客户端ID，一般以客户端唯一标识符表示，MemoryPersistence设置clientid的保存形式，默认为以内存保存
        try {

            mClient = MqttClient.createMqttClient("host", new MemoryPersistence());
            //设置回调
            mClient.registerSimpleHandler(new MqttSimpleCallback() {

                @Override
                public void connectionLost() throws Exception {

                }

                @Override
                public void publishArrived(String s, byte[] bytes, int i, boolean b) throws Exception {

                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        return new MqttController();
    }

    private final class MqttController extends Binder {

        IMqttImpl get() {
            return MqttService.this;
        }
    }
}
