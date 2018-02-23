package com.shileiyu.mqttclient.common.mqtt.msg;

import com.shileiyu.mqttclient.common.mqtt.model.IAttachment;
import com.shileiyu.mqttclient.common.mqtt.model.IMessage;
import com.shileiyu.mqttclient.common.mqtt.model.MsgStatus;
import com.shileiyu.mqttclient.common.mqtt.model.MsgType;

/**
 * @author shilei.yu
 * @since on 2018/2/22.
 */

public class Message implements IMessage {
    private long id;
    private MsgType type;
    private MsgStatus status;


    @Override
    public MsgType getMsgType() {
        return null;
    }

    @Override
    public void setMsgType(MsgType type) {

    }

    @Override
    public MsgStatus getMsgStatus() {
        return null;
    }

    @Override
    public void setMsgStatus(MsgStatus status) {

    }

    @Override
    public String toJson() {
        return null;
    }

    @Override
    public IAttachment getAttachment() {
        return null;
    }

    @Override
    public void setAttachment(IAttachment attachment) {

    }
}
