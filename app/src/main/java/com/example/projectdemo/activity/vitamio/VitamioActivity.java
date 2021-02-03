package com.example.projectdemo.activity.vitamio;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.projectdemo.R;
import com.example.projectdemo.mvp.BaseActivity;
import com.gyf.immersionbar.ImmersionBar;

public class VitamioActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vitamio);
    }

    @Override
    protected void setBar() {
        ImmersionBar.with(this)
                .titleBar(R.id.tb_login)
                .keyboardEnable(true)
                .navigationBarColor(R.color.page)
                .init();
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, VitamioActivity.class);
        context.startActivity(intent);
    }
}