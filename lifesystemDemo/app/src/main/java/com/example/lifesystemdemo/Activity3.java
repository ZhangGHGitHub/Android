package com.example.lifesystemdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.lifesystemdemo.databinding.Activity2Binding;
import com.example.lifesystemdemo.databinding.Activity3Binding;

public class Activity3 extends AppCompatActivity {



    private final String TAG = "Activity 3";
    private Activity3Binding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = Activity3Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int i = Log.i(TAG, "onCreate() is call");

        binding.button.setOnClickListener(new ButtonClickListener());
    }
    static class ButtonClickListener implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            // 响应处理代码
        }
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