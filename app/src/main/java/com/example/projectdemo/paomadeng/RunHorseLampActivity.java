package com.example.projectdemo.paomadeng;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.projectdemo.R;
import com.example.projectdemo.txl.TxlActivity;

public class RunHorseLampActivity extends AppCompatActivity {
    private SpanTextView pmd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_run_horse_lamp);

        initView();
        initData();
    }

    private void initView() {
        pmd = findViewById(R.id.tv_spn);
    }

    private void initData() {
        pmd.setMarquee(true);
        pmd.setSpanTextColor("安静", this.getResources().getColor(R.color.blue));
        pmd.setSpanTextColor("等候", this.getResources().getColor(R.color.blue));
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, RunHorseLampActivity.class);
        context.startActivity(intent);
    }
}