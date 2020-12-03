package com.example.projectdemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.projectdemo.permission.ActivityPermission;
import com.example.projectdemo.update.XUpdate.CustomUpdateParser;
import com.example.projectdemo.view.paomadeng.RunHorseLampActivity;
import com.example.projectdemo.recyclerview.RecyclerActivity;
import com.example.projectdemo.txl.TxlActivity;
import com.example.projectdemo.txl.TxlChangeActivity;
import com.example.projectdemo.update.common.CheckVersion;
import com.example.projectdemo.util.activity.CommonStartActivity;
import com.example.projectdemo.util.layout.DynaLoadLayout;
import com.example.projectdemo.util.activity.LoginActivity;
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

//    private String mUpdateUrl3 = "https://gitee.com/xuexiangjys/XUpdate/raw/master/jsonapi/update_custom.json";
    private String mUpdateUrl3 = "http://192.168.222.92:7300/mock/5fc73578729289001d028930/test/check/update";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
        checkPermission();
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
        button10 = findViewById(R.id.bt_10);
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
        button10.setOnClickListener(this);
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
                CheckVersion checkVersion = new CheckVersion(this);
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
            case R.id.bt_10:
                ActivityPermission.actionStart(MainActivity.this);
                break;
            default:
                break;
        }
    }

    private void checkPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(MainActivity.this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        1);
            } else {

                // 获取权限后需执行的操作

            }
        }
    }

    /**
     * 权限选择回调
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // 获取权限后需执行的操作

                } else {
                    Toast.makeText(this, "很遗憾！您拒绝了存储权限。", Toast.LENGTH_SHORT).show();
                }
                break;
            default:
                break;
        }
    }
}
