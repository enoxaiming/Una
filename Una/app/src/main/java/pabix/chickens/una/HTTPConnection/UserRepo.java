package pabix.chickens.una.HTTPConnection;

/**
 * Created by JunHyeok on 2016. 11. 30..
 */

public class UserRepo {
    private boolean isSuccess;
    private String major;
    private String gender;
    private String username; //이름
    private String state_message;
    private String email;
    private int kisu;
    private String joined;
    private String main;
    private String id; //페이스북 아이디

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getState_message() {
        return state_message;
    }

    public void setState_message(String state_message) {
        this.state_message = state_message;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getKisu() {
        return kisu;
    }

    public void setKisu(int kisu) {
        this.kisu = kisu;
    }

    public String getJoined() {
        return joined;
    }

    public void setJoined(String joined) {
        this.joined = joined;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
