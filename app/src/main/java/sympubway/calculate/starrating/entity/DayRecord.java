package sympubway.calculate.starrating.entity;

import java.util.Date;

/**
 * Created by zsr on 2017/2/10.
 */
public class DayRecord {
    private long id;
    private Date date;
    private float value;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
