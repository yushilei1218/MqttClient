package com.shileiyu.mqttclient.ui.third;


import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

import com.shileiyu.mqttclient.R;
import com.shileiyu.mqttclient.common.base.BaseActivity;
import com.shileiyu.mqttclient.common.util.Util;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class PahoActivity extends BaseActivity {

    @BindView(R.id.act_paho_host_input)
    EditText mHostInput;
    @BindView(R.id.act_paho_connect)
    Button mConnect;
    private MqttAndroidClient mClient;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_paho;
    }

    @Override
    public void initView() {
    }

    @OnClick(R.id.act_paho_connect)
    public void onViewClicked() {
        String url = mHostInput.getText().toString();
        if (!TextUtils.isEmpty(url)) {
            connect(url);
        }
    }

    private void connect(String serverURI) {
        mClient = new MqttAndroidClient(this, serverURI, Util.getClinetId());
        try {
            mClient.connect(this, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    String msg = "connect onSuccess";
                    log(msg);
                    showToast(msg);
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    String msg = "connect onFailure";
                    log(msg);
                    showToast(msg);
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        try {
            if (mClient.isConnected()) {
                mClient.disconnect();
            }
        } catch (MqttException e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }
}
