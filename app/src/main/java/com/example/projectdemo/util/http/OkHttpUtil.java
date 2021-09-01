package com.example.projectdemo.util.http;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class OkHttpUtil {
    private static final int TIME_OUT = 60;
    private OkHttpUtil() {
    }

    private static class HttpUtilHolder {
        private static final OkHttpClient.Builder CLIENT =new OkHttpClient.Builder();
    }

    private static OkHttpClient getInstance() {
        HttpUtilHolder.CLIENT.connectTimeout(TIME_OUT, TimeUnit.SECONDS);
        HttpUtilHolder.CLIENT.pingInterval(TIME_OUT, TimeUnit.SECONDS);
        HttpUtilHolder.CLIENT.readTimeout(TIME_OUT, TimeUnit.SECONDS);
        HttpUtilHolder.CLIENT.writeTimeout(TIME_OUT, TimeUnit.SECONDS);
        return HttpUtilHolder.CLIENT.build();
    }

    /**
     * post请求含token请求头json参数形式数据
     *
     * @param address 请求地址
     * @param token 请求头token
     * @param json 请求参数
     * @param callback 回调
     */
    public static void sendOkHttpRequest(String address, String token, String json, okhttp3.Callback callback) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Request request = new Request.Builder()
                .addHeader("token", token)
                .url(address)
                .post(requestBody)
                .build();
        getInstance().newCall(request).enqueue(callback);
    }

    /**
     * post请求json参数形式数据
     *
     * @param address 请求地址
     * @param json 请求参数
     * @param callback 回调
     */
    public static void sendOkHttpPost(String address, String json, okhttp3.Callback callback) {
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Request request = new Request.Builder()
                .url(address)
                .post(requestBody)
                .build();
        getInstance().newCall(request).enqueue(callback);
    }

    /**
     * get请求
     * @param address
     * @param token
     * @param callback
     */
    public static void sendOkHttpRequestForGet(String address, String token, okhttp3.Callback callback) {
//        OkHttpClient client = new OkHttpClient();
//        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"));
        Request request = new Request.Builder()
                .addHeader("token", token)
                .url(address)
                .build();
        getInstance().newCall(request).enqueue(callback);
    }

    /**
     * get请求表单数据
     * @param url
     * @param token
     * @param params
     * @param callback
     */
    public static void sendOkHttpRequestForGet(String url, String token, Map<String, Object> params, okhttp3.Callback callback) {
        StringBuilder urlBuilder = new StringBuilder(url).append("?");
        if (params != null) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                urlBuilder.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
        }
        Request request = new Request.Builder()
                .addHeader("token", token)
                .url(urlBuilder.substring(0, urlBuilder.length() - 1))
                .get()
                .build();
        getInstance().newCall(request).enqueue(callback);
    }
}
