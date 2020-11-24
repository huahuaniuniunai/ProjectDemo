package com.example.projectdemo.txl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.projectdemo.R;

import java.util.List;

public class TxlActivity extends AppCompatActivity {
    private List<PhoneDto> phoneDtos;
    private ListView lv_main_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_txl);
        check();
    }

    /**
     * 检查权限
     */
    private void check() {
        //判断是否有权限
        if(ContextCompat.checkSelfPermission(TxlActivity.this, Manifest.permission.READ_CONTACTS)
                != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(TxlActivity.this,new String[]{Manifest.permission.READ_CONTACTS},201);
        }else{
            initViews();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==201){
            initViews();
        }else{
            return;
        }
    }

    private void initViews() {
        PhoneUtil phoneUtil = new PhoneUtil(this);
        phoneDtos = phoneUtil.getPhone();
        lv_main_list = (ListView) findViewById(R.id.lv_main_list);
        MyAdapter myAdapter = new MyAdapter();
        lv_main_list.setAdapter(myAdapter);
        //给listview增加点击事件
        lv_main_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //拨打电话
//                Intent intent = new Intent();
//                intent.setAction("android.intent.action.CALL");
//                intent.addCategory(Intent.CATEGORY_DEFAULT);
//                intent.setData(Uri.parse("tel:"+phoneDtos.get(position).getTelPhone()));

                // 跳转到拨打电话界面，调用系统拨打电话功能
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+phoneDtos.get(position).getTelPhone()));
                startActivity(intent);

                // 跳转到系统的通讯录界面
//                startActivityForResult(new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI), 0);
            }
        });
    }
    //自定义适配器
    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return phoneDtos.size();
        }

        @Override
        public Object getItem(int position) {
            return phoneDtos.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @SuppressLint("NewApi")
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            PhoneDto phoneDto = phoneDtos.get(position);
            LinearLayout linearLayout = new LinearLayout(TxlActivity.this);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0,LinearLayout.LayoutParams.WRAP_CONTENT);
            layoutParams.weight = 1;
            TextView tv_name = new TextView(TxlActivity.this);
            tv_name.setId(View.generateViewId());
            tv_name.setLayoutParams(layoutParams);
            tv_name.setText(phoneDto.getName());
            TextView tv_num = new TextView(TxlActivity.this);
            tv_num.setId(View.generateViewId());
            tv_num.setLayoutParams(layoutParams);
            tv_num.setText(phoneDto.getTelPhone());
            linearLayout.addView(tv_name);
            linearLayout.addView(tv_num);
            return linearLayout;
        }
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, TxlActivity.class);
        context.startActivity(intent);
    }
}