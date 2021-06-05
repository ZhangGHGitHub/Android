package niit.com.d0527_recylerviewsqlite.activity;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import niit.com.d0527_recylerviewsqlite.adapter.MyAdapter;
import niit.com.d0527_recylerviewsqlite.R;
import niit.com.d0527_recylerviewsqlite.databinding.ActivityMainBinding;
import niit.com.d0527_recylerviewsqlite.entity.Student;
import niit.com.d0527_recylerviewsqlite.reposity.StudentReposity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private MyAdapter adapter;
    private Student currentsStudent;
    private List<Student> students;
    private ActivityMainBinding binding;
    private StudentReposity studentReposity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        studentReposity = new StudentReposity(this.getApplication());
        students = studentReposity.selectAll();
        initView();
    }

    private void initView() {
        binding.btnAdd.setOnClickListener(this);
        binding.btnUpdate.setOnClickListener(this);
        binding.btnDel.setOnClickListener(this);

        //RecyclerView 空间的初始化，设置布局管理器和动画
        binding.rvStudent.setLayoutManager(new LinearLayoutManager(this));
        binding.rvStudent.setItemAnimator(new DefaultItemAnimator());
        //设置RecyclerView控件的Adapter
        adapter = new MyAdapter(students);
        binding.rvStudent.setAdapter(adapter);
        // 设置适配器
        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyAdapter.ViewHolder viewHolder = (MyAdapter.ViewHolder) v.getTag();
                int position = viewHolder.getAdapterPosition();
                adapter.setSelectedIndex(position);
                currentsStudent = students.get(position);
            }
        });
        adapter.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                final MyAdapter.ViewHolder viewHolder = (MyAdapter.ViewHolder) view.getTag();
                final int position = viewHolder.getAdapterPosition();
                Snackbar.make(view, "确认删除?", Snackbar.LENGTH_LONG)
                        .setAction("删除", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                adapter.removeData(position);
                            }
                        }).show();
                return true;
            }
        });

    }



    private  ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    changeDate();
                }
            }
    );

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, StuActivity.class);
        if (v.getId() == R.id.btn_add) {
            launcher.launch(intent);
        } else if (v.getId() == R.id.btn_update) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("student", currentsStudent);
            intent.putExtra("flag", 1);
            intent.putExtras(bundle);
            launcher.launch(intent);
        } else if (v.getId() == R.id.btn_del) {
            if (currentsStudent == null) {
                Toast.makeText(this,"没有选中数据",Toast.LENGTH_SHORT).show();
                return;
            }
            new AlertDialog.Builder(this).setTitle("删除").setMessage("确认删除？")
                    .setPositiveButton("确认", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            studentReposity.delete(currentsStudent);
                            dialog.dismiss();
                            changeDate();
                        }
                    })
                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();

        }
    }

    private void changeDate() {
        students.clear();
        students.addAll(studentReposity.selectAll());
        adapter.notifyDataSetChanged();
    }
}