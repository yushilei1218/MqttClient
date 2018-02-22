package com.shileiyu.mqttclient.common.base;

/**
 * @author shilei.yu
 * @since on 2017/7/21.
 */

public interface IBasePresenter {
    void onStart();

    void onDestroy();

    void removeTask();
}
