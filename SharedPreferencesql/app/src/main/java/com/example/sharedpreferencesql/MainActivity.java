package com.example.sharedpreferencesql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.sharedpreferencesql.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final  String name=binding.etName.getText().toString();
                final String phone=binding.etPhone.getText().toString();
                final String age=binding.etAge.getText().toString();
                Toast.makeText(MainActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
                remeber(name,phone,age);
            }
        });
        checkRemember();
    }
    private  void remeber(String name,String phone,String age){
        SharedPreferences sp=getSharedPreferences("user",MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.putString("name",name);
        edit.putString("phone",phone);
        edit.putString("age",age);
        edit.apply();
    }
    private void checkRemember() {
        SharedPreferences sp=getSharedPreferences("user",MODE_PRIVATE);
        binding.etName.setText(sp.getString("name",""));
        binding.etPhone.setText(sp.getString("phone",""));
        binding.etAge.setText(sp.getString("age",""));
    }
    private void clear(){
        SharedPreferences sp=getSharedPreferences("user",MODE_PRIVATE);
        SharedPreferences.Editor edit = sp.edit();
        edit.remove("name");
        edit.clear();
        edit.apply();
    }

}