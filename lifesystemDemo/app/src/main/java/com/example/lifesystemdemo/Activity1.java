package com.example.lifesystemdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.lifesystemdemo.databinding.Activity1Binding;

public class Activity1 extends AppCompatActivity {

    private final String TAG = "Activity 1";
    private Activity1Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = Activity1Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Log.i(TAG, "onCreate() is call");

        binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建 Intent 对象 （跳转对象）
                Intent intent = new Intent(Activity1.this, Activity2.class);
                //启动 Activity2
                startActivity(intent);
                // 设置Intent的class方式
//                Intent intent = new Intent();
//                intent.setClassName("com.example.activity.lifecycle", "com.example.activity.lifecycle.Activity2");
//                startActivity(intent);

                // 隐式Intent
//                Intent intent = new Intent();
//                intent.setAction("com.example.activity.lifecycle.ACTION_START");
//                startActivity(intent);
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart() is called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume() is called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause() is called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop() is called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy() is called");
    }
}