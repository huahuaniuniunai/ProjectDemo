package com.example.projectdemo.mvp;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

import io.reactivex.disposables.CompositeDisposable;

/**
 * User: wangjian
 * Date: 2020/10/29
 * Time: 5:30 PM
 */
public interface BasePresenter <V extends BaseView> extends LifecycleObserver {
    /**
     * 初始化数据到内存
     */
    void init(Bundle savedInstanceState);

    /**
     * 开始
     */
    void start();

    /**
     * activity因意外死亡，保存数据，比如:内存不足
     */
    void onSaveInstanceState(Bundle outState);

    /**
     * 释放资源
     */
    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void release();

    /**
     * 绑定view
     */
    void attachView(@NonNull V v);

    /**
     * 解绑view
     */
    void detachView();

    /**
     * view是否绑定
     */
    boolean isViewAttached();

    /**
     * 获取view
     */
    V getView();

    /**
     * 获取上下文
     * 在标准的mvp中是不允许有这种相关Android的api存在
     * 但是有时候在处理数据的同时，采用第三方工具类，没有办法必须使用Activity
     */
    Activity getActivity();

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void onResume(@NonNull LifecycleOwner owner);

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    void onPause(@NonNull LifecycleOwner owner);

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    void onStop(@NonNull LifecycleOwner owner);

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    void onLifecycleChanged(@NonNull LifecycleOwner owner, @NonNull Lifecycle.Event event);

    /**
     * 增加RxJava的支持
     */
    void setCompositeDisposable(CompositeDisposable compositeDisposable);

    /**
     * 在init之后是否需要执行start,默认true执行start方法
     */
    boolean isAfterInitDoStart();

}
