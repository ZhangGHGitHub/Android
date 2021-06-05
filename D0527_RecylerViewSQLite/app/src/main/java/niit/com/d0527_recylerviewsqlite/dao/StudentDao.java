package niit.com.d0527_recylerviewsqlite.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import niit.com.d0527_recylerviewsqlite.utils.DBHelper;
import niit.com.d0527_recylerviewsqlite.entity.Student;

public class StudentDao {

    private DBHelper dbHelper;
    public StudentDao(Context context) {
        dbHelper = new DBHelper(context);
    }

    //曾
    public void insert(Student student){
        //第一种方法
        //打开数据库
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        //封装
        ContentValues values = new ContentValues();
        values.put("name", student.getName());
        values.put("className", student.getClassName());
        values.put("age", student.getAge());
        db.insert("t_student", null, values);
        db.close();
    }
//    public void insert(Student name, String className, int age){
//        //第二种方法
//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        String sql = "insert into t_student(name,className,age) values(?,?,?)";
//        db.execSQL(sql, new String[]{student.getName(), student.getClassName(), String.valueOf(student.getAge())});
//        db.close();
//    }
    //改
    public void update(Student student) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", student.getName());
        values.put("className", student.getClassName());
        values.put("age", student.getAge());
        db.update("t_student", values, "_id=?", new String[]{String.valueOf(student.get_id())});
        db.close();
    }
    //查所有
    public List<Student> selectall() {
        List<Student> students = new ArrayList<>();
        //打开数据库
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        //查询
        Cursor cursor = db.query("t_student", null, null, null, null, null, null);
        while (cursor.moveToNext()) {
            Student student = new Student(
                    cursor.getString(cursor.getColumnIndex("name")),
                    cursor.getString(cursor.getColumnIndex("className")),
                    cursor.getInt(cursor.getColumnIndex("age")));
            student.set_id(cursor.getInt(cursor.getColumnIndex("_id")));
            students.add(student);
        }
        cursor.close();
        db.close();
        return students;
    }
    //查一个
    public Student select(int _id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.query("t_student", null, "_id=?", new String[]{String.valueOf(_id)}, null, null, null);
        Student student = null;
        while (cursor.moveToNext()) {
               student = new Student(
                    cursor.getString(cursor.getColumnIndex("name")),
                    cursor.getString(cursor.getColumnIndex("className")),
                    cursor.getInt(cursor.getColumnIndex("age")));
            student.set_id(cursor.getInt(cursor.getColumnIndex("_id")));
        }
        cursor.close();
        db.close();

        return student;
    }
    //删
    public void delete(int _id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.delete("t_student","_id=?",new String[]{String.valueOf(_id)});
        db.close();

//        SQLiteDatabase db = dbHelper.getWritableDatabase();
//        String sql = "delete from student where _id=?";
//        db.execSQL(sql, new String[]{ String.valueOf(_id)});
//        db.close();
    }

}
