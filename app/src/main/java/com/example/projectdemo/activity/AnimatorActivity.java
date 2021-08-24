package com.example.projectdemo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.DecelerateInterpolator;

import com.example.projectdemo.R;
import com.example.projectdemo.customview.StepAnimatorView;
import com.example.projectdemo.view.treelist.TreeListActivity;

import java.util.List;

public class AnimatorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animator);

        // 设置动画效果
        setAnimationEffect();
    }

    private void setAnimationEffect() {
        StepAnimatorView stepAnimatorView = findViewById(R.id.step_animator_view);
        stepAnimatorView.setStepMax(4000);// 设置最大值
        // 属性动画
        ValueAnimator valueAnimator = ObjectAnimator.ofFloat(0, 3000);// 设置值0-3000
        valueAnimator.setDuration(1000);// 1秒内
        valueAnimator.setInterpolator(new DecelerateInterpolator());// 设置后面速度
        // 设置1秒内从0到3000不断获取当前值
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float currentStep = (float) animation.getAnimatedValue();// 查值器来获取animation当前的值
                stepAnimatorView.setCurrentStep((int) currentStep);// 设置当前值，并触发重绘
            }
        });
        valueAnimator.start();// 开始动画
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, AnimatorActivity.class);
        context.startActivity(intent);
    }
}