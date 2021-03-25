package com.example.projectdemo;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectdemo.mvp.BaseActivity;
import com.example.projectdemo.util.bytetransform.NetUtils;
import com.example.projectdemo.util.log.LogUtil;
import com.gyf.immersionbar.BarHide;
import com.gyf.immersionbar.ImmersionBar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private Button  bt_login;
    private EditText et_account, et_password;
    private CheckBox remember;
    private TextView tv_forget_password;
    private String resultCode,resultMsg;
    private String account, pwd;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        initView();
        initEvent();

        boolean isRemember = pref.getBoolean("remember_password", false);
        if (isRemember) {
            // 将账号和密码都设置到文本框中
            String account = pref.getString("account", "");
            String password = pref.getString("password", "");
            et_account.setText(account);
            et_password.setText(password);
            remember.setChecked(true);
        }
    }

    private void initView() {
        et_account = findViewById(R.id.et_phone);
        et_password = findViewById(R.id.et_password);
        bt_login = findViewById(R.id.bt_login);
        remember = findViewById(R.id.tv_remember);
        tv_forget_password = findViewById(R.id.tv_forget_password);
    }

    private void initEvent() {
        bt_login.setOnClickListener(this);
        tv_forget_password.setOnClickListener(this);
    }

    @Override
    protected void setBar() {
        ImmersionBar.with(this)
                .titleBar(R.id.tb_login)
                .keyboardEnable(true)
                .navigationBarColor(R.color.page)
                .init();
    }

    private int login(String account, String pwd) {
        String urlPath = "https://fc375f69-e7bc-43f7-bc68-2ade2c772305.mock.pstmn.io/login";
        int id = 0;
        try {
            URL url = new URL(urlPath);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("account", account);
            jsonObject.put("pwd", pwd);
            //参数put到json
            String content = String.valueOf(jsonObject);
            //开启连接
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");//提交方式
            conn.setRequestProperty("Content-Type", "application/json");

            //写输出流，将要转的参数写入流
            OutputStream os = conn.getOutputStream();
            os.write(content.getBytes());
            os.close();
            int code = conn.getResponseCode();
            if (code == 200) {
                //读取返回的json
                InputStream inputStream = conn.getInputStream();
                //调用NetUtils() 将流转成String类型
                String json = NetUtils.readString(inputStream);
                LogUtil.d("login", json);
                JSONObject jsonObject1 = new JSONObject(json);
                resultCode = jsonObject1.getString("resultCode");
                resultMsg = jsonObject1.getString("resultMsg");
                LogUtil.d("login", "json返回状态码====" + resultCode);
                LogUtil.d("login", "json返回消息======" + resultMsg);
            } else {
                Toast.makeText(getApplicationContext(), "数据提交失败", Toast.LENGTH_SHORT).show();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return id;
    }


    public static void actionStart(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //点击取消让账号输入框获取焦点
            /*
            case R.id.cancleBtn:
                passwordEt.setText("");
                accountEt.setText("");
                accountEt.requestFocus();//点击取消让账号输入框获取焦点
                break;
                */

            case R.id.bt_login:
//                MainActivity.actionStart(LoginActivity.this);

                // 本地验证账号密码
                String account = et_account.getText().toString().trim();
                String password = et_password.getText().toString().trim();
                if (TextUtils.isEmpty(account) || TextUtils.isEmpty(password)) {
                    toast("账号或密码不能为空哦...亲！");
                } else {
                    if(account.equals("123456") && password.equals("123456")){
                        editor = pref.edit();
                        if (remember.isChecked()) {
                            editor.putBoolean("remember_password", true);
                            editor.putString("account", account);
                            editor.putString("password", password);
                        } else {
                            editor.clear();
                        }
                        editor.apply();
                        MainActivity.actionStart(LoginActivity.this);
                        finish();
                    }else{
                        Toast.makeText(LoginActivity.this, "很遗憾，账号或密码不正确！", Toast.LENGTH_LONG).show();
                    }
                }

                // 服务端验证账号密码
                /*
                account = accountEt.getText().toString();
                pwd = passwordEt.getText().toString();
                if (account.equals("") || pwd.equals("")) {
                    Toast.makeText(getApplicationContext(), "用户名或密码为空", Toast.LENGTH_SHORT).show();
                }
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int num = login(account, pwd);
                        if (resultCode.equals("0")) {
                            // 状态码为0，则表示验证成功，跳转首页
//                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
//                            startActivity(intent);
                            MainActivity.actionStart(LoginActivity.this);
                        } else {
                            Toast.makeText(getApplicationContext(), "用户名或密码错误", Toast.LENGTH_SHORT).show();
                        }
                    }

                });
                thread.start();
                */
                break;
            case R.id.tv_forget_password:
                toast("账号和密码同为：123456");
                break;
            default:
                break;
        }
    }
}

