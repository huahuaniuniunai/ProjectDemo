package com.example.projectdemo.update.common;

import com.example.projectdemo.update.XUpdate.CustomResult;

import java.io.Serializable;

/**
 * 接口请求信息
 */
public class UpdateInfo implements Serializable {
//    public int versionCode;
//    public String versionName;
//    public String url;
//    public String description;

    public String code;
    public String msg;
    public Data data;
    class Data {
        public String version;
        public String versionExplain;
        public String versionTime;
        public String downloadUrl;
        public String apkSize;
    }
}
