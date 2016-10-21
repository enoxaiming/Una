package pabix.chickens.una.Database;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by JunHyeok on 2016. 10. 10..
 */

public class UserVO extends RealmObject implements io.realm.RealmModel {

    @PrimaryKey
    private String name;
    private String token;
    private String sex;
    private String id;
    private int kisu;
    private String major;
    //TODO Realm String List
    /*private RealmList<String> projects;
    private String[] main;*/

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getKisu() {
        return kisu;
    }

    public void setKisu(int kisu) {
        this.kisu = kisu;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
