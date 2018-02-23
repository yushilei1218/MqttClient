package com.shileiyu.mqttclient.common.third;

import android.content.Context;

import com.shileiyu.mqttclient.common.base.BaseApp;
import com.shileiyu.mqttclient.common.bean.User;
import com.shileiyu.mqttclient.common.util.Util;
import com.shileiyu.mqttclient.ui.third.msg.IMMessage;
import com.shileiyu.mqttclient.ui.third.msg.MqttListener;
import com.shileiyu.mqttclient.ui.third.msg.MqttListenerCenter;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.List;

/**
 * @author shilei.yu
 * @since on 2018/2/23.
 */

public class MqttStub {

    private static MqttStub instance;

    private MqttAndroidClient mClient;

    private final MqttConnectOptions mOptions;

    private MqttStub(String url) {
        mClient = new MqttAndroidClient(BaseApp.AppContext, url, Util.getClinetId());
        mClient.setCallback(new MqttCallback() {
            @Override
            public void connectionLost(Throwable cause) {
                Util.showLog(cause.getMessage());
                List<MqttListener> listeners = MqttListenerCenter.get();
                for (MqttListener l : listeners) {
                    l.connectionLost(cause);
                }
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                Util.showLog("deliveryComplete");
                List<MqttListener> listeners = MqttListenerCenter.get();
                for (MqttListener l : listeners) {
                    l.messageArrived(topic, message);
                }
            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {
                Util.showLog("deliveryComplete");
                List<MqttListener> listeners = MqttListenerCenter.get();
                for (MqttListener l : listeners) {
                    l.deliveryComplete(token);
                }
            }
        });
        mOptions = new MqttConnectOptions();

        //设置是否清空session,这里如果设置为false表示服务器会保留客户端的连接记录，这里设置为true表示每次连接到服务器都以新的身份连接
        mOptions.setCleanSession(true);
        //设置超时连接
        mOptions.setConnectionTimeout(10);
        //设置心跳间隔
        mOptions.setKeepAliveInterval(20);
    }

    public static synchronized MqttStub instance() {
        if (instance == null) {
            instance = new MqttStub("tcp://10.2.2.205:61613");
        }
        return instance;
    }

    public void setUser(User user) {
        mOptions.setUserName(user.name);
        mOptions.setPassword(user.psw.toCharArray());
    }

    public void connect(final IMqttActionListener listener) {
        try {
            mClient.connect(mOptions, this, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    String msg = "connect onSuccess";
                    Util.showLog(msg);
                    if (listener != null) {
                        listener.onSuccess(asyncActionToken);
                    }
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    String msg = "connect onFailure" + exception.getMessage();
                    Util.showLog(msg);
                    if (listener != null) {
                        listener.onFailure(asyncActionToken, exception);
                    }
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
            if (listener != null) {
                listener.onFailure(null, e);
            }
        }
    }

    public void publish(Context context, IMMessage msg, IMqttActionListener listener) {
        try {
            mClient.publish(msg.topic, msg, context, listener);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    public void disConnect() {
        if (mClient.isConnected()) {
            try {
                mClient.disconnect();
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }
    }

    public MqttAndroidClient getClient() {
        return mClient;
    }

    public boolean isConnected() {
        return mClient.isConnected();
    }
}
