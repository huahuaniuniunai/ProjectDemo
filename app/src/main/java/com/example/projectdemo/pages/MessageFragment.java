package com.example.projectdemo.pages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.projectdemo.R;
import com.example.projectdemo.mvp.BaseFragment;
import com.gyf.immersionbar.ImmersionBar;
import com.gyf.immersionbar.components.ImmersionFragment;

public class MessageFragment extends ImmersionFragment {
    private View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_message, container, false);
        return view;
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
}
