package niit.com.homework.utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MySqliteHelper extends SQLiteOpenHelper {
    private final static String CREATE_TABLE_STUDENT="create table courses(" +
            "id integer primary key autoincrement," +
            "course_name text," +
            "teacher text," +
            "class_room text," +
            "day integer," +
            "class_start integer," +
            "class_end integer)";
    private  final static String DROP_TABLE_STUDENT="drop table if exists courses";
    public MySqliteHelper(@Nullable Context context) {
        super(context, "timetable.db", null, 1);
    }

    public MySqliteHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MySqliteHelper.CREATE_TABLE_STUDENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(MySqliteHelper.DROP_TABLE_STUDENT);
        onCreate(db);
    }
}
