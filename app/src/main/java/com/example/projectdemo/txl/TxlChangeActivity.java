package com.example.projectdemo.txl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectdemo.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TxlChangeActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mButton;
    private Button mButton2;
    private LinearLayout mLinearLayout;
    private RecyclerView mRv;
    private Button mButton3;
    private Button mButton4;
    private Activity mContext;
    private List<HashMap<String, String>> mContactList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_txl_change);
        mButton = findViewById(R.id.button);
        mButton2 = findViewById(R.id.button2);
        mButton3 = findViewById(R.id.button3);
        mButton4 = findViewById(R.id.button4);
        mLinearLayout = findViewById(R.id.linearLayout);
        mRv = findViewById(R.id.rv);
        mContext = this;

        queryContactsShowData();
        initEvent();
    }

    /**
     * 设置事件
     */
    private void initEvent() {
        mButton.setOnClickListener(this);
        mButton2.setOnClickListener(this);
        mButton3.setOnClickListener(this);
        mButton4.setOnClickListener(this);
    }

    /**
     * 查询手机联系人并显示
     */
    private void queryContactsShowData() {
        mContactList.clear();
        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        if (cursor == null)
            return;
        while (cursor.moveToNext()) {
            String phoneName;
            String phoneNumber;
            HashMap<String, String> listItem = new HashMap<>();
            phoneName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            listItem.put("phoneName", phoneName);
            listItem.put("phoneNumber", phoneNumber);
            mContactList.add(listItem);
        }

        cursor.close();

        mRv.setAdapter(new ContactAdapter());
        mRv.setLayoutManager(new LinearLayoutManager(mContext));

    }

    /**
     * 联系人的适配器
     */
    public class ContactAdapter extends RecyclerView.Adapter<MyViewHolder> {

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            MyViewHolder viewHolder = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_view, parent, false));
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            HashMap<String, String> map = mContactList.get(position);
            String phoneName = map.get("phoneName");
            String phoneNumber = map.get("phoneNumber");
            holder.mName.setText(phoneName);
            holder.mNumber.setText(phoneNumber);
            Log.v("nn", phoneName + " : " + phoneNumber);
        }

        @Override
        public int getItemCount() {
            return mContactList.size();
        }
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView mName;
        TextView mNumber;
        View     mItemView;

        public MyViewHolder(View itemView) {
            super(itemView);
            mItemView = itemView;
            mName = itemView.findViewById(R.id.tvName);
            mNumber = itemView.findViewById(R.id.tvNumber);
        }
    }

    /**
     * 写入手机联系人
     */
    private void writeContact() {
        String name = "test";
        String number = "13666668888";

        //先查询要添加的号码是否已存在通讯录中, 不存在则添加. 存在则提示用户
        Uri uri = Uri.parse("content://com.android.contacts/data/phones/filter/" + number);
        ContentResolver resolver = getContentResolver();
        //从raw_contact表中返回display_name
        Cursor cursor = resolver.query(uri, new String[]{ContactsContract.Data.DISPLAY_NAME}, null, null, null);
        if (cursor == null)
            return;

        if (cursor.moveToFirst()) {
            Log.i("nn", "name=" + cursor.getString(0));
            Toast.makeText(this, "存在相同号码", Toast.LENGTH_SHORT).show();
        } else {
            uri = Uri.parse("content://com.android.contacts/raw_contacts");
            ContentValues values = new ContentValues();
            long contact_id = ContentUris.parseId(resolver.insert(uri, values));
            //插入data表
            uri = Uri.parse("content://com.android.contacts/data");
            //add Name
            values.put("raw_contact_id", contact_id);
            values.put(ContactsContract.Data.MIMETYPE, "vnd.android.cursor.item/name");
//            values.put("data2", "qq");
            values.put("data1", name);
            resolver.insert(uri, values);
            values.clear();

            //add Phone
            values.put("raw_contact_id", contact_id);
            values.put(ContactsContract.Data.MIMETYPE, "vnd.android.cursor.item/phone_v2");
//            values.put("data2", "2");   //手机
            values.put("data1", number);
            resolver.insert(uri, values);
            values.clear();

            //add email
            values.put("raw_contact_id", contact_id);
            values.put(ContactsContract.Data.MIMETYPE, "vnd.android.cursor.item/email_v2");
            values.put("data2", "1");   //邮箱
            values.put("data1", "xxxx@qq.com");
            resolver.insert(uri, values);
            values.clear();

            //add organization
            values.put("raw_contact_id", contact_id);
            values.put(ContactsContract.Data.MIMETYPE, "vnd.android.cursor.item/organization");
            values.put("data4", "产品经理");   //职务
            values.put("data1", "腾讯科技");   //公司
            resolver.insert(uri, values);
            values.clear();

            Toast.makeText(this, "插入号码成功", Toast.LENGTH_SHORT).show();
        }
        cursor.close();
    }

    /**
     * 删除联系人
     */
    private void deleteContact() {
        String name = "test";

        //根据姓名求id
        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(uri, new String[]{ContactsContract.Data._ID}, "display_name=?", new String[]{name}, null);
        if (cursor == null)
            return;

        if (cursor.moveToFirst()) {
            int id = cursor.getInt(0);
            //根据id删除data中的相应数据
            resolver.delete(uri, "display_name=?", new String[]{name});
            uri = Uri.parse("content://com.android.contacts/data");
            resolver.delete(uri, "raw_contact_id=?", new String[]{id + ""});
            Toast.makeText(this, "删除号码成功", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "没有找到号码", Toast.LENGTH_SHORT).show();
        }
        cursor.close();
    }

    /**
     * 更改联系人
     */
    private void changeContact(){
        String name = "test";
        String newPhone = "13644440000";
        //根据姓名求id
        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
        ContentResolver resolver = getContentResolver();
        Cursor cursor = resolver.query(uri, new String[]{ContactsContract.Data._ID}, "display_name=?", new String[]{name}, null);
        if (cursor == null)
            return;

        if (cursor.moveToFirst()) {
            int id = cursor.getInt(0);
            ContentValues values = new ContentValues();
            values.put("data1", newPhone);
            resolver.update(uri, values, "mimetype=? and raw_contact_id=?", new String[]{"vnd.android.cursor.item/phone_v2", id + ""});
        }else{
            Toast.makeText(this, "没有找到号码", Toast.LENGTH_SHORT).show();
        }
        cursor.close();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button: //查询所有数据
                queryContactsShowData();
                break;
            case R.id.button2: //写入
                writeContact();
                break;
            case R.id.button3: //删除
                deleteContact();
                break;
            case R.id.button4: //更改
                changeContact();
                break;
        }
    }


    public static void actionStart(Context context) {
        Intent intent = new Intent(context, TxlChangeActivity.class);
        context.startActivity(intent);
    }
}