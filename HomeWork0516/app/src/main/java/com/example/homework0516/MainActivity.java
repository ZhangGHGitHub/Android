package com.example.homework0516;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.homework0516.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

/**
 * 1、请使用CoordinatorLayout控件，协调FloatingActionButton和Snackbar的显示，当用户点击页面底部的FloatingActionButton时，
 * 弹出Snackbar，提示“是否新增一条短信？”，同时FloatingActionButton上移，使Snackbar不会覆盖FloatingActionButton。
 *
 * */
public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnFirst.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FirstActivity.class);
            startActivity(intent);
        });
        binding.btnSecond.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        });

    }



}