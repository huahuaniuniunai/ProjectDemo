package com.example.projectdemo.http.okhttp;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

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
     * get请求
     * @param url
     */
    public void get(String url){
        // 1 获取OkHttpClient对象
        OkHttpClient client = new OkHttpClient();
        // 2 设置请求
        Request request = new Request.Builder()
                .get()// 默认get方式可以省略
                .url(url)
                .build();
        // 3 封装call
        Call call = client.newCall(request);
        // 4 异步调用,并设置回调函数
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                // ...
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (response!=null && response.isSuccessful()){
                    // ...
                    // response.body().string();
                }
            }
        });
        //同步调用,返回Response,会抛出IO异常
        //Response response = call.execute();
    }

    /**
     * post请求json参数形式
     *
     * @param url 请求地址
     * @param token 请求头token
     * @param json 请求参数
     * @param callback 回调
     */
    public static void sendJsonPost(String url, String token, String json, okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json);
//        如果是json字符串，替换请求媒体类型即可
//        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain;charset=utf-8"), "{username:admin;password:admin}");
        Request request = new Request.Builder()
                .addHeader("token", token)
                .url(url)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(callback);
    }

    /**
     * post请求form表单形式
     * @param url
     * @param token
     * @param formBody
     * @param callback
     */
    public static void sendFormPost(String url, String token, FormBody formBody, okhttp3.Callback callback) {
        // 1 获取OkHttpClient对象
        OkHttpClient client = new OkHttpClient();
        // 2 构建参数
//        FormBody formBody = new FormBody.Builder()
//                .add("username", "admin")
//                .add("password", "admin")
//                .build();

        // 3 构建 request
        Request request = new Request.Builder()
                .addHeader("token", token)
                .url(url)
                .post(formBody)
                .build();
        // 4 将Request封装为Call
        Call call = client.newCall(request);
        // 5 异步调用
        call.enqueue(callback);
    }

    /**
     * 文件下载
     * @param url
     * @param target
     * @param fileName
     */
    private void download(String url, String target, String fileName){
        // 1 获取OkHttpClient对象
        OkHttpClient client = new OkHttpClient();
        // 2构建 request
        Request request = new Request.Builder()
                .url(url)
                .build();
        // 3 异步调用
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    // 4 文件下载
                    downLodeFile(response, target,fileName);
                }
            }
        });
    }

    private void downLodeFile(Response response, String url, String fileName) {
        InputStream inputStream = null;
        byte[] buf = new byte[2048];
        int len = 0;
        FileOutputStream outputStream = null;
        try {
            inputStream = response.body().byteStream();
            //文件大小
            long total = response.body().contentLength();
            File file = new File(url, fileName);
            outputStream = new FileOutputStream(file);
            long sum = 0;
            while ((len = inputStream.read(buf)) != -1) {
                outputStream.write(buf, 0, len);
            }
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null)
                    inputStream.close();
                if (outputStream != null)
                    outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 文件上传
     * @param url
     */
    public void upload(String url){
        File file = new File("C:/mydata/generator/test.txt");
        if (!file.exists()){
            System.out.println("文件不存在");
        }else{
            // 获取OkHttpClient对象
            OkHttpClient client = new OkHttpClient();
            // 2封装参数以 form形式
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("username", "admin")
                    .addFormDataPart("password", "admin")
                    .addFormDataPart("file", "555.txt", RequestBody.create(MediaType.parse("application/octet-stream"), file))
                    .build();
            // 3 封装 request
            Request request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .build();
            // 4 异步回调
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {

                }
            });
        }
    }
}
