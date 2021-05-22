package com.example.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recyclerview.JavaBean.Fruit;
import com.example.recyclerview.databinding.ItemFruitBinding;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    private List<Fruit> fruits;
    private final View.OnClickListener listener;

    public MyAdapter(List<Fruit> fruits, View.OnClickListener listener) {
        this.listener = listener;
        this.fruits = fruits;
    }

    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //加载列表项布局
        ItemFruitBinding binding = ItemFruitBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);

    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        final Fruit fruit = fruits.get(position);
        holder.binding.tvName.setText(fruit.getName());
        holder.binding.ivFruit.setImageResource(fruit.getImageId());
        holder.itemView.setSelected(selectedIndex == position);
    }

    @Override
    public int getItemCount() {
        return fruits.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemFruitBinding binding;
        public ViewHolder(@NonNull ItemFruitBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.itemView.setTag(this);
            this.itemView.setOnClickListener(listener);
            this.itemView.setOnLongClickListener(longClickListener);
        }
    }
    //记录当前选中的条目索引，用于背景设置
    private int selectedIndex;

    //增
    public void addDate(int postion, Fruit fruit) {
        this.fruits.add(postion, fruit);
        notifyDataSetChanged();
    }
    //删
    public void removeDate(int position) {
        this.fruits.remove(position);
        notifyDataSetChanged();
    }
    //改
    public void setSelectedIndex(int position) {
        this.selectedIndex = position;
        notifyDataSetChanged();
    }
    //查
    //TODU

    private View.OnLongClickListener longClickListener;

    public void setLongClickListener(View.OnLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }
}
