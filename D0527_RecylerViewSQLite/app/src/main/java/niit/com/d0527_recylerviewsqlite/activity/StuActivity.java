package niit.com.d0527_recylerviewsqlite.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.SpinnerAdapter;

import niit.com.d0527_recylerviewsqlite.R;
import niit.com.d0527_recylerviewsqlite.dao.StudentDao;
import niit.com.d0527_recylerviewsqlite.databinding.StuInsertBinding;
import niit.com.d0527_recylerviewsqlite.entity.Student;


public class StuActivity extends Activity implements View.OnClickListener{
    private StudentDao studentDao;
    private Student currentStudent;
    private boolean isUpdate = false;
    private StuInsertBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = StuInsertBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        studentDao = new StudentDao(this);
        binding.btnOk.setOnClickListener(this);
        binding.btnCancel.setOnClickListener(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            currentStudent = (Student) bundle.get("student");
        }
        if (currentStudent != null) {
            isUpdate = true;
            binding.etName.setText(currentStudent.getName());
            binding.etAge.setText(String.valueOf(currentStudent.getAge()));

            SpinnerAdapter spinnerAdapter = binding.spClass.getAdapter();
            for (int i = 0; i < spinnerAdapter.getCount(); i++) {
                if(spinnerAdapter.getItem(i).toString()
                        .equals(currentStudent.getClassName())){
                    binding.spClass.setSelection(i);
                    break;
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btn_ok)
        {
            Student student = new Student(
                    binding.etName.getText().toString(),
                    binding.spClass.getSelectedItem().toString(),
                    Integer.parseInt(binding.etAge.getText().toString())
            );
            if (isUpdate) {
                student.set_id(currentStudent.get_id());
                studentDao.update(student);
            }else {
                studentDao.insert(student);
            }
            setResult(RESULT_OK, new Intent());
            finish();
        } else if (v.getId() == R.id.btn_cancel) {
            finish();
        }
    }

}
