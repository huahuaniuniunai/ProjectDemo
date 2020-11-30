package com.example.projectdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.projectdemo.update.XUpdate.CustomUpdateParser;
import com.example.projectdemo.view.paomadeng.RunHorseLampActivity;
import com.example.projectdemo.recyclerview.RecyclerActivity;
import com.example.projectdemo.txl.TxlActivity;
import com.example.projectdemo.txl.TxlChangeActivity;
import com.example.projectdemo.update.common.CheckVersion;
import com.example.projectdemo.util.CommonStartActivity;
import com.example.projectdemo.util.DynaLoadLayout;
import com.example.projectdemo.util.LoginActivity;
import com.xuexiang.xupdate.XUpdate;

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

    private String mUpdateUrl3 = "https://gitee.com/xuexiangjys/XUpdate/raw/master/jsonapi/update_custom.json";

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
        button9 = findViewById(R.id.bt_9);
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
        button9.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_1:
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
            case R.id.bt_9:
                XUpdate.newBuild(this)
                        .updateUrl(mUpdateUrl3)
                        .updateParser(new CustomUpdateParser())
                        .update();
                break;
            default:
                break;
        }
    }
}
