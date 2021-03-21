package com.example.bmicalculator;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private EditText etweight;
    private EditText etheight;
    private TextView etbmi;
    private TextView etzd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etweight = findViewById(R.id.weight);
        etheight = findViewById(R.id.height);
        etbmi = findViewById(R.id.bmiValue);
        etzd = findViewById(R.id.zdValue);
        Button btncompute = findViewById(R.id.compute);

        btncompute.setOnClickListener(V ->{
            final double weight =  Double.valueOf(String.valueOf(etweight.getText()));
            final double height =  Double.valueOf(String.valueOf(etheight.getText()));

            //  计算公式：bmi = 体重公斤数/身高米数的平方
            etbmi.setText(String.valueOf(weight/(height*height)));

        });
    }
}