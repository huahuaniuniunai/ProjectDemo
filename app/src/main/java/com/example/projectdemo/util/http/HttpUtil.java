package com.example.projectdemo.util.http;

import com.example.projectdemo.util.callback.HttpCallbackListener;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * HTTP网络请求公共类
 */
public class HttpUtil {

    // OkHttp网络请求
    public static void sendOkHttpRequest(String address, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(address).build();
        client.newCall(request).enqueue(callback);
    }

    // HttpURLConnection网络请求
    public static void sendHttpRequest(final String address, final HttpCallbackListener callback) {
        // 开启线程来发起网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();

                    // 设置为get请求来获取数据
                    connection.setRequestMethod("GET");

                    // 设置为post请求来提交数据
//                    connection.setRequestMethod("POST");
//                    DataOutputStream output = new DataOutputStream(connection.getOutputStream());
//                    output.writeBytes("username=admin&password=123456");

                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    // 下面对获取到的输入流进行读取
                    InputStream input = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                    }
                    if (callback != null) {
                        // 回调onFinish()方法，子线程无法通过return返回数据
                        callback.onFinish(response.toString());
                    }
                } catch (Exception e) {
                    if (callback != null) {
                        // 回调onError()方法
                        callback.onError(e);
                    }
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }
}
