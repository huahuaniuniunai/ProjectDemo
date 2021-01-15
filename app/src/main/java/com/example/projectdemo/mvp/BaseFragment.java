package com.example.projectdemo.mvp;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

//import com.ctq.eqc.util.avoid.AvoidOnResult;

import com.example.projectdemo.R;
import com.gyf.immersionbar.ImmersionBar;
import com.gyf.immersionbar.components.ImmersionFragment;

import io.reactivex.disposables.CompositeDisposable;

/**
 * User: wangjian
 * Date: 2020/10/29
 * Time: 5:33 PM
 */
public class BaseFragment<V extends BasePresenter> extends ImmersionFragment {
    public static final String EXTRA_DATA = "t_extra_data";
    public static final String EXTRA_RESULT = "t_extra_result";

//    private AvoidOnResult mActivityForResult;

    private CompositeDisposable mCompositeDisposable;

    private V mPresenter;

    public V getPresenter() {
        return mPresenter;
    }

    public void setPresenter(V p) {
        mPresenter = p;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onCreateFromMvp(view);
        initImmersionBar();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initCompositeDisposable();
        initMvpAfterOnCreate(savedInstanceState);
    }

    /**
     * 使用MVP方式专用,用来替代{@link BaseFragment#onViewCreated(View, Bundle)}
     */
    public V onCreateFromMvp(View view) {
        return null;
    }

    private void initMvpAfterOnCreate(Bundle savedInstanceState) {
        if (mPresenter != null) {
            getLifecycle().addObserver(mPresenter);
            mPresenter.init(savedInstanceState);
            if (mPresenter.isAfterInitDoStart()) {
                mPresenter.start();
            }
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (mPresenter != null) {
            mPresenter.onSaveInstanceState(outState);
        }
    }

    public boolean isMvpValid() {
        return mPresenter != null;
    }

    public Lifecycle.State getCurrentLifeState() {
        return getLifecycle().getCurrentState();
    }

    public void toast(String msg) {
        toast(msg, Toast.LENGTH_SHORT);
    }

    public void toast(String msg, int duration) {
        Toast.makeText(getActivity(), msg, duration).show();
    }

//    @NonNull
//    @MainThread
//    public ViewModel getViewModel(@NonNull Class modelClass) {
//        return new ViewModelProvider(this).get(modelClass);
//    }

    private void initCompositeDisposable() {
        if (isMvpValid()) {
            getPresenter().setCompositeDisposable(getCompositeDisposable());
        }
    }

    public CompositeDisposable getCompositeDisposable() {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }
        return mCompositeDisposable;
    }

    private void disposeCompositeDisposable() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
        mCompositeDisposable = null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disposeCompositeDisposable();
    }

    @Override
    public void initImmersionBar() {
        ImmersionBar.with(this)
                .statusBarColor(R.color.gray)// 设置状态栏颜色
                .statusBarDarkFont(true)// 状态栏字体是深色，不写默认为亮色
                .fitsSystemWindows(true)// 设置解决状态栏和布局重叠问题
                .navigationBarColor(R.color.gray)// 设置导航栏颜色
                .init();
    }

//    public AvoidOnResult getActivityForResult() {
//        if (mActivityForResult == null) {
//            mActivityForResult = new AvoidOnResult(getActivity());
//        }
//        return mActivityForResult;
//    }

}
