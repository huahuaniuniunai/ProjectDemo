package com.example.projectdemo.util.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.projectdemo.R;

/**
 * 启动携带数据的activity的通用代码
 */
public class CommonStartActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commonstart);
        Bundle bundle = getIntent().getExtras();
        String param1 = bundle.getString("param1");
        int param2 = bundle.getInt("param2");
        Log.d("demo", param1);
        Log.d("demo", param2+"");
    }

    /**
     * 启动哪个activity就写在哪个activity下
     * 封装一个启动activity的方法，并携带2个不同类型的参数
     * @param context
     * @param data1
     * @param data2
     */
    public static void actionStart(Context context, String data1, int data2) {
        Bundle bundle = new Bundle();
        bundle.putString("param1", data1);
        bundle.putInt("param2", data2);
        Intent intent = new Intent(context, CommonStartActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}

