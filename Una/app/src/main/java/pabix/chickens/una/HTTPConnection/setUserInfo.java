package pabix.chickens.una.HTTPConnection;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by JunHyeok on 2016. 11. 30..
 */

public interface setUserInfo {
    @GET("userAuth/userInfo")
    Call<List<UserRepo>> setUser(@Query("id") String id);
}
