package sympubway.calculate.starrating.sqlite;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by zsr on 2017/2/7.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "mine_monitoring";
    private Context context;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createDatabase(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        createDatabase(db);
    }

    private void createDatabase(SQLiteDatabase db) {
        try {
            AssetManager am = context.getAssets();
            BufferedReader br = new BufferedReader(new InputStreamReader(am.open("createdb.sql")));
            StringBuffer sql = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                sql.append(line);
                if (line.endsWith(";")) {
                    db.execSQL(sql.toString());
                    sql = new StringBuffer();
                }
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
