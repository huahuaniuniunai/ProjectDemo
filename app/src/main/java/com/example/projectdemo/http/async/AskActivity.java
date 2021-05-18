package com.example.projectdemo.http.async;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Toast;

import com.example.projectdemo.R;
import com.example.projectdemo.application.MyApplication;
import com.example.projectdemo.callback.CallbackFunction;
import com.example.projectdemo.util.dialog.MyProgressDialog;
import com.example.projectdemo.util.preference.PreferenceUtil;
import com.example.projectdemo.util.res.ResourceUtils;

public class AskActivity extends AppCompatActivity {
    private MyProgressDialog progressdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask);
    }

    private void askQuestion(String fileIds) {
        String userId = PreferenceUtil.get().getCacheString("accId", "");
        String token = PreferenceUtil.get().getCacheString("token", "");
        String questionDesc = PreferenceUtil.get().getCacheString("questionDesc", "");
        new ClientAsk(this, userId, token, questionDesc, new CallbackFunction() {
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
        }).ask(fileIds);
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