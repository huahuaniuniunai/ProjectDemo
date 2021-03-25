package com.example.projectdemo.design.factory;

import android.util.Log;

/**
 * 5、定义第二个产品实体
 */
class IphoneXS extends Iphone {
    @Override
    public void power() {
        Log.d("Factory","IphoneXS power on");
    }
}
