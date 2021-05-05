package com.example.lifesystemdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.example.lifesystemdemo.databinding.ActivitySecondBinding;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    private ActivitySecondBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecondBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.btnRet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("data","返回FirstActivity");
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        final Intent intent = getIntent();
//        简单传值
        String name = intent.getStringExtra("data");
        if (name != null) {
            binding.extra.setText("获取的字符串为：" + name);
        }
        //传对象
        User user = (User)intent.getSerializableExtra("User");
//        if (user != null) {
//            binding.extra.setText("获取的对象：" + user.toString());
//        }

        //集合
        final ArrayList<Integer> arrayList = intent.getIntegerArrayListExtra("list");
        if (arrayList != null) {
            binding.arrayList.setText("获取的数据列表为：" + arrayList.toString());
        }
        //第三种buundle
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String Scalss = bundle.getString("班级");
            ArrayList<Integer> arrayList1= bundle.getIntegerArrayList("分数");
            User user1 = (User) bundle.getSerializable("user");
            binding.bundle.setText("获取的Bundle数据列表为：" + Scalss + "," + arrayList1 + user.toString());
        }


    }
}