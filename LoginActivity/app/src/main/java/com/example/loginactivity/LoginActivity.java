package com.example.loginactivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginactivity.databinding.ActivityInfoBinding;
import com.example.loginactivity.databinding.ActivityLoginBinding;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class LoginActivity extends AppCompatActivity {
    //private  EditText etUsername;
    //private  EditText etPassword;
    private ActivityLoginBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        initView();
        //1.获取界面的控件对象
        //etUsername = findViewById(R.id.etUsername);
        //etPassword = findViewById(R.id.etPassword);
        //Button btnLogin = findViewById(R.id.btnLogin);

        //2.监听登录按钮的点击事件
        initView();
        checkRemember();

    }

    private void initView() {
        binding.btnLogin.setOnClickListener(V ->{
            //3.登录的业务逻辑处理
            //3.1获取编辑框输入的数据
            final String username = binding.etUsername.getText().toString();
            final String password = binding.etPassword.getText().toString();
//            //3.2判断用户名密码是否正确，根据判断结果进行处理，成功则跳转，失败则给出提示
//            if(TextUtils.isEmpty(username) || TextUtils.isEmpty(password)){
//                Toast.makeText(LoginActivity.this,"用户名或密码不能为空",Toast.LENGTH_SHORT).show();
//
//            }else if(!username.equals("123") || !password.equals("123")){
//                Toast.makeText(LoginActivity.this,"用户名或密码错误",Toast.LENGTH_SHORT).show();
//                //提高用户体验,清空输入框，光标移动到密码框内
//                binding.etPassword.setText("");
//                binding.etPassword.requestFocus();
//            } else{
//                //成功后将username传递给限一个Activity界面
//                //使用intent对象传递数据 参数（第一个页面，第二个页面)
//                final Intent intent = new Intent(LoginActivity.this,ViewBindingActivity.class);
//                intent.putExtra("username",username);
//                startActivity(intent);
////            Toast.makeText(LoginActivity.this,"登录信息" + username + "," + password,Toast.LENGTH_SHORT);
//                finish();
//            }

            if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)) {
                if (binding.checkboxRem.isChecked()) {
                    remember(username,password);
                    final Intent intent = new Intent(LoginActivity.this,ViewBindingActivity.class);
                    intent.putExtra("username",username);
                    startActivity(intent);
                    Toast.makeText(LoginActivity.this,"登录信息" + username + "," + password,Toast.LENGTH_SHORT);
                    finish();
                }else {
                    clear();
                }

            }
        });
    }


    private void remember(String username, String password) {
//        SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
//        SharedPreferences.Editor edit = sp.edit();
//        edit.putString("username", username);
//        edit.putString("password", password);
//        edit.apply();

        try( FileOutputStream fos = openFileOutput("user.txt", Context.MODE_PRIVATE)){
            fos.write((username + "," + password).getBytes());
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos));
            writer.write(username + "," + password);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void checkRemember() {
//        SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
//        binding.etPassword.setText(sp.getString("username", ""));
//        binding.etUsername.setText(sp.getString("password", ""));
        try {
            FileInputStream fis = openFileInput("user.txt");
            if (fis.available() == 0) {
                Toast.makeText(this, "文件内容为空", Toast.LENGTH_SHORT).show();
                return;
            }
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String [] datas = reader.readLine().split(",");
            if (datas.length > 1) {
                binding.etUsername.setText(datas[0]);
                binding.etPassword.setText(datas[1]);
            }
            fis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    private void clear() {
//        SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
//        SharedPreferences.Editor editor = sp.edit();
//        editor.clear();
//        editor.apply();
        try {
            FileOutputStream fos = openFileOutput("user.txt", Context.MODE_PRIVATE);
            

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}