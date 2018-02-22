package com.shileiyu.mqttclient.ui.home;


import android.content.Intent;
import android.widget.TextView;

import com.shileiyu.mqttclient.R;
import com.shileiyu.mqttclient.common.base.BaseActivity;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SplashActivity extends BaseActivity {


    @BindView(R.id.act_splash_title)
    TextView mTitleTv;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        Observable.intervalRange(0, 4, 0, 1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        log(aLong + "");
                        String msg = "欢迎启动Mqtt " + aLong + " s";
                        mTitleTv.setText(msg);
                        if (aLong == 3) {
                            startActivity(new Intent(getActivityContext(), HomeActivity.class));
                            finish();
                        }
                    }
                });
    }
}
