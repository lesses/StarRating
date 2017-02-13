package sympubway.calculate.starrating.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import sympubway.calculate.starrating.AppContext;
import sympubway.calculate.starrating.AppHelper;
import sympubway.calculate.starrating.sqlite.DBManager;

/**
 * Created by zsr on 2017/2/8.
 */
public abstract class BaseDAO {
    protected static final AppHelper helper = AppHelper.getInstance();
    protected static final DBManager dbMgr = AppContext.getInstance().getDbManager();

    protected void createItem(String table, ContentValues values) {
        SQLiteDatabase db = dbMgr.get();
        db.insert(table, null, values);
    }

    protected Cursor findList(String table, String[] columns) {
        SQLiteDatabase db = dbMgr.get();
        Cursor cursor = db.query(table, columns, null, null, null, null, null, null);
        return cursor;
    }

    protected Cursor findList(String table, String[] columns, String whereClause, String[] whereArgs) {
        return findList(table, columns, whereClause, whereArgs, null);
    }

    protected Cursor findList(String table, String[] columns, String whereClause, String[] whereArgs, String orderBy) {
        SQLiteDatabase db = dbMgr.get();
        Cursor cursor = db.query(table, columns, whereClause, whereArgs, null, null, orderBy, null);
        return cursor;
    }

    protected double getDoubleValue(Cursor cursor, String columnName) {
        int idx = cursor.getColumnIndex(columnName);
        if (idx > -1 && cursor.getType(idx) == Cursor.FIELD_TYPE_FLOAT || cursor.getType(idx) == Cursor.FIELD_TYPE_INTEGER) {
            return cursor.getDouble(idx);
        } else {
            return 0;
        }
    }

    protected Double getDouble(Cursor cursor, String columnName) {
        int idx = cursor.getColumnIndex(columnName);
        if (idx > -1 && (cursor.getType(idx) == Cursor.FIELD_TYPE_FLOAT) || cursor.getType(idx) == Cursor.FIELD_TYPE_INTEGER) {
            return cursor.getDouble(idx);
        } else {
            return null;
        }
    }

    protected int getIntegerValue(Cursor cursor, String columnName) {
        int idx = cursor.getColumnIndex(columnName);
        if (idx > -1 && cursor.getType(idx) == Cursor.FIELD_TYPE_INTEGER) {
            return cursor.getInt(idx);
        } else {
            return 0;
        }
    }

    protected Integer getInteger(Cursor cursor, String columnName) {
        int idx = cursor.getColumnIndex(columnName);
        if (idx > -1 && cursor.getType(idx) == Cursor.FIELD_TYPE_INTEGER) {
            return cursor.getInt(idx);
        } else {
            return null;
        }
    }

    protected Float getFloat(Cursor cursor, String columnName) {
        int idx = cursor.getColumnIndex(columnName);
        if (idx > -1) {
            return cursor.getFloat(idx);
        } else {
            return null;
        }
    }

    protected String getStringValue(Cursor cursor, String columnName) {
        int idx = cursor.getColumnIndex(columnName);
        if (idx > -1) {
            return cursor.getString(idx);
        } else {
            return null;
        }
    }

    protected void deleteItem(String table, String whereClause, String[] whereArgs) {
        SQLiteDatabase db = dbMgr.get();
        db.delete(table, whereClause, whereArgs);
    }

    protected void deleteById(String table, String id) {
        SQLiteDatabase db = dbMgr.get();
        db.delete(table, " id = ?", new String[] { String.valueOf(id) });
    }

    protected Cursor loadById(String table, String id, String[] columns) {
        SQLiteDatabase db = dbMgr.get();
        Cursor cursor = db.query(table, columns, " id = ?", new String[] { String.valueOf(id) }, null, null, null, null);
        return cursor;
    }

    protected void updateItem(String tablename, ContentValues values, String id) {
        SQLiteDatabase db = dbMgr.get();
        db.update(tablename, values, " id = ?", new String[] { String.valueOf(id) });
    }
}
