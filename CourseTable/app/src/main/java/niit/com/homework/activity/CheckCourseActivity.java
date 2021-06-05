package niit.com.homework.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import niit.com.homework.entity.Course;
import niit.com.homework.utils.MySqliteHelper;
import niit.com.homework.R;
import niit.com.homework.databinding.CheckCourseBinding;


public class CheckCourseActivity extends AppCompatActivity {

    private CheckCourseBinding binding;
    private MySqliteHelper databaseHelper = new MySqliteHelper
            (this, "timetable.db", null, 1);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_course);

        Intent intent = getIntent();
        final Course seeCourse = (Course) intent.getSerializableExtra("seeCourse");


        binding.seeCourseName.setText(seeCourse.getCourseName());
        binding.seeWeek.setText(String.valueOf(seeCourse.getDay()));
        binding.seeClassesBegin.setText(String.valueOf(seeCourse.getStart()));
        binding.seeClassesEnds.setText(String.valueOf(seeCourse.getEnd()));
        binding.seeTeacherName.setText(seeCourse.getTeacher());
        binding.seeClassRoom.setText(seeCourse.getClassRoom());

        Button delBtn = (Button)findViewById(R.id.btn_del);
        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CheckCourseActivity.this, MainActivity.class);
                intent.putExtra("PreCourse", seeCourse);
                intent.putExtra("isDelete",true);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

        //修改按钮被按下时
        Button ReviseBtn = (Button)findViewById(R.id.btn_revise);
        ReviseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CheckCourseActivity.this, MainActivity.class);
                intent.putExtra("PreCourse", seeCourse);
                intent.putExtra("isDelete",false);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("SeeCourseActivity", "修改的返回来了");
    }
}
