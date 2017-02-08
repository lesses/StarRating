package sympubway.calculate.starrating.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by zsr on 2017/2/7.
 */
public class DBManager {
    private final static ThreadLocal<SQLiteDatabase> database = new ThreadLocal<SQLiteDatabase>();
    private DatabaseHelper helper;

    public DBManager(Context cxt) {
        helper = new DatabaseHelper(cxt);
    }

    public SQLiteDatabase get() {
        SQLiteDatabase db = database.get();
        if (db == null) {
            db = helper.getWritableDatabase();
            database.set(db);
        }
        return db;
    }

    public void close() {
        SQLiteDatabase db = database.get();
        if (db != null) {
            db.close();
            database.set(null);
        }
    }

}
