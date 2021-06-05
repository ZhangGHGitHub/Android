package niit.com.d0527_recylerviewsqlite.entity;

import java.io.Serializable;

public class Student implements Serializable {
    private int _id;
    private String name;
    private String className;
    private int age;

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
