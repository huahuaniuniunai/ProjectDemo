package com.example.projectdemo.broadcast;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.projectdemo.LoginActivity;
import com.example.projectdemo.R;
import com.example.projectdemo.util.activity.ActivityManager;

public class DynBroadcastActivity extends AppCompatActivity implements View.OnClickListener {
    private Button button;
    private ForceOfflineReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dyn_broadcast);

        button = findViewById(R.id.force_offline);
        button.setOnClickListener(this);
    }

    // 保证只有处于栈顶的activity才能接收这条广播来执行强制下线，非栈顶的activity不应该也没必要接收这条广播
    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.example.projectdemo.FORCE_OFFLINE");
        receiver = new ForceOfflineReceiver();
        registerReceiver(receiver, intentFilter);
    }

    // 当一个activity失去栈顶的位置时就会自动取消广播接收器的注册
    @Override
    protected void onPause() {
        super.onPause();
        if (receiver != null) {
            unregisterReceiver(receiver);
        }
        receiver = null;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent("com.example.projectdemo.FORCE_OFFLINE");
        sendBroadcast(intent);
    }

    class ForceOfflineReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("风险提示");
            builder.setMessage("您的账号被强制下线，请重新登录！");
            builder.setCancelable(false);
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    ActivityManager.finishAll();
                    LoginActivity.actionStart(DynBroadcastActivity.this);
                }
            });
            builder.show();
        }
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, DynBroadcastActivity.class);
        context.startActivity(intent);
    }
}