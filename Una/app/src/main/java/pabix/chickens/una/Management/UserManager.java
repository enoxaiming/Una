package pabix.chickens.una.Management;

/**
 * Created by JunHyeok on 2016. 12. 11..
 */
public class UserManager {
    private static UserManager ourInstance = new UserManager();

    public static UserManager getInstance() {
        return ourInstance;
    }

    private UserManager() {
    }
}
