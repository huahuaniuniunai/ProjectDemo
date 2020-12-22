package com.example.projectdemo.mvp;

import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

import io.reactivex.disposables.CompositeDisposable;

/**
 * User: wangjian
 * Date: 2020/10/29
 * Time: 5:32 PM
 */
public abstract class BasePresenterImpl<V extends BaseView> implements BasePresenter<V> {
    public static final String EXTRA_DATA = "t_extra_data";
    public static final String EXTRA_RESULT = "t_extra_result";

    private CompositeDisposable mCompositeDisposable;

    private V mView;

    public BasePresenterImpl(@NonNull V v) {
        attachView(v);
        mView.setPresenter(this);
    }

    @Override
    public void attachView(@NonNull V v) {
        mView = v;
    }

    @Override
    public void detachView() {
        mView = null;
    }

    @Override
    public boolean isViewAttached() {
        return mView != null;
    }

    @Override
    public V getView() {
        return mView;
    }

    @Override
    public Activity getActivity() {
        if (mView == null) {
            return null;
        }
        if (mView instanceof Fragment) {
            return ((Fragment) mView).getActivity();
        } else if (mView instanceof FragmentActivity) {
            return (FragmentActivity) mView;
        } else if (mView instanceof Activity) {
            return (Activity) mView;
        } else {
            return null;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        //call in activity#onSaveInstanceState
    }

    @Override
    public void onResume(@NonNull LifecycleOwner owner) {
        //callback of activity life is onResume
    }

    @Override
    public void onPause(@NonNull LifecycleOwner owner) {
        //callback of activity life is onPause
    }

    @Override
    public void onStop(@NonNull LifecycleOwner owner) {
        //callback of activity life is onStop
    }

    @Override
    public void release() {
        //call in activity#onDestroy
        detachView();

    }

    @Override
    public void onLifecycleChanged(@NonNull LifecycleOwner owner, @NonNull Lifecycle.Event event) {
        //callback of activity life is changed
    }

    @Override
    public void setCompositeDisposable(CompositeDisposable compositeDisposable) {
        mCompositeDisposable = compositeDisposable;
    }

    public CompositeDisposable getCompositeDisposable() {
        return mCompositeDisposable;
    }

    @Override
    public boolean isAfterInitDoStart() {
        return true;
    }

}
