package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.example.recyclerview.JavaBean.Fruit;
import com.example.recyclerview.databinding.ActivityMainBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    private MyAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initData();
        initView();
    }


    private void initView(){
        //设置布局管理器
        //线性布局器
        binding.rvFruit.setLayoutManager(new LinearLayoutManager(this));
        //设置分割线
        binding.rvFruit.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        //设置动画
        binding.rvFruit.setItemAnimator(new DefaultItemAnimator());
        //设置适配器
        adapter = new MyAdapter(fruits, new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                final MyAdapter.ViewHolder viewHolder = (MyAdapter.ViewHolder)view.getTag();
                final int position = viewHolder.getAdapterPosition();
                adapter.setSelectedIndex(position);
                final Fruit fruit = fruits.get(position);
                Snackbar.make(view, String.format(Locale.CHINA,"点击了第%d行的%s",position
                        + 1, fruit.getName()),Snackbar.LENGTH_SHORT).show();
            }
        });
        binding.rvFruit.setAdapter(adapter);

        binding.fabAdd.setOnClickListener(v -> {
            Snackbar.make(v,"添加一个水果",Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show();
        });
        adapter.setLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final MyAdapter.ViewHolder viewHolder = (MyAdapter.ViewHolder)v.getTag();
                final int position = viewHolder.getAdapterPosition();
                Snackbar.make(v,"确认删除?",Snackbar.LENGTH_LONG)
                        .setAction("删除", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                adapter.removeDate(position);
                            }
                        }).show();
                return true;
            }
        });
    }


    private final List<Fruit> fruits = new ArrayList<>();
    private final List<Fruit> fruitsList = new ArrayList<Fruit>(){
        {
            add(new Fruit("苹果", R.drawable.apple));
            add(new Fruit("香蕉", R.drawable.banana));
            add(new Fruit("橙子", R.drawable.orange));
            add(new Fruit("樱桃", R.drawable.cherry));
            add(new Fruit("芒果", R.drawable.mango));
            add(new Fruit("梨子", R.drawable.pear));
            add(new Fruit("草莓", R.drawable.strawberry));
            add(new Fruit("菠萝", R.drawable.pineapple));
            add(new Fruit("西瓜", R.drawable.watermelon));
            add(new Fruit("葡萄", R.drawable.grape));
        }
    };
    //初始化数据
    private void initData(){
        fruits.clear();
        for (int i = 0; i < 10; i++) {
            fruits.addAll(fruitsList);
        }
        Collections.shuffle(fruits);
    }

}