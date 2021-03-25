package com.example.bmicalculator;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.*;


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
        radioGroup.setOnCheckedChangeListener((group, checkedId) -> { });
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
        if(sex.equals("男")){
            if(bmi<20) {
                etzd.setText("体重过轻");
            }else if(bmi<25){
                etzd.setText("体重正常");
            }else if(bmi<27){
                etzd.setText("体重超重");
            }else if(bmi<30){
                etzd.setText("轻度肥胖");
            }else if(bmi<35){
                etzd.setText("中度肥胖");
            }else{
                etzd.setText("重度肥胖");
            }
        }
        if(sex.equals("女")){
            if(bmi<19) {
                etzd.setText("体重过轻");
            }else if(bmi<24){
                etzd.setText("体重正常");
            }else if(bmi<26){
                etzd.setText("体重超重");
            }else if(bmi<29){
                etzd.setText("轻度肥胖");
            }else if(bmi<34){
                etzd.setText("中度肥胖");
            }else{
                etzd.setText("重度肥胖");
            }
        }
    }
}