package com.example.projectdemo.mvp;

import android.content.Intent;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModel;

/**
 * User: wangjian
 * Date: 2020/10/29
 * Time: 5:30 PM
 */
public interface BaseView <V extends BasePresenter> {
    /**
     * 设置P
     */
    void setPresenter(V presenter);

    /**
     * MVP是否有效
     */
    boolean isMvpValid();

    /**
     * 获取当前的生命周期
     */
    Lifecycle.State getCurrentLifeState();

    /**
     * 获取ViewModel
     */
    @NonNull
    @MainThread
    ViewModel getViewModel(@NonNull Class modelClass);

    /**
     * Toast
     */
    void toast(String msg, int duration);

    /**
     * Toast
     */
    void toast(String msg);

    /**
     * {@link android.app.Activity#startActivity(Intent)}
     */
    void startActivity(Intent intent);

    /**
     * {@link android.app.Activity#startActivityForResult(Intent, int)}
     */
    @Deprecated
    void startActivityForResult(Intent intent, int reqCode);
}

