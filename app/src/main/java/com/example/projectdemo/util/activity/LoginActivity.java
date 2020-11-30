package com.example.projectdemo.util.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projectdemo.R;

public class LoginActivity extends AppCompatActivity {

    EditText accountEt, passwordEt;
    Button  loginBtn, cancleBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        accountEt = findViewById(R.id.account_et);
        passwordEt = findViewById(R.id.password_et);
        loginBtn = this.findViewById(R.id.loginbtn) ;
        cancleBtn = this.findViewById(R.id.canclebtn);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String account = accountEt.getText().toString().trim();
                String password = passwordEt.getText().toString().trim();
                if(account.equals("admin") && password.equals("123456")){
                    Toast.makeText(LoginActivity.this, "恭喜，登陆成功！", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(LoginActivity.this, "很遗憾，账号或密码不正确！", Toast.LENGTH_LONG).show();
                }
            }
        });

        cancleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                passwordEt.setText("");
                accountEt.setText("");
                accountEt.requestFocus();//让账号输入框获取焦点
            }
        });
    }

    public static void actionStart(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }
}

