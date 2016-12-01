package pabix.chickens.una.HTTPConnection;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by JunHyeok on 2016. 11. 30..
 */

public interface getProjects {
    @GET("project/list/allProjects")
    Call<List<Repo>> getProject();
}
