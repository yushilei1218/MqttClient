package com.shileiyu.mqttclient.common.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.widget.Toast;

import butterknife.ButterKnife;

/**
 * @author shilei.yu
 * @since on 2018/2/22.
 */

public abstract class BaseActivity extends AppCompatActivity implements IBaseView {
    /**
     * 用于存储 或查询 当前Activity findViewById() 查找过的View
     */
    private SparseArray<View> mViews = new SparseArray<>();

    private OperateViewHolder mHolder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getLayoutId());

        ButterKnife.bind(this);

        initOperateView();

        initView();

        initData();
    }

    @Override
    @SuppressWarnings("unchecked")
    public <T extends View> T findView(int rid) {
        View view = mViews.get(rid);
        if (view == null) {
            view = findViewById(rid);
            mViews.append(rid, view);
        }
        return (T) view;
    }

    @LayoutRes
    protected abstract int getLayoutId();

    @Override
    public void initData() {

    }

    protected void setOnClick(View v) {
        v.setOnClickListener(this);
    }

    protected void setOnClick(View... views) {
        for (View v : views) {
            setOnClick(v);
        }
    }

    protected void setOnClick(@IdRes int rid) {
        findView(rid).setOnClickListener(this);
    }

    protected void setOnClick(@IdRes int... rids) {
        for (int rid : rids) {
            setOnClick(rid);
        }
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public String getTAG() {
        String name = this.getClass().getSimpleName();
        if (name.length() > 23) {
            name = name.substring(0, 23);
        }
        return name;
    }

    @Override
    public Activity getActivityContext() {
        return this;
    }

    @Override
    public void showDialog(AlertDialog dialog) {
        dialog.show();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(BaseApp.AppContext, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void initOperateView() {
        mHolder = new OperateViewHolder(this);
    }

    @Override
    public void showLoading() {
        mHolder.showLoading();
    }

    @Override
    public void hideLoading() {
        mHolder.hideLoading();
    }

    @Override
    public void showLoadingDialog() {
        mHolder.showLoadingDialog();
    }

    @Override
    public void hideLoadingDialog() {
        mHolder.hideLoadingDialog();
    }

    @Override
    public void onError(int imageId, String msg, String btnText, View.OnClickListener onClickListener) {
        mHolder.onError(imageId, msg, btnText, onClickListener);
    }

    public void log(String msg) {
        Log.d(getTAG(), msg);
    }
}
