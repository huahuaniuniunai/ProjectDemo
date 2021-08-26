package com.example.projectdemo.http.async;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.example.projectdemo.application.MyApplication;
import com.example.projectdemo.callback.CallbackFunction;
import com.example.projectdemo.util.preference.PreferenceUtil;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.ByteArrayEntity;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;

public class ClientAsk {
    private final Activity activity;
    private String userId;
    private final String token;
    private String questionDesc;
    private String userName;
    private String orgId;
    private String classCode;
    private String busiNo;
    private String password;
    private String ip;
    private String sn;
    private final CallbackFunction askCallback;
    private String evaluationOpinion;
    private String questionId;
    private int totalEvaluation;
    private String snOld;

    public ClientAsk(Activity activity, String token, CallbackFunction askCallback) {
        this.activity = activity;
        this.token = token;
        this.askCallback = askCallback;
    }

    public ClientAsk(Activity activity, String userId, String token, CallbackFunction askCallback) {
        this.activity = activity;
        this.userId = userId;
        this.token = token;
        this.askCallback = askCallback;
    }

    public ClientAsk(Activity activity, String userId, String token, String questionDesc, CallbackFunction askCallback) {
        this.activity = activity;
        this.userId = userId;
        this.token = token;
        this.questionDesc = questionDesc;
        this.askCallback = askCallback;
    }

    public ClientAsk(Activity activity, String userId, String token, String evaluationOpinion, String questionId, int totalEvaluation, CallbackFunction askCallback) {
        this.activity = activity;
        this.userId = userId;
        this.token = token;
        this.evaluationOpinion = evaluationOpinion;
        this.questionId = questionId;
        this.totalEvaluation = totalEvaluation;
        this.askCallback = askCallback;
    }

    // post请求：带请求头并以JSON数据格式的请求
    public void evaluateCommit() {
        askCallback.onStart();// 启动回调方法
        String userId = PreferenceUtil.get().getCacheString("accId", "");// 取数据
        String token = PreferenceUtil.get().getCacheString("token", "");
        Log.d("demo", userId+"#"+token+"#"+evaluationOpinion+"#"+totalEvaluation+"#"+questionId);
        String url = null;
        JSONObject jsonObject = new JSONObject();// 封装JSON格式
        try {
            jsonObject.put("evalUserId", userId);
            jsonObject.put("questionId", questionId);
            jsonObject.put("totalEvaluation", totalEvaluation);
            jsonObject.put("evaluationOpinion", evaluationOpinion);
            jsonObject.put("handlingSpeed", "5");
            jsonObject.put("questionIsover", "5");
            jsonObject.put("staffAttitude", "5");
            jsonObject.put("resumeAttentiveness", "5");
            jsonObject.put("professionalEvaluation", "5");
            jsonObject.put("evalUserType", "1");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        ByteArrayEntity entity = null;
        entity = new ByteArrayEntity(jsonObject.toString().getBytes(StandardCharsets.UTF_8));
        entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        url = HttpInterfacesUrl.EVALUATE_COMMIT;
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("token", token);
        client.post(activity, url, entity, "application/json", new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    String code = response.getString("code");
                    Log.d("demo", "匿名评价接口状态："+code);
                    if ("200".equals(code)) {
                        askCallback.onSuccess();// 进入回调成功方法
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.d("demo", throwable.toString()+"###"+errorResponse.toString());
                Toast.makeText(MyApplication.getContext(), "评价失败"+throwable.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // get请求：带请求头并以表单数据格式的请求
    public void getEvaluateNum() {
        askCallback.onStart();
        String url = null;
        RequestParams params = new RequestParams();// 封装表单格式
        params.put("userId", userId);
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("token", token);
        url = HttpInterfacesUrl.LIST_NUM;
        client.get(activity, url, params, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    String state = response.getString("code");
                    if ("200".equals(state)) {
                        JSONArray array = response.getJSONArray("data");
                        int num = array.length();
                        Log.d("demo", "未评价工单数量:"+num);
                        if (num > 10) {
                            Toast.makeText(MyApplication.getContext(), "待评价单子超过10个，请先去评价再来提问！", Toast.LENGTH_SHORT).show();
                        } else {
                            askCallback.onSuccess();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Toast.makeText(MyApplication.getContext(), "获取未评价工单数量接口："+throwable.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    // get请求：只带请求头、不带数据的请求
    public void askWhy() {
        askCallback.onStart();
        String url = null;
        url = HttpInterfacesUrl.ASK_WHY;
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("token", token);
        client.get(activity, url, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    String state = response.getString("code");
                    Log.d("askWhy","提问原因返回数据："+state);
                    if ("200".equals(state)) {
                        JSONObject jsonObject = response.getJSONObject("data");
                        JSONArray jsonArray = jsonObject.getJSONArray("list");
                        JSONObject object = (JSONObject) jsonArray.get(0);
                        JSONArray childrenArray = object.getJSONArray("children");
                        JSONObject childrenObject = (JSONObject) childrenArray.get(0);
                        JSONArray childrenArr = childrenObject.getJSONArray("children");
                        JSONObject childrenObj = (JSONObject) childrenArr.get(0);
                        JSONArray list = childrenObj.getJSONArray("children");
                        for (int i = 0; i < list.length(); i++) {
                            JSONObject obj = (JSONObject) list.get(i);
                            String name = obj.getString("name");
                            String code = obj.getString("code");
                            switch (code) {
                                case "sop_q_type_jk_zj_kd_rzfs":
                                    PreferenceUtil.get().putCacheString("sop_q_type_jk_zj_kd_rzfs", name);// 存数据
                                    break;
                                case "sop_q_type_jk_zj_kd_gmzt":
                                    PreferenceUtil.get().putCacheString("sop_q_type_jk_zj_kd_gmzt", name);
                                    break;
                                case "sop_q_type_jk_zj_kd_pon":
                                    PreferenceUtil.get().putCacheString("sop_q_type_jk_zj_kd_pon", name);
                                    break;
                                case "sop_q_type_jk_zj_kd_ggl":
                                    PreferenceUtil.get().putCacheString("sop_q_type_jk_zj_kd_ggl", name);
                                    break;
                                case "sop_q_type_jk_zj_kd_gmsjzz":
                                    PreferenceUtil.get().putCacheString("sop_q_type_jk_zj_kd_gmsjzz", name);
                                    break;
                                case "sop_q_type_jk_zj_kd_kdk":
                                    PreferenceUtil.get().putCacheString("sop_q_type_jk_zj_kd_kdk", name);
                                    break;
                                case "sop_q_type_jk_zj_kd_kd":
                                    PreferenceUtil.get().putCacheString("sop_q_type_jk_zj_kd_kd", name);
                                    break;
                                case "sop_q_type_jk_zj_kd_yzm":
                                    PreferenceUtil.get().putCacheString("sop_q_type_jk_zj_kd_yzm", name);
                                    break;
                                case "sop_q_type_jk_zj_kd_qt":
                                    PreferenceUtil.get().putCacheString("sop_q_type_jk_zj_kd_qt", name);
                                    break;
                            }
                        }
                    }
                    askCallback.onSuccess();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.d("askWhy","提问原因失败返回数据："+errorResponse.toString());
            }
        });
    }

    public void ask(String fileIds) {
        askCallback.onStart();
        String url = null;
        userName = PreferenceUtil.get().getCacheString("userName", "");
        orgId = PreferenceUtil.get().getCacheString("orgId", "");
        classCode = PreferenceUtil.get().getCacheString("classCode", "");
        busiNo = PreferenceUtil.get().getCacheString("busiNo", "");
        password = PreferenceUtil.get().getCacheString("password", "");
        ip = PreferenceUtil.get().getCacheString("ip", "");
        sn = PreferenceUtil.get().getCacheString("sn", "");
        snOld = PreferenceUtil.get().getCacheString("snOld", "");
        JSONObject jsonObject = new JSONObject();
        try {
            // 传参数
            jsonObject.put("clientType", 1);       //移动端
            jsonObject.put("userId",userId);             //账号
            jsonObject.put("questionDesc", questionDesc);//问题描述
            jsonObject.put("userName", userName);        //用户名
            jsonObject.put("orgId", orgId);              //用户组织ID
            jsonObject.put("classCode", classCode);      //提问问题类型
            jsonObject.put("busiNo", busiNo);           // 宽带账号
            jsonObject.put("password", password);       //PW
            jsonObject.put("ip", ip);                   //IP
            jsonObject.put("sn", sn);                   //SN
            jsonObject.put("snOld", snOld);                   //SN
            jsonObject.put("fileIds", fileIds);                   //SN

        } catch (JSONException e) {
            e.printStackTrace();
        }
        ByteArrayEntity entity = null;
        entity = new ByteArrayEntity(jsonObject.toString().getBytes(StandardCharsets.UTF_8));
        entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        url = HttpInterfacesUrl.ASK_QUESTION;
        AsyncHttpClient client = new AsyncHttpClient();
        client.addHeader("token", token);
        client.post(activity, url, entity,"application/json", new JsonHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        try {
                            String code = response.getString("code");
                            Log.d("0509","提问返回状态码："+code);
                            if ("200".equals(code)) {
                                JSONObject obj = response.getJSONObject("data");
                                String questionId = obj.getString("questionId");
                                String sessionId = obj.getString("sessionId");
                                Log.d("demo","提问返回数据："+questionId +"***"+ sessionId);
                                askCallback.onSuccess();
                            }else {
                                askCallback.onFailed(response.getString("msg"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

//    public void check() {
//        askCallback.onStart();
//        String url = null;
//        JSONObject jsonObject = new JSONObject();
//        try {
//            // 传参数
//            jsonObject.put("nodeType", 1);
//            jsonObject.put("orgId", "9000003161");
//            jsonObject.put("userId",userId);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        ByteArrayEntity entity = null;
//        try {
//            entity = new ByteArrayEntity(jsonObject.toString().getBytes("UTF-8"));
//            entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        url = HttpInterfacesUrl.ASK_QUESTION;
//        AsyncHttpClient client = new AsyncHttpClient();
//        client.addHeader("token", token);
//        client.post(ChatApplication.getContext(), url, entity,"application/json",
//                new JsonHttpResponseHandler() {
//                    @Override
//                    public void onSuccess(JSONObject responseObj) {
//                        Log.d("yxsdk","选择联系人下发数据:"+responseObj.toString());
//                        try {
//                            String code = responseObj.getString("code");
////                            JSONObject obj = responseObj.getJSONObject("data");
////                            String accId = obj.getString("accId");
////                            String accToken = obj.getString("accToken");
//                            if ("200".equals(code)) {
////                                PreferenceUtil.get().putCacheString("accId", accId);
////                                PreferenceUtil.get().putCacheString("accToken", accToken);
//                                Log.d("yxsdk","选择联系人状态码:"+code);
//                                askCallback.onSuccess();
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                });
//    }
}
