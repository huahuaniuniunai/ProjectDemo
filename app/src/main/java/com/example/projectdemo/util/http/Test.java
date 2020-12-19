package com.example.projectdemo.util.http;

import android.util.Log;

import com.example.projectdemo.callback.HttpCallbackListener;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Response;

/**
 * OkHttp和HttpURLConnection网络的调用
 */
public class Test {
    String address = "https://www.baidu.com/";

    // OkHttp网络的调用
    public void okHttpResponse() {
        HttpUtil.sendOkHttpRequest(address, new okhttp3.Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

                // 在这里对异常情况进行处理
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                // 得到服务器返回的具体类容
                String responseData = response.body().string();
            }
        });
    }

    // HttpURLConnection网络的调用
    public void httpResponse() {
        HttpUtil.sendHttpRequest(address, new HttpCallbackListener() {
            @Override
            public void onFinish(String response) {

                // 当服务器成功响应的时候，在这里根据返回内容执行具体操作
                Log.d("response", response);
            }

            @Override
            public void onError(Exception e) {

                // 当出现异常，在这里对异常情况进行处理
            }
        });
    }
}
