package com.shileiyu.mqttclient.ui.third;


import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.shileiyu.mqttclient.R;
import com.shileiyu.mqttclient.common.base.BaseActivity;
import com.shileiyu.mqttclient.common.third.MqttStub;

import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class PahoActivity extends BaseActivity {


    @BindView(R.id.act_paho_topic)
    EditText mTopic;
    @BindView(R.id.act_paho_subscribe)
    Button mSubscribe;

    @Override
    public void initView() {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_paho;
    }

    @OnClick(R.id.act_paho_subscribe)
    public void onViewClicked() {
        String topic = mTopic.getText().toString();
        if (!TextUtils.isEmpty(topic)) {
            MsgActivity.invoke(this, topic);
        }
    }
}
