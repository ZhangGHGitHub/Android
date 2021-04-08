package com.example.loginactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.*;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InfoActivity extends AppCompatActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener{
    private LinearLayout linearLayout;
    private EditText etName, etPhone;
    private RadioGroup rgSex;
    private  CheckBox cbjava, cbandroid, cbenglish, cbmath;
    private static final String PHONE_PATTERN = "^1[3-9]\\d{9}$";
    private TextInputLayout phoneLayuout,nameLayout;
    // 存放复选框值
    private String selected = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        //初始化页面布局
        linearLayout = findViewById(R.id.main_layout);
        phoneLayuout = findViewById(R.id.iphone_layout);
        nameLayout = findViewById(R.id.name_layout);

        //初始化输入框,复选框,单选控件
        etName = findViewById(R.id.et_name);
        etPhone = findViewById(R.id.et_iphone);

        cbjava = findViewById(R.id.java);
        cbandroid = findViewById(R.id.Android);
        cbenglish = findViewById(R.id.english);
        cbmath = findViewById(R.id.math);

        rgSex = findViewById(R.id.radioButton);

        //初始化提交按钮
        Button submit = findViewById(R.id.button_sub);
        //设置监听
        submit.setOnClickListener(this);
        cbandroid.setOnCheckedChangeListener(this);
        cbenglish.setOnCheckedChangeListener(this);
        cbjava.setOnCheckedChangeListener(this);
        cbmath.setOnCheckedChangeListener(this);
        //获取传递的值
        final Intent Intent = getIntent();
        String username = Intent.getStringExtra("username");
        if (!TextUtils.isEmpty(username)){
            etName.setText(username);
        }
    }

    @Override
    public void onClick(View v) {
        //获取输入的值 ，trim()去掉空格
        String username = etName.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        if(username.isEmpty())
        {
            nameLayout.setError("姓名不能为空");
            etName.setText("");
            etName.requestFocus();
            return;
        }
        if(!validatePhone(phone)){
            phoneLayuout.setError("请输入正确的手机号！");
            etPhone.setText("");
            etPhone.requestFocus();
            return;
        }
        //获取radioButton的值
        int id = rgSex.getCheckedRadioButtonId();
        RadioButton btn = findViewById(id);
        String sex = btn.getText().toString();

        String tips = "UNVIEWBINDING用户名：" + username
                +   ",手机号：" + phone
                +   ",性别："  + sex + "\n喜欢的课程有："
                +   selected;
        //显示内容到Snackbar
        Snackbar.make(linearLayout, tips, Snackbar.LENGTH_LONG).setAction(
                "确定", new View.OnClickListener(){
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(InfoActivity.this, "信息已确认", Toast.LENGTH_SHORT).show();
                    }
                }).show();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        CheckBox checkBox = (CheckBox) buttonView;
        if(isChecked){
            selected += checkBox.getText().toString() + ",";
        }else {
            selected = selected.replace(checkBox.getText().toString() + ",", "");
        }
        Snackbar.make(linearLayout, selected, Snackbar.LENGTH_SHORT).show();
    }
    public boolean validatePhone(String phone){
        Pattern pattern = Pattern.compile(PHONE_PATTERN);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }
}