package pabix.chickens.una.Activity;

import android.support.v7.app.ActionBar;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;

import com.facebook.AccessToken;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import pabix.chickens.una.Database.ProjectVO;
import pabix.chickens.una.HTTPConnection.Repo;
import pabix.chickens.una.HTTPConnection.getProjects;
import pabix.chickens.una.Management.URLManager;
import pabix.chickens.una.Management.UnaApplication;
import pabix.chickens.una.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SplashActivity extends AppCompatActivity {

    private Realm mRealm = Realm.getDefaultInstance();
    private RealmConfiguration realmConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.e("Login",String.valueOf(isLoggedIn()));
                getProject();

                //로그인 상태 확인 후 액티비티 이동
                //Login이 true 일때,
                if(isLoggedIn()) {
                    startActivity(new Intent(SplashActivity.this,NavigationDrawerActivity.class));
                    finish();
                }
                //Login이 false 일때
                else {
                    startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                    finish();
                }
                /*startActivity(new Intent(SplashActivity.this,LoginActivity.class));
                finish();*/
            }
        }, 3000);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    //Login Check
    public boolean isLoggedIn() {
        return AccessToken.getCurrentAccessToken() != null;
    }

    private void getProject() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLManager.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final getProjects getProjects = retrofit.create(getProjects.class);

        Call<List<Repo>> call = getProjects.getProject();

        call.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                Log.e("cout",String.valueOf(response.body().size()));
                insertData(response.body().size(),response.body());
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void deleteuserData(){

        mRealm.beginTransaction();

        RealmResults<ProjectVO> projectList = mRealm.where(ProjectVO.class).findAll();
        projectList.deleteAllFromRealm();

        mRealm.commitTransaction();
    }


    private void insertData(int size,List<Repo> list) {
        mRealm.beginTransaction();
        ProjectVO projects = new ProjectVO();
        for(int count = 0 ; count < size; count++) {
            projects.setProject_idx(list.get(count).getProject_idx());
            projects.setApplicants(list.get(count).getApplicants());
            projects.setAvaliable(list.get(count).getIsAvaliable());
            projects.setContents(list.get(count).getContents());
            projects.setId(list.get(count).getId());
            projects.setWants(list.get(count).getWants());
            projects.setView_count(list.get(count).getView_count());
            projects.setLaunchDate(list.get(count).getLaunchDate());
            projects.setFinishDate(list.get(count).getFinishDate());
            projects.setSubscriber(list.get(count).getSubscriber());
            projects.setLike_count(list.get(count).getLike_count());
            projects.setProjectName(list.get(count).getProjectName());
            projects.setLauncher(list.get(count).getLauncher());
            mRealm.copyToRealmOrUpdate(projects);
        }
        mRealm.commitTransaction();
    }
}
