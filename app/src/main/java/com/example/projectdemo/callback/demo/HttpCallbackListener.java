package com.example.projectdemo.callback.demo;

/**
 * Java的回调监听机制
 * 回调的核心就是调用方将本身即this传递给回调方，回调方再反过来调用调用方的方法
 *
 * 解决在服务器还没来得及响应的时候，调用方法无法返回响应的数据
 *
 * HttpURLConnection的回调接口
 */
public interface HttpCallbackListener {
    void onFinish(String response);
    void onError(Exception e);
}
