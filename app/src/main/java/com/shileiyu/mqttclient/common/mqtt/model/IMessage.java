package com.shileiyu.mqttclient.common.mqtt.model;

/**
 * @author shilei.yu
 * @since on 2018/2/22.
 */

public interface IMessage {
    MsgType getMsgType();

    void setMsgType(MsgType type);

    MsgStatus getMsgStatus();

    void setMsgStatus(MsgStatus status);

    String toJson();

    IAttachment getAttachment();

    void setAttachment(IAttachment attachment);
}
