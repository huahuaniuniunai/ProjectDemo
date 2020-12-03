package com.example.projectdemo.update.XUpdate;

import java.io.Serializable;

/**
 * 自定义版本检查的结果
 */
public class CustomResult implements Serializable {

    /*{
        "code":"200",
        "msg":"验证成功",
        "data":{"version":"V1.0.3",
                "versionExplain":"版本描述",
                "versionTime":"版本时间",
                "downloadUrl":"下载地址",
                "apkSize":"单位M"}
    }*/

    public String code;
    public String msg;
    public Data data;
    class Data {
        public String version;
        public String versionExplain;
        public String versionTime;
        public String downloadUrl;
        public long apkSize;
    }

//    public boolean hasUpdate;
//
//    public boolean isIgnorable;
//
//    public int versionCode;
//
//    public String versionName;
//
//    public String updateLog;
//
//    public String apkUrl;
//
//    public long apkSize;

}
