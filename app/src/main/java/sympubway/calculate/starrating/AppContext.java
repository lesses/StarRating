package sympubway.calculate.starrating;

import android.app.Activity;

import sympubway.calculate.starrating.sqlite.DBManager;

/**
 * Created by zsr on 2017/2/8.
 */
public class AppContext {
    private static final AppContext instance = new AppContext();
    private Activity mainActivity;

    public static AppContext getInstance() {
        return instance;
    }

    private DBManager dbMgr;

    void initialiseContext(Activity activity){
        mainActivity = activity;
        dbMgr = new DBManager(mainActivity);
    }

    public DBManager getDbManager() {
        return dbMgr;
    }
}
