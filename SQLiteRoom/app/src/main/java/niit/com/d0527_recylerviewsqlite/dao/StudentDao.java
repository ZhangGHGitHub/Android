package niit.com.d0527_recylerviewsqlite.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import niit.com.d0527_recylerviewsqlite.entity.Student;
@Dao
public interface StudentDao {

    //曾
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Student student);
    //删
    @Delete
    void delete(Student student);
    //改
    @Update
    void update(Student student);
    //查所有
    @Query("select * from t_student")
    List<Student> selectall();
    //查
    @Query("select * from t_student where _id=:_id")
    Student select(int _id);


    /**
     * @Query 注解用于声明需要查询的SQL与， 占位符是：表示
     *
     *
     *
     * */
}
