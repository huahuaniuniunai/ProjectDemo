package com.example.projectdemo;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

/**
 * 隐藏ActionBar
 */
public class DynaLoadLayout extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dyna_load_layout);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, DynaLoadLayout.class);
        context.startActivity(intent);
    }
}
