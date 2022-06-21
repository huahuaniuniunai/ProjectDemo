package com.example.projectdemo.util.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.projectdemo.R;
import com.example.projectdemo.util.log.LogUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 启动携带数据的activity的通用代码
 */
public class CommonStartActivity extends Activity implements View.OnClickListener {
    private final int REQUEST_CODE = 0x1;
    private Button mButton;
    private Button mButtonNo;
    private TextView mTextView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commonstart);
        initData();
        initEvent();
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

    /**
     * 判断是否是首次启动
     *
     * 此方法启动调用第一次是准确值，如果在一次启动中多次调用，即使是首次启动，第二次调用也会变成非首次启动。
     * 若需要多次获取，可以赋新值使用,每次启动只能调用此方法一次，赋值获取
     * @param context
     * @return
     */
    public static boolean isFirstStart(Context context) {
        SharedPreferences preferences = context.getSharedPreferences("NB_FIRST_START", 0);
        boolean isFirst = preferences.getBoolean("FIRST_START", true);
        if (isFirst) {
            preferences.edit().putBoolean("FIRST_START", false).commit();
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断是否是今日首次启动APP
     * @param context
     * @return
     */
    public static boolean isTodayFirstStartApp(Context context) {
        try {
            SharedPreferences preferences = context.getSharedPreferences("NB_TODAY_FIRST_START_APP", MODE_PRIVATE);
            String svaeTime = preferences.getString("startAppTime", "2020-01-08");
            String todayTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

            if (!TextUtils.isEmpty(todayTime) && !TextUtils.isEmpty(svaeTime)) {
                if(!svaeTime.equals(todayTime)) {
                    preferences.edit().putString("startAppTime", todayTime).commit();
                    return true;
                }
            }

        }catch (Exception e){
            LogUtil.d("TAG", "是否为今日首次启动APP,获取异常：" + e.toString());
            return true;
        }
        return  false;
    }

    private void initData() {
        mButton = findViewById(R.id.bt_send);
        mButtonNo = findViewById(R.id.bt_no);
        mTextView = findViewById(R.id.tv_result);
    }

    private void initEvent() {
        mButton.setOnClickListener(this);
        mButtonNo.setOnClickListener(this);
        mTextView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_send:
                /**
                 *
                 * 1、启动另一个activity，把要传回的数据放到Intent里，并设置标志。
                 */
                Intent intent = new Intent(this, MoonActivity.class);
                intent.putExtra("name", "张三");
                intent.putExtra("age", 25);
                startActivityForResult(intent, REQUEST_CODE);//REQUEST_CODE用于标记启动了哪个Activity
                break;
            case R.id.bt_no:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            /**
             * 2、基于请求码，且另一个activity回调成功，拿到回调数据。ps：第3步在MoonActivity里
             */
            case REQUEST_CODE: //返回的结果来自于MoonActivity
                if (resultCode == Activity.RESULT_OK) {// RESULT_OK是回调成功标志
                    mTextView.setText(data.getStringExtra("respond"));// 根据key拿到MoonActivity里设置的value
                }
                break;
            default:
                break;
        }
    }
}