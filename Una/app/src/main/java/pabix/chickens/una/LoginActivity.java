package pabix.chickens.una;

import android.support.v7.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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

import butterknife.BindView;
import butterknife.ButterKnife;

import io.realm.Realm;
import io.realm.RealmResults;
import pabix.chickens.una.Database.UserVO;
import pabix.chickens.una.Management.UnaApplication;

public class LoginActivity extends AppCompatActivity {

    private CallbackManager callbackManager;
    private Realm mRealm;
    private UserVO mUser;
    private JSONObject Users;
    @BindView(R.id.fb_login_button)
    LoginButton loginButton;
    @BindView(R.id.Textview)
    TextView textView;
    @BindView(R.id.imageView)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

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


}
