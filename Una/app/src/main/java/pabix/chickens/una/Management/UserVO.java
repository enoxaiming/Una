package pabix.chickens.una.Management;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by JunHyeok on 2016. 10. 10..
 */

public class UserVO extends RealmObject {

    @PrimaryKey
    private String name;
    private String token;

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
