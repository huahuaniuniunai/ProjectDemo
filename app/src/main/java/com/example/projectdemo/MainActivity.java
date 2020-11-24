package com.example.projectdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.projectdemo.paomadeng.RunHorseLampActivity;
import com.example.projectdemo.recyclerview.RecyclerActivity;
import com.example.projectdemo.txl.TxlActivity;
import com.example.projectdemo.txl.TxlChangeActivity;
import com.example.projectdemo.update.CheckVersion;
import com.example.projectdemo.util.CommonStartActivity;
import com.example.projectdemo.util.DynaLoadLayout;
import com.example.projectdemo.util.LoginActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private Button button5;
    private Button button6;
    private Button button7;
    private Button button8;
    private Button button9;
    private Button button10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
    }

    private void initView() {
        button1 = findViewById(R.id.bt_1);
        button2 = findViewById(R.id.bt_2);
        button3 = findViewById(R.id.bt_3);
        button4 = findViewById(R.id.bt_4);
        button5 = findViewById(R.id.bt_5);
        button6 = findViewById(R.id.bt_6);
        button7 = findViewById(R.id.bt_7);
        button8 = findViewById(R.id.bt_8);
    }

    private void initEvent() {
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);
        button5.setOnClickListener(this);
        button6.setOnClickListener(this);
        button7.setOnClickListener(this);
        button8.setOnClickListener(this);
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
            case R.id.bt_7:
                CheckVersion checkVersion = new CheckVersion();
                new Thread(checkVersion).start();
                break;
            case R.id.bt_8:
                RunHorseLampActivity.actionStart(MainActivity.this);
                break;
            default:
                break;
        }
    }
}
