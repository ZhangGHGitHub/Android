package com.example.loginactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;

import com.example.loginactivity.databinding.ActivityInfoBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ViewBindingActivity extends AppCompatActivity implements View.OnClickListener{
    private static final String PHONE_PATTERN = "^1[3-9]\\d{9}$";
    private ActivityInfoBinding binding;
    private boolean isRight = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取视图绑定对象
        binding = ActivityInfoBinding.inflate(getLayoutInflater());
        LinearLayout root = binding.getRoot();
        setContentView(root);

        binding.buttonSub.setOnClickListener(this);
        binding.etIphone.setOnFocusChangeListener((v, hasFocus) ->{
            if(!hasFocus){
                String phone = binding.etIphone.getText().toString();
                if(!TextUtils.isEmpty(phone) && !validatePhone(phone)){
                    binding.iphoneLayout.setError("请输入正确的手机号！");
                    binding.etIphone.setText("");
                    isRight = false;
                }else {
                    isRight = true;
                    binding.etIphone.setError("");
                }
            }
        });
        //获取传递的值
        final Intent Intent = getIntent();
        String username = Intent.getStringExtra("username");
        if (!TextUtils.isEmpty(username)){
            binding.etName.setText(username);
        }

    }
    public String getCheckBoxText(){
        String result = "";
        if(binding.math.isChecked()){
            result += binding.math.getText() + ",";
        }
        if(binding.java.isChecked()){
            result += binding.java.getText() + ",";
        }
        if(binding.english.isChecked()){
            result += binding.english.getText() + ",";
        }
        if(binding.Android.isChecked()){
            result += binding.Android.getText() + ",";
        }

        return result.substring(0,result.length() - 1);
    }

    @Override
    public void onClick(View v) {
        int id = binding.radioButton.getCheckedRadioButtonId();
        String sex = binding.radioButtonMan.getText().toString();
        if(id == R.id.radioButton_woman){
            sex = binding.radioButtonWoman.getText().toString();
        }
        if(isRight){
            String tips = "用户名：" + binding.etName.getText()
                    +   ",手机号：" + binding.etIphone.getText()
                    +   ",性别："  + sex + "\n喜欢的课程有："
                    +   getCheckBoxText();
            Snackbar.make(binding.mainLayout, tips, Snackbar.LENGTH_SHORT).setAction("确认", null).show();
        }
    }

    public boolean validatePhone(String phone){
        Pattern pattern = Pattern.compile(PHONE_PATTERN);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }
}