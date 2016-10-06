package pabix.chickens.una.Management;

/**
 * Created by JunHyeok on 2016. 10. 6..
 */

public class UserManager {
    private static UserManager instance = new UserManager();

    private String token;
    private String userID;
    private String userName;

    private UserManager(){

    }

    public static UserManager getInstance(){
        return instance;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
