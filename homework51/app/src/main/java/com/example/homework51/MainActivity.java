package com.example.homework51;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.homework51.databinding.ActivityMainBinding;

/**
 * 1. 编写一个数据传递的程序，在第一个界面输入两个数字，第二个界面显示这两个数字的和。
 * 2. 创建项目演示Fragment的生命周期方法的执行顺序。
 * 3. 使用对话框的方式完成第1题。
 * 完成以上简单编程题，提交项目的url地址，并录制3分钟以上的视频讲解编码及思考过程
 *
 * */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityMainBinding binding;
    private AlertDialog.Builder builder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnSub1.setOnClickListener(this);
        binding.btnSub2.setOnClickListener(this);
        binding.btnSub3.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_sub1:
                questionOne();
                break;
            case R.id.btn_sub2:
                questionTwo();
                break;
            case R.id.btn_sub3:
                questionThree();
                break;
            default:
                break;
        }
    }

    private void questionThree() {
        builder = new AlertDialog.Builder(this);
        final AlertDialog dialog = builder.create();
        View loginView = View.inflate(MainActivity.this, R.layout.dialog_login, null);
        final EditText num1=loginView.findViewById(R.id.et_num1);
        final EditText num2=loginView.findViewById(R.id.et_num2);
        final Button btnCal=loginView.findViewById(R.id.btn_calculate);
        final Button btnCan=loginView.findViewById(R.id.btn_cancel);
        builder.setView(loginView);
        builder.show();

        btnCal.setOnClickListener(v -> {
            String n1 = (num1.getText().toString());
            String n2 = (num2.getText().toString());
            if (!TextUtils.isEmpty(num1.toString()) && !TextUtils.isEmpty(num2.toString())) {
                switch (v.getId()) {
                    case R.id.btn_calculate:
                        sum(String.valueOf(Integer.parseInt(n1 + n2)));
                        builder.create().dismiss();
                }
            }else {
                Toast.makeText(this, "不能为空", Toast.LENGTH_LONG);
            }
        });
        btnCan.setOnClickListener(v -> {
            dialog.dismiss();
        });


    }

    private void sum(String str) {
        builder = new AlertDialog.Builder(this);
        View view2 = View.inflate(MainActivity.this,R.layout.dialog_sum,null);
        final EditText sum1 = view2.findViewById(R.id.et_sum);
        builder.setView(view2);
        builder.create().show();
        sum1.setText(str);
    }
    private void replaceFragment(Fragment fragment){
            //获取FragmentManager，通过getSupportFragmentManager调用
            //开启一个事务
            //向容器内添加或替换事务，一般通过replace方法来实现，传入的参数为:传入容器的id以及待添加的碎片实例。
            //返回栈
            //提交事务
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment,fragment)
                .addToBackStack(null)
                .commit();
    }

    private void questionTwo() {
        replaceFragment(new LifeSystemFragment());
    }

    private void questionOne() {
        String n1 = binding.etNum1.getText().toString();
        String n2 = binding.etNum2.getText().toString();

        if(n1 != null && n2 != null){
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            int num1 = Integer.parseInt(n1);
            int num2 = Integer.parseInt(n2);
            int sum = num1 + num2;
            intent.putExtra("sum", Integer.toString(sum));
            startActivity(intent);
        }else {
            Toast.makeText(MainActivity.this,"不能为空！！！", Toast.LENGTH_SHORT).show();
        }
    }

}