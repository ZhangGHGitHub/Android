package com.example.calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    // 数字
    Button  btn1,
            btn2,
            btn3,
            btn4,
            btn5,
            btn6,
            btn7,
            btn8,
            btn9,
            btn0;
    //操作符
    Button add,sub, mul,div, dot,equ,rem,backspace,c;
    // 显示文本
    EditText resultText;

    Object Result = 0;          //计算结果

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 获取页面上的控件
        btn1 = findViewById(R.id.btn_1);
        btn2 = findViewById(R.id.btn_2);
        btn3 = findViewById(R.id.btn_3);
        btn4 = findViewById(R.id.btn_4);
        btn5 = findViewById(R.id.btn_5);
        btn6 = findViewById(R.id.btn_6);
        btn7 = findViewById(R.id.btn_7);
        btn8 = findViewById(R.id.btn_8);
        btn9 = findViewById(R.id.btn_9);
        btn0 = findViewById(R.id.btn_0);
        add = findViewById(R.id.btn_add);
        sub = findViewById(R.id.btn_sub);
        mul = findViewById(R.id.btn_mul);
        div = findViewById(R.id.btn_div);
        equ = findViewById(R.id.btn_equ);
        dot = findViewById(R.id.btn_dot);
        c = findViewById(R.id.btn_c);
        rem = findViewById(R.id.btn_rem);
        backspace = findViewById(R.id.btn_backspace);
        resultText = findViewById(R.id.result);


        // 按钮的单击事件
        btn1.setOnClickListener(new Click());
        btn2.setOnClickListener(new Click());
        btn3.setOnClickListener(new Click());
        btn4.setOnClickListener(new Click());
        btn5.setOnClickListener(new Click());
        btn6.setOnClickListener(new Click());
        btn7.setOnClickListener(new Click());
        btn8.setOnClickListener(new Click());
        btn9.setOnClickListener(new Click());
        btn0.setOnClickListener(new Click());
        add.setOnClickListener(new Click());
        sub.setOnClickListener(new Click());
        mul.setOnClickListener(new Click());
        div.setOnClickListener(new Click());
        equ.setOnClickListener(new Click());
        dot.setOnClickListener(new Click());
        c.setOnClickListener(new Click());
        backspace.setOnClickListener(new Click());
        resultText.setOnClickListener(new Click());
        rem.setOnClickListener(new Click());

    }
    class Click implements View.OnClickListener{
        String myStringEqu ;
        @Override
        public void onClick(View v) {
            switch (v.getId()) {                //switch循环获取点击按钮后的值
                case R.id.btn_0:                //获取，0-9、小数点，并在编辑框显示
                    resultText.setText(resultText.getText().toString().concat("0"));
                    break;
                case R.id.btn_1:
                    resultText.setText(resultText.getText().toString().concat("1"));
                    break;
                case R.id.btn_2:
                    resultText.setText(resultText.getText().toString().concat("2"));
                    break;
                case R.id.btn_3:
                    resultText.setText(resultText.getText().toString().concat("3"));
                    break;
                case R.id.btn_4:
                    resultText.setText(resultText.getText().toString().concat("4"));
                    break;
                case R.id.btn_5:
                    resultText.setText(resultText.getText().toString().concat("5"));
                    break;
                case R.id.btn_6:
                    resultText.setText(resultText.getText().toString().concat("6"));
                    break;
                case R.id.btn_7:
                    resultText.setText(resultText.getText().toString().concat("7"));
                    break;
                case R.id.btn_8:
                    resultText.setText(resultText.getText().toString().concat("8"));;
                    break;
                case R.id.btn_9:
                    resultText.setText(resultText.getText().toString().concat("9"));
                    break;
                case R.id.btn_dot:
                    resultText.setText(resultText.getText().toString().concat("."));
                    break;
                case R.id.btn_add:             //判断，使用加减乘除的操作符
                    resultText.setText(resultText.getText().toString().concat("+"));
                    break;
                case R.id.btn_sub:
                    resultText.setText(resultText.getText().toString().concat("-"));
                    break;
                case R.id.btn_mul:
                    resultText.setText(resultText.getText().toString().concat("*"));
                    break;
                case R.id.btn_div:
                    resultText.setText(resultText.getText().toString().concat("/"));
                    break;
                case R.id.btn_rem:
                    resultText.setText(resultText.getText().toString().concat("%"));
                    break;
                case R.id.btn_c:                 //清除，将编辑框文本显示为空
                    resultText.setText(null);
                    break;
                case R.id.btn_backspace:                 //清除，将编辑框文本显示为空
                    try {
                        myStringEqu = resultText.getText().toString();
                        myStringEqu = new StringBuilder(myStringEqu).deleteCharAt(myStringEqu.length()-1).toString();
                        resultText.setText(myStringEqu);
                    }catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case R.id.btn_equ:                   //计算，以操作符为判断，选择所需的运算，并将结果输出
                    myStringEqu = resultText.getText().toString();
                    if(myStringEqu.endsWith("+") || myStringEqu.endsWith("-") || myStringEqu.endsWith("*")
                            || myStringEqu.endsWith("/") || myStringEqu.endsWith("+") ||myStringEqu.endsWith("=")
                            || myStringEqu.endsWith("%") || myStringEqu.endsWith("."))
                    {
                        myStringEqu = new StringBuilder(myStringEqu).deleteCharAt(myStringEqu.length()-1).toString();
                        break;
                    }
                    resultText.setText(myStringEqu + "=" + (AviatorEvaluator.execute((myStringEqu))));    //将结果完整输出
                    break;
                default:
                    break;
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(AviatorEvaluator.execute("6/2"));
    }

}