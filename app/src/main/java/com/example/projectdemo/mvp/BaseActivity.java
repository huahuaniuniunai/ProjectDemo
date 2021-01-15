package com.example.projectdemo.mvp;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

//import com.ctq.eqc.util.avoid.AvoidOnResult;
//import com.example.projectdemo.statusbar.StatusBarHelper;

import com.example.projectdemo.R;
import com.example.projectdemo.lztx.statusbar.StatusBarHelper;
import com.example.projectdemo.util.activity.ActivityCollector;
import com.gyf.immersionbar.ImmersionBar;

import io.reactivex.disposables.CompositeDisposable;

/**
 * User: wangjian
 * Date: 2020/10/29
 * Time: 5:28 PM
 */
public class BaseActivity<V extends BasePresenter> extends FragmentActivity {
    private static final String TAG = "BaseActivity";
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
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation();
        onCreateFromMvp();
//        initCompositeDisposable();
        initMvpAfterOnCreate(savedInstanceState);
        Log.d(TAG, "当前执行的Activity:" + getClass().getSimpleName());

        ActivityCollector.addActivity(this);
    }

    /**
     * 使用MVP方式专用,用来替代{@link BaseActivity#onCreate(Bundle)}
     */
    protected V onCreateFromMvp() {
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
        Toast.makeText(this, msg, duration).show();
    }

//    @NonNull
//    @MainThread
//    public ViewModel getViewModel(@NonNull Class modelClass) {
//        return new ViewModelProvider(this).get(modelClass);
//    }

//    private void initCompositeDisposable() {
//        if (isMvpValid()) {
//            getPresenter().setCompositeDisposable(getCompositeDisposable());
//        }
//    }

//    public CompositeDisposable getCompositeDisposable() {
//        if (mCompositeDisposable == null) {
//            mCompositeDisposable = new CompositeDisposable();
//        }
//        return mCompositeDisposable;
//    }

    private void disposeCompositeDisposable() {
        if (mCompositeDisposable != null) {
            mCompositeDisposable.dispose();
        }
        mCompositeDisposable = null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        disposeCompositeDisposable();
        ActivityCollector.removeActivity(this);
    }

    public Activity getActivity() {
        return this;
    }

//    public AvoidOnResult getActivityForResult() {
//        if (mActivityForResult == null) {
//            mActivityForResult = new AvoidOnResult(this);
//        }
//        return mActivityForResult;
//    }

    /**
     * 解决android8.0 java.lang.IllegalStateException:
     * Only fullscreen opaque activities can request orientation
     */
    protected void setRequestedOrientation() {
        // fixing portrait mode problem for SDK 26 if using windowIsTranslucent = true
        if (Build.VERSION.SDK_INT != Build.VERSION_CODES.O) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        // 设置导航栏
        setNavigationBar();

        // 设置状态栏
        setStatusBar();
    }

    /**
     * 设置头部状态栏
     */
    protected void setStatusBar() {
        StatusBarHelper.setStatusBar(this, StatusBarHelper.VZStatusBarType.BAR_TYPE_NO);
    }

    /**
     * 设置底部导航栏
     */
    protected void setNavigationBar() {
        ImmersionBar.with(this).init();
    }

    public void onBackButtonClick(View v) {
        finish();
    }


    public boolean isActivityValid() {
        return !this.isFinishing();
    }

}
