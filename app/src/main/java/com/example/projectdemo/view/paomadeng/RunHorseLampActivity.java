package com.example.projectdemo.view.paomadeng;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.example.projectdemo.R;
import com.example.projectdemo.util.DateFormat;

public class RunHorseLampActivity extends AppCompatActivity {
    private SpanTextView pmd;
    private TextView mTime;
    private static final int msgKey1 = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_horse_lamp);

        initView();
        initData();
        new TimeThread().start();
    }

    private void initView() {
        pmd = findViewById(R.id.tv_spn);
        mTime = findViewById(R.id.my_time);
    }

    private void initData() {
        pmd.setMarquee(true);
        pmd.setSpanTextColor("温馨提示：", this.getResources().getColor(R.color.blue));
        pmd.setSpanTextColor("安静", this.getResources().getColor(R.color.blue));
        pmd.setSpanTextColor("等候", this.getResources().getColor(R.color.blue));
    }

    public class TimeThread extends Thread {
        @Override
        public void run () {
            do {
                try {
                    Message msg = new Message();
                    msg.what = msgKey1;
                    mHandler.sendMessage(msg);
                    Thread.sleep(1000);
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while(true);
        }
    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage (Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case msgKey1:
                    mTime.setText(DateFormat.getTime());
                    break;
                default:
                    break;
            }
        }
    };

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, RunHorseLampActivity.class);
        context.startActivity(intent);
    }
}