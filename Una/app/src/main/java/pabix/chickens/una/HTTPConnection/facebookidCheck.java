package pabix.chickens.una.HTTPConnection;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by JunHyeok on 2016. 11. 30..
 */

public interface facebookidCheck {
    @GET("userAuth/checkuser")
    Call<Repo> checkID(@Query("id") String id);

}
