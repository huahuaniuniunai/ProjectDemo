package com.example.projectdemo.lztx.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.projectdemo.R;

public class PersonalDetailsActivity extends AppCompatActivity {
    private TextView mName;
    private TextView mTel;
    private String name;
    private String tel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);

        initView();
        initData();
    }

    private void initView() {
        mName = findViewById(R.id.name);
        mTel = findViewById(R.id.telephone);
    }

    private void initData() {
        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("name");
        tel = bundle.getString("tel");
        mName.setText(name);
        mTel.setText(tel);
    }

    public static void actionStart(Context context, String name, String tel) {
        Bundle bundle = new Bundle();
        bundle.putString("name", name);
        bundle.putString("tel", tel);
        Intent intent = new Intent(context, PersonalDetailsActivity.class);
        intent.putExtras(bundle);
        context.startActivity(intent);
    }
}