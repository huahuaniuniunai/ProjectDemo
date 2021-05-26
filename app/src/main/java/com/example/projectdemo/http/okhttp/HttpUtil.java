package com.example.projectdemo.http.okhttp;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class HttpUtil {
//    private HttpUtil() {
//    }
//
//    private static class HttpUtilHolder {
//        private static final OkHttpClient CLIENT = new OkHttpClient();
//    }
//
//    private static OkHttpClient getInstance() {
//        return HttpUtilHolder.CLIENT;
//    }


    /**
     * post请求json参数形式数据
     *
     * @param address 请求地址
     * @param token 请求头token
     * @param json 请求参数
     * @param callback 回调
     */
    public static void sendOkHttpRequest(String address, String token, String json, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
        Request request = new Request.Builder()
                .addHeader("token", token)
                .url(address)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(callback);
    }
}
