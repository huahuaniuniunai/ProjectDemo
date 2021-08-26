package com.example.projectdemo.util.dialog;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projectdemo.callback.CallbackFunction;
import com.example.projectdemo.R;
import com.example.projectdemo.util.preference.PreferenceUtil;

/**
 * Created by AaronPasi on 2017/9/16.
 */
public class AlertDialogRemark extends AlertDialog implements View.OnClickListener {
    private EditText mEtPasswd;
    private Button mBtnCancel, mBtnConnect;
    private final Context mContext;
    private final CallbackFunction mCallBack;
    public AlertDialogRemark(Context context, CallbackFunction callBack) {
        super(context);
        mContext = context;
        mCallBack = callBack;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_remark);
        mEtPasswd = (EditText) findViewById(R.id.et_remark);
        //保证EditText能弹出键盘
        this.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        this.setCancelable(false);
        mBtnCancel = (Button) findViewById(R.id.remark_cancel);
        mBtnCancel.setOnClickListener(this);
        mBtnConnect = (Button) findViewById(R.id.remark_ok);
        mBtnConnect.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.remark_cancel:
                this.dismiss();
                break;
            case R.id.remark_ok:
                if (TextUtils.isEmpty(mEtPasswd.getText())) {
                    Toast.makeText(mContext, "备注不能为空", Toast.LENGTH_SHORT).show();
                } else {
                    this.dismiss();
                    Log.d("0825", "输入框内容："+mEtPasswd.getText().toString());
                    // 获取输入框内容并存储本地
                    PreferenceUtil.get().putCacheString("processDesc", mEtPasswd.getText().toString());
                    mCallBack.onStart();
                    mCallBack.onSuccess();
                }
                break;
            default:
                break;
        }
    }
}