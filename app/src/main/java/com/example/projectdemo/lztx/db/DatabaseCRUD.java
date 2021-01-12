package com.example.projectdemo.lztx.db;

import android.content.Context;
import android.text.TextUtils;
import android.widget.Toast;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

/**
 * User: zhangshiwu
 * Date: 2020/11/07
 * Time: 11:00 PM
 * 对手机系统通讯录及数据库操作
 */
public class DatabaseCRUD {
    private static Context mContext;

    public DatabaseCRUD(Context context) {
        this.mContext = context;
    }

    /**
     * 新增联系人
     */
    public void writeContact(String name, String telephone) {
        List<SortModel> sortModelTels = DatabaseUtil.getContactAllTel();
        List<String> tels = new ArrayList<>();
        for (SortModel sortModelTel : sortModelTels) {
            tels.add(sortModelTel.getTelPhone());
        }

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(telephone)) {
            Toast.makeText(mContext, "抱歉，姓名或号码不能为空！", Toast.LENGTH_SHORT).show();
        } else {
            if (tels.contains(telephone)) {
                Toast.makeText(mContext, "该号码已存在，请勿重复添加！", Toast.LENGTH_SHORT).show();
            } else {
                SortModel sortModel = new SortModel();
                sortModel.setName(name);
                sortModel.setTelPhone(telephone);
                sortModel.save();
                Toast.makeText(mContext, name + ":" + telephone + "号码保存成功!", Toast.LENGTH_SHORT).show();
            }
        }

        /*
        // 系统通讯录
        if (!TextUtils.isEmpty(name) || !TextUtils.isEmpty(telephone)) {
            //先查询要添加的号码是否已存在通讯录中, 不存在则添加. 存在则提示用户
            Uri uri = Uri.parse("content://com.android.contacts/data/phones/filter/" + telephone);
            ContentResolver resolver = mContext.getContentResolver();
            //从raw_contact表中返回display_name
            Cursor cursor = resolver.query(uri, new String[]{ContactsContract.Data.DISPLAY_NAME}, null, null, null);
            if (cursor == null)
                return;

            if (cursor.moveToFirst()) {
                Log.d("demo", "name=" + cursor.getString(0));
                Toast.makeText(mContext, "该号码已存在，请勿重复添加！", Toast.LENGTH_SHORT).show();
            } else {
                uri = Uri.parse("content://com.android.contacts/raw_contacts");
                ContentValues values = new ContentValues();
                long contact_id = ContentUris.parseId(resolver.insert(uri, values));
                //插入data表
                uri = Uri.parse("content://com.android.contacts/data");

                //add Name
                values.put("raw_contact_id", contact_id);
                values.put(ContactsContract.Data.MIMETYPE, "vnd.android.cursor.item/name");
                values.put("data1", name);
                resolver.insert(uri, values);
                values.clear();

                //add Phone
                values.put("raw_contact_id", contact_id);
                values.put(ContactsContract.Data.MIMETYPE, "vnd.android.cursor.item/phone_v2");
                values.put("data1", telephone);
                resolver.insert(uri, values);
                values.clear();

                // 新增到TxlDatabase.db数据库
                SortModel sortModel = new SortModel();
                sortModel.setName(name);
                sortModel.setTelPhone(telephone);
                sortModel.save();
                Toast.makeText(mContext, name + ":" + telephone + "号码保存成功!", Toast.LENGTH_SHORT).show();
            }
            cursor.close();
        } else {
            Toast.makeText(mContext, "抱歉，姓名或号码不能为空！", Toast.LENGTH_SHORT).show();
        }
        */

    }

    /**
     * 删除联系人，只适用与删除app添加的联系人，暂无法删除系统添加的联系人
     */
    public void deleteContact(String tel) {
        // 删除数据库联系人信息
        LitePal.deleteAll(SortModel.class, "telPhone=?", tel);
        Toast.makeText(mContext, "删除联系人成功!", Toast.LENGTH_SHORT).show();

        /*
        // 系统通讯录
        name = "王雷";//测试

        //根据姓名求id
        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
        ContentResolver resolver = mContext.getContentResolver();
        Cursor cursor = resolver.query(uri, new String[]{ContactsContract.Data._ID}, "display_name=?", new String[]{name}, null);
        if (cursor == null)
            return;

        if (cursor.moveToFirst()) {
            int id = cursor.getInt(0);
            //根据id删除data中通讯录的相应数据
            resolver.delete(uri, "display_name=?", new String[]{name});
            uri = Uri.parse("content://com.android.contacts/data");
            resolver.delete(uri, "raw_contact_id=?", new String[]{id + ""});

            // 删除数据库联系人信息
            LitePal.deleteAll(SortModel.class, "name=?" , name);
            Toast.makeText(mContext, "删除联系人成功!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(mContext, "没有找到该号码!", Toast.LENGTH_SHORT).show();
        }

        cursor.close();
        */
    }

    /**
     * 更改联系人
     */
//    public static void changeContact(String name, String telephone, String newName, String newTelephone){
//        //根据姓名求id
//        Uri uri = Uri.parse("content://com.android.contacts/raw_contacts");
//        ContentResolver resolver = mContext.getContentResolver();
//        Cursor cursor = resolver.query(uri, new String[]{ContactsContract.Data._ID}, "display_name=?", new String[]{newName}, null);
//        if (cursor == null)
//            return;
//
//        if (cursor.moveToFirst()) {
//            int id = cursor.getInt(0);
//            ContentValues values = new ContentValues();
//            values.put("data1", newTelephone);
//            resolver.update(uri, values, "mimetype=? and raw_contact_id=?", new String[]{"vnd.android.cursor.item/phone_v2", id + ""});
//
//            // 修改ContactDatabase.db数据库的联系人信息
//            PhoneDto phoneDto = new PhoneDto();
//            phoneDto.setName(newName);
//            phoneDto.setTelPhone(newTelephone);
//            phoneDto.updateAll("name = ? and telPhone = ?", name, telephone);
//        }else{
//            Toast.makeText(mContext, "没有找到号码", Toast.LENGTH_SHORT).show();
//        }
//        cursor.close();
//    }
}
