package com.shileiyu.mqttclient.ui.home;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.shileiyu.mqttclient.R;
import com.shileiyu.mqttclient.common.base.BaseActivity;
import com.shileiyu.mqttclient.ui.third.PahoActivity;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
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
        AndPermission.with(this)
                .permission("android.permission.WAKE_LOCK")
                .requestCode(1)
                .callback(new PermissionListener() {

                    @Override
                    public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
                        mTitle2.setVisibility(View.VISIBLE);
                    }

                    @Override
                    public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {
                        mTitle2.setVisibility(View.GONE);
                    }
                });
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
