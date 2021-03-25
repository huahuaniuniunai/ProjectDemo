package com.example.projectdemo.design.factory;

import android.util.Log;

/**
 * 4、定义第一个产品实体
 */
class IphoneX extends Iphone {
    @Override
    public void power() {
        Log.d("Factory","IphoneX power on");
    }
}
