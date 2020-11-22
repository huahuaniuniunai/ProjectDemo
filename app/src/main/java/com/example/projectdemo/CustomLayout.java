package com.example.projectdemo;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;

/**
 * 动态加载布局：通过LayoutInflater.from()来加载一个布局到当前布局上。
 * 1、新建一个动态加载的xml布局title，并定义好控件；
 * 2、新建一个自定义布局的类CustomLayout，并继承LinearLayout；
 * 3、新建一个activity活动DynaLoadLayout,并通过actionBar.hide()使系统自带的标题栏隐藏；
 * 同时创建它的布局activity_dyna_load_layout.xml，并添加自定义控件com.example.projectdemo.CustomLayout。
 * 4、回到CustomLayout类中设置监听事件；
 * 5、再在MainActivity中启动创建好的DynaLoadLayout的activity。
 */

public class CustomLayout extends LinearLayout implements View.OnClickListener {
    public CustomLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.title, this);
        Button back = findViewById(R.id.title_back);
        back.setOnClickListener(this);
        Button edit = findViewById(R.id.title_edit);
        edit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.title_back:
                ((Activity)getContext()).finish();
                break;
            case R.id.title_edit:
                Toast.makeText(getContext(), "you clicked edit button!", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
