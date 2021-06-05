package niit.com.d0527_recylerviewsqlite.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import niit.com.d0527_recylerviewsqlite.databinding.ItemStudentInfoBinding;
import niit.com.d0527_recylerviewsqlite.entity.Student;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{
    private final List<Student> students;

    // 构造方法，接收传递的数据集合
    public MyAdapter(List<Student> students) {
        this.students = students;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 加载列表项布局
        ItemStudentInfoBinding binding = ItemStudentInfoBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // 给列表项控件赋值
        Student student = students.get(position);
        holder.binding.tvName.setText(student.getName());
        holder.binding.tvClass.setText(student.getClassName());
        holder.binding.tvAge.setText(String.valueOf(student.getAge()));

        // 设置item的选中与否状态
//        holder.itemView.setSelected(selectedIndex == position);
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public void addData(int position, Student student) {
        this.students.add(position, student);
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        this.students.remove(position);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ItemStudentInfoBinding binding;

        public ViewHolder(@NonNull ItemStudentInfoBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
            this.itemView.setTag(this);
            this.itemView.setOnClickListener(onClickListener);
            this.itemView.setOnLongClickListener(longClickListener);
        }
    }

    //记录当前选中的条目索引，用于设置背景色
    private int selectedIndex;
    public void setSelectedIndex(int position) {
        this.selectedIndex = position;
        notifyDataSetChanged();
    }

    private View.OnLongClickListener longClickListener =null;
    public void setOnLongClickListener(View.OnLongClickListener longClickListener) {
        this.longClickListener = longClickListener;
    }

    private View.OnClickListener onClickListener = null;
    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

}
