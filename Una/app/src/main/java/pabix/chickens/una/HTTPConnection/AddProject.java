package pabix.chickens.una.HTTPConnection;

import java.util.List;

import okhttp3.ResponseBody;
import pabix.chickens.una.Database.ProjectVO;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by JunHyeok on 2016. 11. 7..
 */

public interface AddProject {
    @FormUrlEncoded
    @POST("project/list/insertProject")
        Call<List<SecondRepo>> doSignup(@Field("id") String id,
                                        @Field("projectName") String name,
                                        @Field("launcher") String host,
                                        @Field("contents") String contents,
                                        @Field("launchDate") String startDate,
                                        @Field("finishDate") String finishDate,
                                        @Field("isAvaliable") int isAvailable,
                                        @Field("Applicants") int applicants,
                                        @Field("wants") String wants);
}