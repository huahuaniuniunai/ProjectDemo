package com.example.projectdemo.http.okhttp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.example.projectdemo.R;
import com.example.projectdemo.application.MyApplication;
import com.example.projectdemo.callback.CallbackFunction;
import com.example.projectdemo.http.async.ClientAsk;
import com.example.projectdemo.http.async.HttpInterfacesUrl;
import com.example.projectdemo.util.dialog.MyProgressDialog;
import com.example.projectdemo.util.preference.PreferenceUtil;
import com.example.projectdemo.util.res.ResourceUtils;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class EvaluateActivity extends AppCompatActivity {
    private MyProgressDialog progressdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluate);
    }

    // 登录请求
    private void login() {
        String url = null;
        String evaluationOpinion = "非常好";
        String score = "5";
        PreferenceUtil.get().putCacheString("accId", evaluationOpinion);
        PreferenceUtil.get().putCacheString("token", score);
        String userId = PreferenceUtil.get().getCacheString("accId", "");
        String token = PreferenceUtil.get().getCacheString("token", "");
        String json = new Gson().toJson(new EndBean("1", userId, "555555555", evaluationOpinion, "无", score));
        url = "http://www.baidu.com";
        HttpUtil.sendOkHttpRequest(url, token, json, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                EvaluateActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        updateUI(2, "");
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String data = response.body().string();
                try {
                    JSONObject jsonObject = new JSONObject(data);
                    String code = jsonObject.getString("code");
                    Log.d("0524", "办结完成状态码："+code);
                    String msg = jsonObject.getString("data");
                    EvaluateActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            updateUI(1, msg);
                        }
                    });
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });
    }

    private void updateUI(int flag,String msg){
        EvaluateActivity.this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (flag==1){
                    Toast.makeText(EvaluateActivity.this, msg, Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(EvaluateActivity.this, "办结失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void askQuestion(String fileIds) {
        String userId = PreferenceUtil.get().getCacheString("accId", "");
        String token = PreferenceUtil.get().getCacheString("token", "");
        String questionDesc = PreferenceUtil.get().getCacheString("questionDesc", "");
        String url = HttpInterfacesUrl.EVALUATE_LIST;
        new ClientEvaluate(this, userId, token, questionDesc, new CallbackFunction() {
            @Override
            public void onStart() {
                showProgressDialog(ResourceUtils.getString(R.string.asking));
            }

            @Override
            public void onSuccess() {
                dismissProgressDialog();

                // TODO
//                Intent intent = new Intent(this, AskingListActivity.class);
//                startActivity(intent);
//                finish();
            }

            @Override
            public void onFailed(String msg) {
                dismissProgressDialog();
                Toast.makeText(MyApplication.getContext(), msg, Toast.LENGTH_SHORT).show();
            }
        }).getEvaluate(url);
    }

    // 显示加载框
    public void showProgressDialog(String str) {
        String text = str;
        if (TextUtils.isEmpty(text))
            text = ResourceUtils.getString(R.string.loading);
        if (progressdialog != null && progressdialog.isShowing()) {
            progressdialog.dismiss();
            progressdialog = null;
        }
        progressdialog = new MyProgressDialog(this, text);
        progressdialog.setCanceledOnTouchOutside(false);
        progressdialog.setCancelable(false);
        progressdialog.show();
    }

    // 隐藏加载框
    public void dismissProgressDialog() {
        if (progressdialog != null && progressdialog.isShowing()) {
            progressdialog.dismiss();
            progressdialog = null;
        }
    }
}