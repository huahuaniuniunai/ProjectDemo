package com.example.projectdemo.mvp;

import android.content.Context;

import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;

/**
 * User: wangjian
 * Date: 2020/10/29
 * Time: 5:29 PM
 */
public interface LifecycleView extends LifecycleObserver {

//    default void registeredLifecycle(Context context) {
//        if (context != null && context instanceof FragmentActivity) {
//            FragmentActivity activity = (FragmentActivity) context;
//            if (activity != null && !activity.isFinishing()) {
//                activity.getLifecycle().addObserver(this);
//            }
//        }
//    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    default void onLifeStart() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    default void onLifeResume() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    default void onLifePause() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    default void onLifeStop() {

    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    default void onLifeDestroy() {

    }
}

