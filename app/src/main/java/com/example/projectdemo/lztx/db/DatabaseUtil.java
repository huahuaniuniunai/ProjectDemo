package com.example.projectdemo.lztx.db;

import android.content.Context;

import org.litepal.LitePal;

import java.util.List;

/**
 * 对lztx数据库操作：
 * 数据库名称：TxlDatabase.db
 * 表名称：SortModel
 */
public class DatabaseUtil {
    private static Context mContext;

    public DatabaseUtil(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * 查询数据库name列的所有数据
     *
     * @return
     */
    public static List<SortModel> getContactAllName() {
        return LitePal.select("name").find(SortModel.class);
    }

    /**
     * 查询数据库telPhone列的所有数据
     *
     * @return
     */
    public static List<SortModel> getContactAllTel() {
        return LitePal.select("telPhone").find(SortModel.class);

    }

    /**
     * 查询数据库name列和telPhone列的所有数据
     *
     * @return
     */
    public static List<SortModel> getContact() {
        return LitePal.select("name", "telPhone").find(SortModel.class);
    }

    /**
     * 通过姓名精确查询号码
     *
     * @param name
     * @return
     */
    public static List<SortModel> getContactByName(String name) {
        return LitePal.select("telPhone").where("name = ?", name).find(SortModel.class);
    }

    /**
     * 通过号码精确查询姓名
     *
     * @param tel
     * @return
     */
    public static List<SortModel> getContactByTel(String tel) {
        return LitePal.select("name").where("telPhone = ?", tel).find(SortModel.class);
    }

    /**
     * 通过姓名模糊查询
     *
     * @param str
     * @return
     */
    public static List<SortModel> getLikeByName(String str) {
        return LitePal.where("name like ?", "%" + str + "%").find(SortModel.class);
    }

    /**
     * 通过号码模糊查询
     *
     * @param str
     * @return
     */
    public static List<SortModel> getLikeByTel(String str) {
        return LitePal.where("telPhone like ?", str + "%").find(SortModel.class);
    }

    /**
     * 通过手机号查询联系人在通信录的名称
     * 主要用于显示接收短信的名称与通讯录保持一致
     */
    public static List<SortModel> getTopNameFromTel(String tel) {
        return LitePal.select("name").where("telPhone = ? limit 1", tel).find(SortModel.class);

    }

}
