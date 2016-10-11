package pabix.chickens.una.Management;

/**
 * Created by JunHyeok on 2016. 10. 10..
 */

public class UserVO extends RealmObject{

    @PrimaryKey
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
    Colored by Color Scripter
cs

