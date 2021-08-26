package com.example.projectdemo.activity;

import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.projectdemo.R;
import com.example.projectdemo.adapter.SeeMoreAdapter;
import com.example.projectdemo.bean.CityBean;
import com.example.projectdemo.mvp.BaseActivity;
import com.gyf.immersionbar.ImmersionBar;

import java.util.ArrayList;
import java.util.List;

public class SeeMoreActivity extends BaseActivity implements View.OnClickListener {
    private RecyclerView recyclerView;
    private TextView txtOpen;
    private TextView txtYinying;
    private SeeMoreAdapter mSeeMoreAdapter;
    private final List<CityBean> mCityList = new ArrayList<>();
    private final List<CityBean> HideList = new ArrayList<>();

    @Override
    protected void setBar() {
        ImmersionBar.with(this)
                .titleBar(R.id.tb_see_more)
                .navigationBarColor(R.color.page)
                .init();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_more);

        initView();
        initData();
        initEvent();
    }

    private void initView() {
        recyclerView = findViewById(R.id.rv_see_more);
        txtOpen = findViewById(R.id.tv_open);
        txtYinying = findViewById(R.id.tv_yinying);
    }

    private void initData() {
        String[] data = {"黑龙江","辽宁","吉林","河北","河南","湖北","湖南","山东"
                ,"山西","陕西","安徽","浙江","江苏","福建","广东","海南"
                ,"四川","云南","贵州","青海","甘肃","江西","台湾","北京"
                ,"天津","重庆","上海","内蒙古","新疆","宁夏","广西"};

        //获取源数据集合
        for (int i = 0; i < data.length; i++) {
            CityBean obj = new CityBean();
            obj.setName(data[i]);
            mCityList.add(obj);
        }

        //收起显示的数据仅显示12条
        for (int i = 0; i < 12; i++) {
            HideList.add(mCityList.get(i));
        }
        //适配器
        mSeeMoreAdapter = new SeeMoreAdapter(mCityList);
        recyclerView.setAdapter(mSeeMoreAdapter);
        //默认设置收起时的数据
        mSeeMoreAdapter.setHideList(HideList);
    }

    private void initEvent() {
        txtOpen.setOnClickListener(this);
        txtYinying.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_open:
                Log.d("demo", "来了老弟");
                if (txtOpen.getText().toString().equals("展开更多")) {
                    txtOpen.setText("点击收起");
                    txtYinying.setVisibility(View.GONE);
                    mSeeMoreAdapter.setOpenList(mCityList);
                } else {
                    txtOpen.setText("展开更多");
                    txtYinying.setVisibility(View.VISIBLE);
                    mSeeMoreAdapter.setHideList(HideList);
                }
                break;
            default:
                break;
        }
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, SeeMoreActivity.class);
        context.startActivity(intent);
    }
}