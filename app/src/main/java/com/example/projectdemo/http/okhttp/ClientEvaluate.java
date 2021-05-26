package com.example.projectdemo.http.okhttp;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.example.projectdemo.application.MyApplication;
import com.example.projectdemo.callback.CallbackFunction;
import com.example.projectdemo.http.async.HttpInterfacesUrl;
import com.example.projectdemo.util.preference.PreferenceUtil;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.ByteArrayEntity;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ClientEvaluate {
    private Activity activity;
    private String userId;
    private String token;
    private String questionDesc;
    private String userName;
    private String orgId;
    private String classCode;
    private String busiNo;
    private String password;
    private String ip;
    private String sn;
    private CallbackFunction askCallback;
    private String evaluationOpinion;
    private String questionId;
    private int totalEvaluation;
    private String snOld;

    public ClientEvaluate(Activity activity, String token, CallbackFunction askCallback) {
        this.activity = activity;
        this.token = token;
        this.askCallback = askCallback;
    }

    public ClientEvaluate(Activity activity, String userId, String token, CallbackFunction askCallback) {
        this.activity = activity;
        this.userId = userId;
        this.token = token;
        this.askCallback = askCallback;
    }

    public ClientEvaluate(Activity activity, String userId, String token, String questionDesc, CallbackFunction askCallback) {
        this.activity = activity;
        this.userId = userId;
        this.token = token;
        this.questionDesc = questionDesc;
        this.askCallback = askCallback;
    }

    public ClientEvaluate(Activity activity, String userId, String token, String evaluationOpinion, String questionId, int totalEvaluation, CallbackFunction askCallback) {
        this.activity = activity;
        this.userId = userId;
        this.token = token;
        this.evaluationOpinion = evaluationOpinion;
        this.questionId = questionId;
        this.totalEvaluation = totalEvaluation;
        this.askCallback = askCallback;
    }

    // get请求：异步调用只带请求头的请求
    public void getEvaluate(String url) {
        askCallback.onStart();
        // 1、获取OkHttpClient对象
        OkHttpClient client = new OkHttpClient();
        // 2、设置请求
        Request request = new Request.Builder()
                .get()
                .addHeader(token,"application/json; charset=utf-8")
                .url(url)
                .build();
        // 3、封装call
        Call call = client.newCall(request);
        // 4、异步调用,并设置回调函数
        call.enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (response!=null && response.isSuccessful()){
                    // TODO
                    final String responseString = response.body().string();
                    Log.d("okhttp", "okhttp响应的数据："+responseString);
                    askCallback.onSuccess();
                }
            }

            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                // TODO
                call.cancel();
            }
        });
        //同步调用,返回Response,可能引起ANR异常，会抛出IO异常（不推荐使用）
        /*
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = call.execute();// 同步调用必须在子线程中执行
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        */
    }


}
