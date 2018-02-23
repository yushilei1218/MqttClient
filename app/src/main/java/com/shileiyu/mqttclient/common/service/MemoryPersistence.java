package com.shileiyu.mqttclient.common.service;

import com.ibm.mqtt.MqttPersistence;
import com.ibm.mqtt.MqttPersistenceException;

/**
 * @author shilei.yu
 * @since on 2018/2/22.
 */

public class MemoryPersistence implements MqttPersistence {
    @Override
    public void open(String s, String s1) throws MqttPersistenceException {

    }

    @Override
    public void close() {

    }

    @Override
    public void reset() throws MqttPersistenceException {

    }

    @Override
    public byte[][] getAllSentMessages() throws MqttPersistenceException {
        return new byte[0][];
    }

    @Override
    public byte[][] getAllReceivedMessages() throws MqttPersistenceException {
        return new byte[0][];
    }

    @Override
    public void addSentMessage(int i, byte[] bytes) throws MqttPersistenceException {

    }

    @Override
    public void updSentMessage(int i, byte[] bytes) throws MqttPersistenceException {

    }

    @Override
    public void delSentMessage(int i) throws MqttPersistenceException {

    }

    @Override
    public void addReceivedMessage(int i, byte[] bytes) throws MqttPersistenceException {

    }

    @Override
    public void delReceivedMessage(int i) throws MqttPersistenceException {

    }
}
