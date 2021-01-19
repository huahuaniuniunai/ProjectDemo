package com.example.projectdemo.pages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.projectdemo.R;
import com.example.projectdemo.mvp.BaseFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
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
                toast("欢迎光临！");
                break;
            default:
                break;
        }
    }
}
