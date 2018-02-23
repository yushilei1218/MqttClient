package com.shileiyu.mqttclient.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import com.shileiyu.mqttclient.R;
import com.shileiyu.mqttclient.common.base.BaseActivity;
import com.shileiyu.mqttclient.common.bean.User;
import com.shileiyu.mqttclient.common.third.MqttStub;
import com.shileiyu.mqttclient.common.util.Constant;
import com.shileiyu.mqttclient.common.util.SpUtil;
import com.shileiyu.mqttclient.common.util.Util;
import com.shileiyu.mqttclient.ui.home.SplashActivity;

import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttToken;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {


    @BindView(R.id.act_login_pw)
    EditText mPw;
    @BindView(R.id.act_login_name)
    EditText mName;
    @BindView(R.id.act_login_btn)
    TextView mLoginBtn;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (Util.isLogin()) {
            startActivity(new Intent(this, SplashActivity.class));
            finish();
        }
    }

    @Override
    public void initView() {

    }

    @OnClick(R.id.act_login_btn)
    public void onViewClicked() {
        String name = mName.getText().toString();
        if (TextUtils.isEmpty(name)) {
            showToast("name null");
            return;
        }
        String psw = mPw.getText().toString();
        if (TextUtils.isEmpty(psw)) {
            showToast("psw null");
            return;
        }
        User user = new User(name, psw);
        SpUtil.save(Constant.USER, user);
        showLoadingDialog();
        MqttStub stub = MqttStub.instance();
        stub.setUser(user);
        stub.connect(new IMqttActionListener() {
            @Override
            public void onSuccess(IMqttToken asyncActionToken) {
                hideLoadingDialog();
                startActivity(new Intent(LoginActivity.this, SplashActivity.class));
                finish();
            }

            @Override
            public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                hideLoadingDialog();
            }
        });
    }
}
