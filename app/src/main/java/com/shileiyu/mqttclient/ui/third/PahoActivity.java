package com.shileiyu.mqttclient.ui.third;


import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.shileiyu.mqttclient.R;
import com.shileiyu.mqttclient.common.base.BaseActivity;
import com.shileiyu.mqttclient.common.util.Util;
import com.shileiyu.mqttclient.ui.home.SplashActivity;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.nio.charset.Charset;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class PahoActivity extends BaseActivity {

    @BindView(R.id.act_paho_host_input)
    EditText mHostInput;
    @BindView(R.id.act_paho_connect)
    Button mConnect;
    @BindView(R.id.act_paho_host_input2)
    EditText mInput2;
    @BindView(R.id.act_paho_publish)
    Button mPublish;
    @BindView(R.id.act_paho_tv)
    TextView mMsgTv;
    @BindView(R.id.act_paho_host_input3)
    EditText mSubscribeInput;
    @BindView(R.id.act_paho_subscribe)
    Button mSubscribe;

    private MqttAndroidClient mClient;

    Handler mHandler = new Handler();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_paho;
    }

    @Override
    public void initView() {
        mHostInput.setText("tcp://10.2.2.205:61613");
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

    @OnClick({R.id.act_paho_connect, R.id.act_paho_publish,R.id.act_paho_subscribe})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.act_paho_connect:
                connect();
                break;
            case R.id.act_paho_publish:
                publish();
                break;
            case R.id.act_paho_subscribe:
                subscribe();
                break;
            default:
                break;
        }

    }

    private void subscribe() {
        String s = mSubscribeInput.getText().toString();
        if (TextUtils.isEmpty(s)) {
            return;
        }
        if (mClient.isConnected()) {
            try {
                mClient.subscribe(s, 2, this, new IMqttActionListener() {
                    @Override
                    public void onSuccess(IMqttToken asyncActionToken) {
                        String msg = "subscribe onSuccess";
                        showLog(msg);
                    }

                    @Override
                    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                        String msg = "subscribe onFailure " + exception.getMessage();
                        showLog(msg);
                    }
                }, new IMqttMessageListener() {
                    @Override
                    public void messageArrived(String topic, MqttMessage message) throws Exception {
                        final String msg = "subscribe messageArrived topic= " + topic + " MqttMessage=" + message.toString();

                        showLog(msg);
                        mHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                mMsgTv.setText(msg);
                            }
                        });
                    }
                });
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }
    }

    private void connect() {
        String url = mHostInput.getText().toString();
        if (TextUtils.isEmpty(url)) {
            return;
        }
        mClient = new MqttAndroidClient(this, url, Util.getClinetId());
        try {

            MqttConnectOptions options = new MqttConnectOptions();
            options.setUserName(SplashActivity.useName);
            options.setPassword(SplashActivity.pw.toCharArray());
            options.setKeepAliveInterval(5);

            mClient.connect(options, this, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    String msg = "connect onSuccess";
                    showLog(msg);
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    String msg = "connect onFailure" + exception.getMessage();
                    showLog(msg);
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    private void publish() {
        String s = mInput2.getText().toString();
        if (TextUtils.isEmpty(s)) {
            return;
        }
        if (mClient.isConnected()) {
            byte[] bytes = s.getBytes(Charset.forName("UTF-8"));
            MqttMessage message = new MqttMessage();
            message.setPayload(bytes);
            try {
                mClient.publish("topic1", message, this, new IMqttActionListener() {
                    @Override
                    public void onSuccess(IMqttToken asyncActionToken) {
                        String msg = "publish onSuccess ";
                        showLog(msg);
                    }

                    @Override
                    public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                        String msg = "publish onFailure " + exception.getMessage();
                        showLog(msg);
                    }
                });
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }
    }

    private void showLog(String msg) {
        log(msg);
        showToast(msg);
    }
}
