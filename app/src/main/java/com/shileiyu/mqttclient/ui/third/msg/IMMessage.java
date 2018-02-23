package com.shileiyu.mqttclient.ui.third.msg;

import org.eclipse.paho.client.mqttv3.MqttMessage;

/**
 * @author shilei.yu
 * @since on 2018/2/23.
 */

public class IMMessage extends MqttMessage {
    public String topic;
    public boolean isMe = false;

    public IMMessage() {
    }

    public IMMessage(byte[] payload, String topic, boolean isMe) {
        super(payload);
        this.topic = topic;
        this.isMe = isMe;
    }

    public static IMMessage get(MqttMessage msg) {
        IMMessage message = new IMMessage();
        message.setPayload(msg.getPayload());
        message.setDuplicate(msg.isDuplicate());
        message.setId(msg.getId());
        message.setQos(msg.getQos());
        return message;
    }
}
