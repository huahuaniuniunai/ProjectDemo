package com.example.projectdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.projectdemo.recyclerview.RecyclerActivity;
import com.example.projectdemo.txl.TxlActivity;
import com.example.projectdemo.txl.TxlChangeActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button1 = findViewById(R.id.bt_1);
        button1.setOnClickListener(this);

        Button button2 = findViewById(R.id.bt_2);
        button2.setOnClickListener(this);

        Button button3 = findViewById(R.id.bt_3);
        button3.setOnClickListener(this);

        Button button4 = findViewById(R.id.bt_4);
        button4.setOnClickListener(this);

        Button button5 = findViewById(R.id.bt_5);
        button5.setOnClickListener(this);

        Button button6 = findViewById(R.id.bt_6);
        button6.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_1:
                /**
                 * 调用通用启动activity的方法，并携带2个不同类型的参数
                 */
                CommonStartActivity.actionStart(MainActivity.this, "张三", 25);
                break;
            case R.id.bt_2:
                DynaLoadLayout.actionStart(MainActivity.this);
                break;
            case R.id.bt_3:
                RecyclerActivity.actionStart(MainActivity.this);
                break;
            case R.id.bt_4:
                LoginActivity.actionStart(MainActivity.this);
                break;
            case R.id.bt_5:
                TxlActivity.actionStart(MainActivity.this);
                break;
            case R.id.bt_6:
                TxlChangeActivity.actionStart(MainActivity.this);
                break;
            default:
                break;
        }
    }
}
