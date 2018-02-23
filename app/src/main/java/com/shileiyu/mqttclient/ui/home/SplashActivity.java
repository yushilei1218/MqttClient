package com.shileiyu.mqttclient.ui.home;


import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.shileiyu.mqttclient.R;
import com.shileiyu.mqttclient.common.base.BaseActivity;
import com.shileiyu.mqttclient.ui.third.PahoActivity;

import butterknife.BindView;
import butterknife.OnClick;


public class SplashActivity extends BaseActivity {

    @BindView(R.id.act_splash_title)
    TextView mTitle1;
    @BindView(R.id.act_splash_title2)
    TextView mTitle2;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
    }


    @OnClick({R.id.act_splash_title, R.id.act_splash_title2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.act_splash_title:
                startActivity(new Intent(this, HomeActivity.class));
                break;
            case R.id.act_splash_title2:
                startActivity(new Intent(this, PahoActivity.class));
                break;
            default:
                break;
        }
        finish();
    }
}
