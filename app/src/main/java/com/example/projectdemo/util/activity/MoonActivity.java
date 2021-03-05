package com.example.projectdemo.util.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.projectdemo.R;

public class MoonActivity extends Activity implements View.OnClickListener{
    private Button mButton;
    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moon);
        initData();
        initEvent();

        String name = getIntent().getStringExtra("name");
        int age = getIntent().getIntExtra("age", 0);
        mTextView.setText("你好，我叫" + name + "今年" + age + "岁，来自地球！");
    }

    private void initData() {
        mButton = findViewById(R.id.bt_back);
        mTextView = findViewById(R.id.tv_moon);
    }

    private void initEvent() {
        mButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_back:
                /**
                 * 3、设置返回给调用方的数据
                 */
                Intent intent = new Intent();
                intent.putExtra("respond", "来自月球的问候：HelloWorld!");// 可以用putExtra()的方法，也可以用setXXX()的方法
                setResult(Activity.RESULT_OK, intent);// 设置返回码和返回携带的数据
                finish();
                break;
            default:
                break;
        }
    }
}