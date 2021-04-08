package com.example.projectdemo.pages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.projectdemo.R;
import com.example.projectdemo.mvp.BaseFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.gyf.immersionbar.ImmersionBar;
import com.gyf.immersionbar.components.ImmersionFragment;

public class MessageFragment extends BaseFragment implements View.OnClickListener {
    private View view;
    private FloatingActionButton fab;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_message, container, false);
        initView();
        initEvent();
        return view;
    }

    private void initView() {
        fab = view.findViewById(R.id.message_add);
    }

    private void initEvent() {
        fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.message_add:
                // 自带动画效果的提示, 还需配合CoordinatorLayout布局来使悬浮按钮自动移动而不被遮挡。
                Snackbar.make(view, "确定写信？", Snackbar.LENGTH_SHORT).setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(), "欢迎光临", Toast.LENGTH_SHORT).show();
                    }
                }).show();
                break;
            default:
                break;
        }
    }
}
