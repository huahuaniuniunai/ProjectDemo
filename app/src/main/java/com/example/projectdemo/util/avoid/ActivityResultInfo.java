package com.example.projectdemo.util.avoid;

import android.content.Intent;

/**
 * User: wangjian
 * Date: 2020/10/29
 * Time: 5:35 PM
 */
public class ActivityResultInfo {
    private int requestCode;
    private int resultCode;
    private Intent data;

    public ActivityResultInfo(int requestCode, int resultCode, Intent data) {
        this.requestCode = requestCode;
        this.resultCode = resultCode;
        this.data = data;
    }

    public int getRequestCode() {
        return requestCode;
    }

    public void setRequestCode(int requestCode) {
        this.requestCode = requestCode;
    }

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public Intent getData() {
        return data;
    }

    public void setData(Intent data) {
        this.data = data;
    }
}
