package com.example.projectdemo.view.progressbar.loading;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.projectdemo.R;
import com.example.projectdemo.mvp.BaseActivity;
import com.gyf.immersionbar.ImmersionBar;

public class LoadingViewActivity extends BaseActivity {
    LoadingView loading1;
    LoadingView loading2;
    LoadingView loading3;
    ValueAnimator valueAnimator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading_view);
        loading1 = findViewById(R.id.loading1);
        loading2 = findViewById(R.id.loading2);
        loading3 = findViewById(R.id.loading3);

        valueAnimator = ValueAnimator.ofFloat(0, 100);
        valueAnimator.setDuration(2000);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                loading1.setPercent(animation.getAnimatedFraction());
            }
        });
        loading1.post(new Runnable() {
            @Override
            public void run() {
                valueAnimator.start();
            }
        });
    }

    @Override
    protected void setBar() {
        super.setBar();
        ImmersionBar.with(this)
                .titleBar(R.id.down_refresh)
                .navigationBarColor(R.color.page)
                .init();
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, LoadingViewActivity.class);
        context.startActivity(intent);
    }
}
