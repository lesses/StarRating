package sympubway.calculate.starrating.dao;

/**
 * Created by zsr on 2017/2/8.
 */
public class DAOFactory {

    private final static DAOFactory instance = new DAOFactory();

    public static DAOFactory getInstance() {
        return instance;
    }
}
