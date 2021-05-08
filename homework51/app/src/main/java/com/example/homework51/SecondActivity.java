package com.example.homework51;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.homework51.databinding.ActivitySecondBinding;

public class SecondActivity extends AppCompatActivity{
    //绑定之前记得 gradle 导入
    private ActivitySecondBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = this.getIntent();
        String sum = (intent.getStringExtra("sum"));
        System.out.println(sum);
        Toast.makeText(SecondActivity.this,"计算结果为"+sum, Toast.LENGTH_SHORT).show();
        binding.Sum.setText("计算结果为：" + sum);
        binding.btnRet.setOnClickListener(v ->{
            finish();
        });
    }
}