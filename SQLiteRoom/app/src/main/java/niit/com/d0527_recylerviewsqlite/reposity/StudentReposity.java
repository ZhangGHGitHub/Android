package niit.com.d0527_recylerviewsqlite.reposity;

import android.app.Application;
import android.icu.text.IDNA;

import java.util.List;

import niit.com.d0527_recylerviewsqlite.R;
import niit.com.d0527_recylerviewsqlite.dao.StudentDao;
import niit.com.d0527_recylerviewsqlite.entity.Student;
import niit.com.d0527_recylerviewsqlite.utils.InfoRoomDatabase;

public class StudentReposity {
    private final StudentDao studentDao;

    public StudentReposity(Application application) {
        InfoRoomDatabase db = InfoRoomDatabase.getINSTANCE(application);
        studentDao = db.getStudentDao();
    }

    //在子线程中执行增加操作
    public void insert(final Student student) {
        InfoRoomDatabase.writeExecutor.execute(new Runnable() {
            @Override
            public void run() {
                studentDao.insert(student);
            }
        });
    }

    //在子线程中执行更新操作
    public void update(final Student student) {
        InfoRoomDatabase.writeExecutor.execute(new Runnable() {
            @Override
            public void run() {
                studentDao.update(student);
            }
        });
    }

    //在子线程中执行删除操作
    public void delete(final Student student) {
        InfoRoomDatabase.writeExecutor.execute(new Runnable() {
            @Override
            public void run() {
                studentDao.delete(student);
            }
        });
    }

    //查询所有
    public List<Student> selectAll() {
        return studentDao.selectall();
    }
    //查询一个
    public Student select(int id){
        return studentDao.select(id);
    }


}
