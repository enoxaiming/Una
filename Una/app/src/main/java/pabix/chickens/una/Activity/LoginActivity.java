package pabix.chickens.una.Activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import io.realm.Realm;
import io.realm.RealmResults;
import pabix.chickens.una.Database.UserVO;
import pabix.chickens.una.HTTPConnection.AddProject;
import pabix.chickens.una.HTTPConnection.Repo;
import pabix.chickens.una.HTTPConnection.Successful;
import pabix.chickens.una.HTTPConnection.facebookidCheck;
import pabix.chickens.una.HTTPConnection.getProjects;
import pabix.chickens.una.HTTPConnection.insertDatas;
import pabix.chickens.una.Management.URLManager;
import pabix.chickens.una.Management.UnaApplication;
import pabix.chickens.una.R;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    private CallbackManager callbackManager;
    private Realm mRealm;
    private UserVO mUser;
    private JSONObject Users;
    @BindView(R.id.fb_login_button)
    LoginButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        putBitmap(R.id.background, R.drawable.back, 8);

        callbackManager = CallbackManager.Factory.create(); // callbackManager 선언
        ButterKnife.bind(this);

        mRealm.init(UnaApplication.getContext());

        loginButton.setReadPermissions("public_profile"); //Facebook API Permission

        //Facebook Login 버튼을 눌렀을 때
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(final LoginResult result) {

                final GraphRequest request;

                request = GraphRequest.newMeRequest(result.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {

                    @Override
                    public void onCompleted(JSONObject user, GraphResponse response) {
                        if (response.getError() != null) {

                        } else {
                            Log.i("TAG", "user: " + user.toString());
                            Log.i("TAG", "AccessToken: " + result.getAccessToken().getToken());
                            Log.i("ID", "ID : " + result.getAccessToken().getUserId());
                            setResult(RESULT_OK);
                            try {
                                insertuserData(user.getString("Name"),result.getAccessToken().getToken());
                            } catch (JSONException e) {
                                //TODO 이름확인
                            }
                        }
                    }
                });

                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,name,email,gender,birthday");
                request.setParameters(parameters);
                request.executeAsync();
                AccessToken.setCurrentAccessToken(result.getAccessToken());
                startActivity(new Intent(LoginActivity.this, NavigationDrawerActivity.class));
                finish();
            }

            @Override
            public void onError(FacebookException error) {
                Log.e("test", "Error: " + error);
                finish();
            }

            @Override
            public void onCancel() {
                finish();
            }
        });


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    //유저 정보 데이터 리스트 반환
    private RealmResults<UserVO> getUserList(){
        return mRealm.where(UserVO.class).findAll();
    }

    //유저 정보 데이터 DB 저장
    private void insertuserData(String name, String token){
        mRealm.beginTransaction();
        UserVO user = mRealm.createObject(UserVO.class);
        user.setName(name);
        user.setToken(token);
        mRealm.commitTransaction();
    }

    //유저 정보 데이터 DB 전부 삭제
    private void deleteuserData(){
        mRealm.beginTransaction();
        RealmResults<UserVO> userList = mRealm.where(UserVO.class).findAll();
        userList.deleteAllFromRealm();
        mRealm.commitTransaction();
    }

    private void doSignUp() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLManager.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final AddProject doSignup = retrofit.create(AddProject.class);

        Call<List<Successful>> call = doSignup.doSignup(
                "321321","fdsaf","31231","dasf","2016-08-01","2016-09-11",1,3,"fdaf;fdas;");

        call.enqueue(new Callback<List<Successful>>() {
            @Override
            public void onResponse(Call<List<Successful>> call, Response<List<Successful>> response) {
                Log.e("Log",response.message());
                Log.e("Log",String.valueOf(response.body().get(0).isSuccess()));
            }

            @Override
            public void onFailure(Call<List<Successful>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void getProject() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLManager.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final getProjects getProjects = retrofit.create(getProjects.class);

        Call<List<Repo>> call = getProjects.getProject();

        Log.e("start","se");
        call.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                Log.e("launcher",response.body().get(0).getLauncher());
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
    private void facebookIdCheck(String id) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLManager.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final facebookidCheck facebookidCheck = retrofit.create(pabix.chickens.una.HTTPConnection.facebookidCheck.class);
        Call<List<Successful>> call = facebookidCheck.checkID(id);

        call.enqueue(new Callback<List<Successful>>() {
            @Override
            public void onResponse(Call<List<Successful>> call, Response<List<Successful>> response) {
                if(!response.body().get(0).isSuccess()) {

                }
            }

            @Override
            public void onFailure(Call<List<Successful>> call, Throwable t) {

            }
        });

    }

    private void insert(String name,String id,String email,String gender,int kisu,String major,String main,String message,String joined) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URLManager.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        final insertDatas insertData = retrofit.create(insertDatas.class);

        Call<List<Successful>> call = insertData.insertData(name,id,email,gender,kisu,major,main,"","");

        call.enqueue(new Callback<List<Successful>>() {
            @Override
            public void onResponse(Call<List<Successful>> call, Response<List<Successful>> response) {

            }

            @Override
            public void onFailure(Call<List<Successful>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        recycleView(R.id.background);
    }

    private void putBitmap(int imageViewId, int drawableId, int scale) {
        ImageView imageView = (ImageView)findViewById(imageViewId);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = scale;

        imageView.setImageBitmap(BitmapFactory.decodeResource(getResources(), drawableId, options));
    }

    private void recycleView(int id) {
        ImageView view = (ImageView)findViewById(id);

        Drawable d = view.getDrawable();
        if(d instanceof BitmapDrawable) {
            Bitmap b = ((BitmapDrawable) d).getBitmap();
            view.setImageBitmap(null);
            b.recycle();
            b = null;
        }
        d.setCallback(null);
        System.gc();
        Runtime.getRuntime().gc();
    }


}
