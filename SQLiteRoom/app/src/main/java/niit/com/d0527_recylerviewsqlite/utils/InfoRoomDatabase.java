package niit.com.d0527_recylerviewsqlite.utils;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import niit.com.d0527_recylerviewsqlite.dao.StudentDao;
import niit.com.d0527_recylerviewsqlite.entity.Student;

@Database(entities = {Student.class}, version = 1, exportSchema = false)
public abstract class InfoRoomDatabase extends RoomDatabase {

    public static String DB_NAME = "info";
    private static volatile InfoRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    //获取dao的抽象方法
    public abstract StudentDao getStudentDao();
    //数据库写操作
    public static final ExecutorService writeExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    //单例模式
    public static InfoRoomDatabase getINSTANCE(final Context context) {
        if (INSTANCE == null) {
            synchronized (InfoRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            InfoRoomDatabase.class,InfoRoomDatabase.DB_NAME)
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
    /**
     * 清除Database实例
     * */
    public void clearUp() {
        INSTANCE = null;
    }
}
