package com.example.bmicalculator;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;
import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import java.util.*;


public class MainActivity extends AppCompatActivity {
    private EditText etweight;
    private EditText etheight;
    private TextView etbmi;
    private TextView etzd;
    private RadioButton radioButton;
    private RadioGroup radioGroup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etweight = findViewById(R.id.weight);
        etheight = findViewById(R.id.height);
        etbmi = findViewById(R.id.bmiValue);
        etzd = findViewById(R.id.zdValue);
        //1.找到RadioGroup
        radioGroup = findViewById(R.id.radiogroup);
        //给RadioGroup设置选中想改变监听
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

            }
        });
        //通过getCheckedRadioButonId()方法找到RadioGroup中被选中的radiobutton
        radioButton = findViewById(radioGroup.getCheckedRadioButtonId());
        String sex = radioButton.getText().toString();
        Button btncompute = findViewById(R.id.compute);

        btncompute.setOnClickListener(V ->{
            final double weight =  Double.valueOf(String.valueOf(etweight.getText()));
            final double height =  Double.valueOf(String.valueOf(etheight.getText()));

            //  计算公式：bmi = 体重公斤数/身高米数的平方
            double bmi = (weight/(height*height));
            etbmi.setText(String.valueOf(bmi));
            health(Integer.valueOf((int) bmi),sex);
        });
    }

    public void health(int bmi,String sex)
    {
        //男性
        Map<String,Object> map1 = new HashMap<String,Object>();
        map1.put("a<20","体重过轻");
        map1.put("20<=a&&a<25","体重正常");
        map1.put("25<=a&&a<27","体重超重");
        map1.put("27<=a&&a<30","轻度肥胖");
        map1.put("30<=a&&a<35","中度肥胖");
        map1.put("35<=a","重度肥胖");
        //女性
        Map<String,Object> map2= new HashMap<String,Object>();
        map1.put("a<19","体重过轻");
        map1.put("19<=a&&a<24","体重正常");
        map1.put("24<=a&&a<26","体重超重");
        map1.put("26<=a&&a<29","轻度肥胖");
        map1.put("29<=a&&a<34","中度肥胖");
        map1.put("34<=a","重度肥胖");
        //BMI传入
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("a",bmi);

        if(sex.equals("男"))
        {
            for(Map.Entry<String,Object> entry : map1.entrySet())
            {
                String expression = entry.getKey();
                Expression exp =  AviatorEvaluator.compile(expression);
                Boolean bool = (Boolean)exp.execute(map);
                if(bool)
                {
                    System.out.println(entry.getValue().toString());
                    etzd.setText(entry.getValue().toString());
                    break;
                }
            }
        }
        if(sex.equals("女"))
        {
            for(Map.Entry<String,Object> entry : map2.entrySet())
            {
                String expression = entry.getKey();
                Expression exp =  AviatorEvaluator.compile(expression);
                Boolean bool = (Boolean)exp.execute(map);
                if(bool)
                {
                    System.out.println(entry.getValue().toString());
                    etzd.setText(entry.getValue().toString());
                    break;
                }
            }
        }
    }
    public static void main(String[] args) {
//        MainActivity ma = new MainActivity();
//        ma.health(20,"男");
//    }

        Map<String,Object> map = new HashMap<String,Object>();
        map.put("a",Integer.valueOf((int) 35.4));
        Map<String,Object> map1 = new HashMap<String,Object>();
        map1.put("a<20","体重过轻");
        map1.put("20<=a&&a<25","体重正常");
        map1.put("25<=a&&a<27","体重超重");
        map1.put("27<=a&&a<30","轻度肥胖");
        map1.put("30<=a&&a<35","中度肥胖");
        map1.put("35<=a","重度肥胖");
        for(Map.Entry<String,Object> entry : map1.entrySet())
        {
            String expression = entry.getKey();
            Expression exp =  AviatorEvaluator.compile(expression);
            Boolean bool = (Boolean)exp.execute(map);
            if(bool)
            {
                System.out.println(entry.getValue().toString());
                break;
            }
        }
    }
}