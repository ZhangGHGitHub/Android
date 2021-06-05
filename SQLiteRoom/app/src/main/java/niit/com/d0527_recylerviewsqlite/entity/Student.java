package niit.com.d0527_recylerviewsqlite.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "t_student")
public class Student implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo
    private int _id;

    //ColumnInfo 与数据库字段关联 如果属性名与表的字段名相同，则无需添加name属性
    @ColumnInfo
    private String name;
    @ColumnInfo
    private String className;
    @ColumnInfo
    private int age;
    public Student() {
    }
    public Student(String name, String className, int age) {
        this.name = name;
        this.className = className;
        this.age = age;
    }
    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
