package sympubway.calculate.starrating;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by zsr on 2017/2/8.
 */
public class AppHelper {
    private static AppHelper instance = new AppHelper();
    private static final int epochBase = 31536000;

    private AppHelper(){

    }

    public static AppHelper getInstance() {
        return instance;
    }

    public int dateToEpoch(Date date) {
        return (int) ((date.getTime() / 1000) - epochBase);
    }

    public Date epochToDate(Integer value) {
        if (value == null)
            return null;
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(1000l * (value + epochBase));
        return c.getTime();
    }
}
