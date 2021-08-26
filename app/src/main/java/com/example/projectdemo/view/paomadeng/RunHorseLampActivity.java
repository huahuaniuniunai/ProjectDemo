package com.example.projectdemo.view.paomadeng;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import com.example.projectdemo.R;
import com.example.projectdemo.util.date.DateFormat;
import com.example.projectdemo.view.paomadeng.horizontal.SpanTextView;
import com.example.projectdemo.view.paomadeng.vertical.SwitchTextView;

import java.util.ArrayList;
import java.util.List;

public class RunHorseLampActivity extends AppCompatActivity {
    private SpanTextView pmd;
    private TextView mTime;
    private SwitchTextView switchTextView;
    private static final int msgKey1 = 1;
//    private static final int msgKey2 = 2;
    private final List<String> datas = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_horse_lamp);

        initView();
        initData();
        initEvent();
    }

    private void initView() {
        pmd = findViewById(R.id.tv_spn);
        mTime = findViewById(R.id.my_time);
        switchTextView = findViewById(R.id.switch_text);
    }

    private void initData() {
        // 初始化垂直方向滚动的数据
        for (int i = 0; i < 10; i++) {
            datas.add("恭喜！！！用户138****000" + i + "抽中400MB流量。");
        }
    }

    private void initEvent() {
        // 设置水平方向滚动
        new TimeThread().start();
        pmd.setMarquee(true);
        pmd.setSpanTextColor("温馨提示：", this.getResources().getColor(R.color.blue));
        pmd.setSpanTextColor("安静", this.getResources().getColor(R.color.blue));
        pmd.setSpanTextColor("等候", this.getResources().getColor(R.color.blue));

        // 设置垂直方向滚动效果
        switchTextView.startPlay(datas);
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

    private final Handler mHandler = new Handler() {
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