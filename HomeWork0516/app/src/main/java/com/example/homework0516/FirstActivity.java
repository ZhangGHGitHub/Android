package com.example.homework0516;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.homework0516.databinding.ActivityFirstBinding;
import com.google.android.material.snackbar.Snackbar;

public class FirstActivity extends AppCompatActivity {

    private ActivityFirstBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFirstBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        onclick();
    }
    private void onclick() {
        binding.fabButton.setOnClickListener(view ->{
            Snackbar.make(view, "是否新增一条短信？", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        });
    }
}