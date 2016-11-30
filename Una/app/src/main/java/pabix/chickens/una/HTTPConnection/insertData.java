package pabix.chickens.una.HTTPConnection;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.POST;

/**
 * Created by JunHyeok on 2016. 11. 30..
 */

public interface insertData {
    @POST("user/signup/insertData")
    Call<Repo> insertDatas (@Field("username") String name,
                           @Field("id") String id,
                           @Field("email") String email,
                           @Field("gender") String gender,
                           @Field("kisu") int kisu,
                           @Field("major") String major,
                           @Field("main") String main,
                           @Field("state_message") String message,
                           @Field("joined") String joined);
}
