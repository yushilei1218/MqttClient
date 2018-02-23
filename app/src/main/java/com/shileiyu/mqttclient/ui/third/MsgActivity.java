package com.shileiyu.mqttclient.ui.third;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.shileiyu.mqttclient.R;
import com.shileiyu.mqttclient.common.adapter.MsgAdapter;
import com.shileiyu.mqttclient.common.base.BaseActivity;
import com.shileiyu.mqttclient.common.mqtt.model.IMessage;
import com.shileiyu.mqttclient.common.third.MqttStub;
import com.shileiyu.mqttclient.common.util.Constant;
import com.shileiyu.mqttclient.common.util.SpUtil;
import com.shileiyu.mqttclient.common.util.Util;
import com.shileiyu.mqttclient.ui.third.msg.IMMessage;
import com.shileiyu.mqttclient.ui.third.msg.MqttListener;
import com.shileiyu.mqttclient.ui.third.msg.MqttListenerCenter;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttMessageListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MsgActivity extends BaseActivity {
    private static final String EXTRA = "EXTRA";
    @BindView(R.id.act_msg_lv)
    ListView mMsgLv;
    @BindView(R.id.act_msg_input)
    EditText mMsgInput;
    @BindView(R.id.act_msg_send)
    TextView mMsgSend;
    private MqttStub mStub;
    private String mTopic;
    private List<IMMessage> mData;
    private MsgAdapter mAdapter;

    public static void invoke(Context context, String topic) {
        Intent intent = new Intent(context, MsgActivity.class);
        intent.putExtra(EXTRA, topic);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_msg;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean b = item.getItemId() == R.id.logout;
        if (b) {
            SpUtil.remove(Constant.USER);
            mStub.disConnect();
            finish();
        }
        return true;
    }

    @Override
    public void initView() {
        mTopic = getIntent().getStringExtra(EXTRA);
        mData = new ArrayList<>();

        mAdapter = new MsgAdapter();
        mAdapter.update(mData);
        mMsgLv.setAdapter(mAdapter);
        mMsgLv.setStackFromBottom(true);
        mStub = MqttStub.instance();
        MqttListenerCenter.add(new MqttListener() {
            @Override
            public void bizMessageArrived(String topic, IMMessage message) {
                refresh(message);
            }

            @Override
            public void connectionLost(Throwable cause) {

            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {

            }
        });
        try {
            mStub.getClient().subscribe(mTopic, 2);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    private void refresh(IMMessage imMessage) {
        mData.add(imMessage);
        mAdapter.notifyDataSetChanged();
        mMsgLv.smoothScrollToPosition(mData.size() - 1);
    }

    @OnClick(R.id.act_msg_send)
    public void onViewClicked() {
        String msg = mMsgInput.getText().toString();
        if (!TextUtils.isEmpty(msg)) {
            mMsgInput.setText(null);

            IMMessage imMsg = new IMMessage(msg.getBytes(), mTopic, true);
            refresh(imMsg);
            boolean connected = mStub.isConnected();
            log("isConnected " + connected + "");

            mStub.publish(this, imMsg, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Util.showLog("publish onSuccess");
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    String message = exception == null ? "" : exception.getMessage();
                    Util.showLog("publish onFailure " + message);
                }
            });
        }
    }
}
