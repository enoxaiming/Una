package pabix.chickens.una.HTTPConnection;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by JunHyeok on 2016. 11. 30..
 */

public interface facebookidCheck {
    @GET("userAuth/checkuser")
    Call<List<Successful>> checkID(@Query("id") String id);

}
