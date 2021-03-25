package com.example.loginactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private  EditText etUsername;
    private  EditText etPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
       //1.获取界面的控件对象
        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);
        Button btnLogin = findViewById(R.id.btnLogin);
        //2.监听登录按钮的点击事件
        btnLogin.setOnClickListener(V ->{
        //3.登录的业务逻辑处理
        //3.1获取编辑框输入的数据
        final String username = etUsername.getText().toString();
        final String password = etPassword.getText().toString();
        //3.2判断用户名密码是否正确，根据判断结果进行处理，成功则跳转，失败则给出提示
        if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
            Toast.makeText(LoginActivity.this,"用户名或密码不能为空",Toast.LENGTH_SHORT).show();
        }else if(!username.equals("123") || !password.equals("123")){
            Toast.makeText(LoginActivity.this,"用户名或密码错误",Toast.LENGTH_SHORT).show();
            //提高用户体验,清空输入框，光标移动到密码框内
            etPassword.setText("");
            etPassword.requestFocus();
        } else{
            //成功后将username传递给限一个Activity界面
            //使用intent对象传递数据
            final Intent intent = new Intent(LoginActivity.this,InfoActivity.class);
            intent.putExtra("username",username);
            startActivity(intent);
            Toast.makeText(LoginActivity.this,"登录信息" + username + "," + password,Toast.LENGTH_SHORT);
            finish();
        }
        });
    }
}