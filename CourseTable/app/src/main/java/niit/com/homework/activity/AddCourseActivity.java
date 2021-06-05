package niit.com.homework.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import niit.com.homework.entity.Course;
import niit.com.homework.R;
import niit.com.homework.databinding.ActivityAddCourseBinding;


public class AddCourseActivity extends AppCompatActivity {

    private ActivityAddCourseBinding binding;
    boolean isRevise = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAddCourseBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setFinishOnTouchOutside(false);


        Intent intent = getIntent();
        final Course ReviseCourse = (Course) intent.getSerializableExtra("ReviseCourse");
        isRevise=intent.getBooleanExtra("isRevise",false);

        Button okButton = (Button) findViewById(R.id.button);



        if(isRevise){

            binding.courseName.setText(ReviseCourse.getCourseName());
            binding.classRoom.setText(ReviseCourse.getClassRoom());
            binding.teacherName.setText(ReviseCourse.getTeacher());
            setSpinnerDefaultValue(binding.week,String.valueOf(ReviseCourse.getDay()));
            setSpinnerDefaultValue(binding.classesBegin,String.valueOf(ReviseCourse.getStart()));
            setSpinnerDefaultValue(binding.classesEnds,String.valueOf(ReviseCourse.getEnd()));



            okButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String courseName = binding.courseName.getText().toString();
                    String teacher = binding.teacherName.getText().toString();
                    String classRoom = binding.classRoom.getText().toString();
                    String day = binding.week.getSelectedItem().toString();
                    String start = binding.classesBegin.getSelectedItem().toString();
                    String end = binding.classesEnds.getSelectedItem().toString();

                    if (courseName.equals("") || day.equals("") || start.equals("") || end.equals("")) {
                        Toast.makeText(AddCourseActivity.this, "基本课程信息未填写", Toast.LENGTH_SHORT).show();
                    }

                    Course newCourse = new Course(courseName, teacher, classRoom,
                            Integer.valueOf(day), Integer.valueOf(start), Integer.valueOf(end));

                    Intent intent = new Intent();
                    intent.putExtra("PreCourse",ReviseCourse);
                    intent.putExtra("newCourse", newCourse);
                    Log.d("AddCourseActivity","我进了了");
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                }
            });

        }else {
            okButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String courseName = binding.courseName.getText().toString();
                    String teacher = binding.teacherName.getText().toString();
                    String classRoom = binding.classRoom.getText().toString();
                    String day = binding.week.getSelectedItem().toString();
                    String start = binding.classesBegin.getSelectedItem().toString();
                    String end = binding.classesEnds.getSelectedItem().toString();

                    if (courseName.equals("") || day.equals("") || start.equals("") || end.equals("")) {
                        Toast.makeText(AddCourseActivity.this, "基本课程信息未填写", Toast.LENGTH_SHORT).show();
                    } else {
                        Course course = new Course(courseName, teacher, classRoom,
                                Integer.valueOf(day), Integer.valueOf(start), Integer.valueOf(end));
                        Intent intent = new Intent(AddCourseActivity.this, MainActivity.class);
                        intent.putExtra("course", course);

                        setResult(Activity.RESULT_OK, intent);
                        finish();
                    }
                }
            });
        }

    }

    private void setSpinnerDefaultValue(Spinner spinner, String value) {
        SpinnerAdapter apsAdapter = spinner.getAdapter();
        int size = apsAdapter.getCount();
        for (int i = 0; i < size; i++) {

            if (TextUtils.equals(value, apsAdapter.getItem(i).toString())) {
                spinner.setSelection(i,true);
                break;
            }
        }
    }

}
