package com.example.projectdemo.lztx.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.projectdemo.R;

public class NewPeopleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_people);
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, NewPeopleActivity.class);
        context.startActivity(intent);
    }
}