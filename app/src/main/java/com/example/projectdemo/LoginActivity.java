package com.example.projectdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projectdemo.mvp.BaseActivity;
import com.example.projectdemo.util.bytetransform.NetUtils;
import com.example.projectdemo.util.log.LogUtil;
import com.gyf.immersionbar.ImmersionBar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LoginActivity extends BaseActivity {

    private EditText accountEt, passwordEt;
    private Button  loginBtn;
    private String resultCode,resultMsg;
    private String account, pwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        accountEt = findViewById(R.id.et_phone);
        passwordEt = findViewById(R.id.et_password);
        loginBtn = this.findViewById(R.id.bt_login) ;

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /**
                 * 本地验证账号密码
                 */
                String account = accountEt.getText().toString().trim();
                String password = passwordEt.getText().toString().trim();
                if(account.equals("admin") && password.equals("123456")){
                    Toast.makeText(LoginActivity.this, "恭喜，登陆成功！", Toast.LENGTH_LONG).show();
                    MainActivity.actionStart(LoginActivity.this);
                }else{
                    Toast.makeText(LoginActivity.this, "很遗憾，账号或密码不正确！", Toast.LENGTH_LONG).show();
                }

                /**
                 * 服务端验证账号密码
                 */
//                account = accountEt.getText().toString();
//                pwd = passwordEt.getText().toString();
//                if (account.equals("") || pwd.equals("")) {
//                    Toast.makeText(getApplicationContext(), "用户名或密码为空", Toast.LENGTH_SHORT).show();
//                }
//                Thread thread = new Thread(new Runnable() {
//                    @Override
//                    public void run() {
//                        int num = login(account, pwd);
//                        if (resultCode.equals("0")) {
//                            // 状态码为0，则表示验证成功，跳转首页
////                            Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
////                            startActivity(intent);
//                            MainActivity.actionStart(LoginActivity.this);
//                        } else {
//                            Toast.makeText(getApplicationContext(), "用户名或密码错误", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//
//                });
//                thread.start();
            }
        });
//        // 点击取消后清空数据并让输入框获取焦点
//        cancleBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                passwordEt.setText("");
//                accountEt.setText("");
//                accountEt.requestFocus();//让账号输入框获取焦点
//            }
//        });
    }

    @Override
    protected void setBar() {
        ImmersionBar.with(this).titleBar(R.id.tb_login).keyboardEnable(true).init();
    }

    private int login(String account, String pwd) {
        String urlPath = "http://58.19.117.122:12706/HbHxAppService/resLogin.htm";
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
}

