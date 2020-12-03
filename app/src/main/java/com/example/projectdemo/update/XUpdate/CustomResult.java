package com.example.projectdemo.update.XUpdate;

import java.io.Serializable;

/**
 * 自定义版本检查的结果
 */
public class CustomResult implements Serializable {

    /*{
        "code":"200",
        "msg":"验证成功",
        "data":{
                "version":"V1.0.3",
                "versionExplain":"\r\n1、优化api接口。\r\n2、添加使用demo演示。\r\n3、新增自定义更新服务API接口。\r\n4、优化更新提示界面。",
                "versionTime":"2020-12-03",
                "downloadUrl":"http://www.dothantech.com/app/android/SYDY.apk",
                "apkSize":"4096"}
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
