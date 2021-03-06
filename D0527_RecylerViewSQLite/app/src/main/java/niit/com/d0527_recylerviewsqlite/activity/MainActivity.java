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
import java.util.Locale;

import niit.com.d0527_recylerviewsqlite.adapter.MyAdapter;
import niit.com.d0527_recylerviewsqlite.R;
import niit.com.d0527_recylerviewsqlite.dao.StudentDao;
import niit.com.d0527_recylerviewsqlite.databinding.ActivityMainBinding;
import niit.com.d0527_recylerviewsqlite.entity.Student;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private ActivityMainBinding binding;
    private MyAdapter adapter;
    private Student currentsStudent;
    private StudentDao studentDao;
    private List<Student> students =new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        studentDao = new StudentDao(this);
        students = studentDao.selectall();
        if (students.size() > 0) {
            currentsStudent = students.get(0);
        }
        initView();
    }

    private void initView() {
        binding.btnAdd.setOnClickListener(this);
        binding.btnUpdate.setOnClickListener(this);
        binding.btnDel.setOnClickListener(this);

        //RecyclerView ???????????????????????????????????????????????????
        binding.rvStudent.setLayoutManager(new LinearLayoutManager(this));
        binding.rvStudent.setItemAnimator(new DefaultItemAnimator());
        //??????RecyclerView?????????Adapter
        adapter = new MyAdapter(students);
        binding.rvStudent.setAdapter(adapter);
        // ???????????????
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
                Snackbar.make(view, "?????????????", Snackbar.LENGTH_LONG)
                        .setAction("??????", new View.OnClickListener() {
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
                Toast.makeText(this,"??????????????????",Toast.LENGTH_SHORT).show();
                return;
            }
            new AlertDialog.Builder(this).setTitle("??????").setMessage("???????????????")
                    .setPositiveButton("??????", new DialogInterface.OnClickListener(){
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            studentDao.delete(currentsStudent.get_id());
                            dialog.dismiss();
                            changeDate();
                        }
                    })
                    .setNegativeButton("??????", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    }).show();

        }
    }
    // ???assets????????????db????????????/data/data/??????/databases/??????????????????
    private void saveDBFile(String dbName) {
        String destPath = "/data" + Environment.getDataDirectory().getAbsolutePath()
                + File.separator + getPackageName()
                + File.separator + "databases";
        File filePath = new File(destPath);
        // ????????????????????????
        if (!filePath.exists()) {
            filePath.mkdirs();
        }

        // ???????????????????????????
        File file = new File(destPath, dbName);
        try {
            // ??????????????????????????????
            InputStream input = this.getAssets().open(dbName);
            FileOutputStream output = new FileOutputStream(file);

            // ???????????????????????????????????????????????????????????????????????????
            int len = -1;
            byte[] buffer = new byte[1024];

            while ((len = input.read(buffer)) != -1) {
                output.write(buffer, 0, len);
            }
            output.flush();

            // ????????????????????????
            output.close();
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void changeDate() {
        students.clear();
        students.addAll(studentDao.selectall());
        adapter.notifyDataSetChanged();
    }
}