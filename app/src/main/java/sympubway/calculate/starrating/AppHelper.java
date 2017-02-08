package sympubway.calculate.starrating;

/**
 * Created by zsr on 2017/2/8.
 */
public class AppHelper {
    private static AppHelper instance = new AppHelper();

    private AppHelper(){

    }

    public static AppHelper getInstance() {
        return instance;
    }
}
