package sympubway.calculate.starrating.dao;

import android.content.ContentValues;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import sympubway.calculate.starrating.entity.DayRecord;

/**
 * Created by zsr on 2017/2/10.
 */
public class DayRecordDAO extends BaseDAO {
    private static final String TABLENAME = "day_records";
    private static final String[] columns = { "id", "date", "value" };

    public void createNew(DayRecord item) {
        ContentValues values = setContentValue(item);
        values.put("id", item.getId());
        super.createItem(TABLENAME, values);
    }

    public void delete(String id) {
        super.deleteById(TABLENAME, id);
    }

    public DayRecord load(String id) {
        Cursor c = super.loadById(TABLENAME, id, columns);
        if (c != null && c.moveToFirst()) {
            c.moveToFirst();
            return extractValue(c);
        }
        return null;
    }

    public List<DayRecord> getWholeList() {
        Cursor c = super.findList(TABLENAME, columns);
        List<DayRecord> list = new ArrayList<DayRecord>();
        if (c != null) {
            while (c.moveToNext()) {
                list.add(extractValue(c));
            }
        }
        return list;
    }

    public void update(DayRecord item) {
        ContentValues values = setContentValue(item);
        super.updateItem(TABLENAME, values, item.getId());
    }

    private DayRecord extractValue(Cursor cursor) {
        DayRecord item = new DayRecord();
        item.setId(getStringValue(cursor, "id"));
        item.setDate(helper.epochToDate(getIntegerValue(cursor, "date")));
        item.setValue(getFloat(cursor, "value"));
        return item;
    }

    private ContentValues setContentValue(DayRecord item) {
        ContentValues values = new ContentValues();
        values.put("id", item.getId());
        values.put("date", helper.dateToEpoch(item.getDate()));
        values.put("value", item.getValue());
        return values;
    }

}
