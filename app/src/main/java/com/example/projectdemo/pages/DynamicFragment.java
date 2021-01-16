package com.example.projectdemo.pages;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.projectdemo.R;
import com.example.projectdemo.mvp.BaseFragment;
import com.gyf.immersionbar.ImmersionBar;
import com.gyf.immersionbar.components.ImmersionFragment;

public class DynamicFragment extends BaseFragment {
    private View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_dynamic, container, false);
        return view;
    }
}
