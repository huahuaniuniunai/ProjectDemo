package com.example.projectdemo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.projectdemo.R;

public class VitamioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vitamio);
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, VitamioActivity.class);
        context.startActivity(intent);
    }
}