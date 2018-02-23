package com.shileiyu.mqttclient.ui.third.msg;

import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * @author shilei.yu
 * @since on 2018/2/23.
 */

public abstract class MqttListener implements MqttCallback {


    @Override
    public void messageArrived(String topic, MqttMessage message) throws Exception {
        IMMessage imMessage = IMMessage.get(message);
        imMessage.topic = topic;
        imMessage.isMe = false;
        bizMessageArrived(topic, imMessage);
    }

    public abstract void bizMessageArrived(String topic, IMMessage message);
}
