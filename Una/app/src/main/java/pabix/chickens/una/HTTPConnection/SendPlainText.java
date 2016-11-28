package pabix.chickens.una.HTTPConnection;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by JunHyeok on 2016. 11. 28..
 */

public interface SendPlainText {
    @POST("project/list/insertProject")
    Call<ResponseBody> getString(@Body RequestBody body);
}
