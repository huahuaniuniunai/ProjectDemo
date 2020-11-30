package com.example.projectdemo.update.XUpdate;

import java.io.Serializable;

/**
 * 自定义版本检查的结果
 */
public class CustomResult implements Serializable {

    public boolean hasUpdate;

    public boolean isIgnorable;

    public int versionCode;

    public String versionName;

    public String updateLog;

    public String apkUrl;

    public long apkSize;

}
