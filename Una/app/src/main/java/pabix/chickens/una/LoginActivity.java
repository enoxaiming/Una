package pabix.chickens.una;

import android.support.v7.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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
import pabix.chickens.una.Management.UnaApplication;
import pabix.chickens.una.Management.UserManager;

public class LoginActivity extends AppCompatActivity {

    private CallbackManager callbackManager;
    private String URL;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private UserManager userManager;
    @BindView(R.id.fb_login_button) LoginButton loginButton;
    @BindView(R.id.Textview) TextView textView;
    @BindView(R.id.imageView) ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        pref = PreferenceManager.getDefaultSharedPreferences(UnaApplication.getContext());
        editor = pref.edit();
        editor.putBoolean("Login",false).commit();


        callbackManager = CallbackManager.Factory.create(); // callbackManager 선언
        ButterKnife.bind(this);



        loginButton.setReadPermissions("public_profile"); //Facebook API Permission

        userManager = UserManager.getInstance(); //For User Management
        
        //Facebook Login 버튼을 눌렀을 때
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
                                    editor.putBoolean("Login",true).commit();
                                    try {
                                        userManager.setUserName(user.getString("name"));
                                        userManager.setUserID(result.getAccessToken().getUserId());
                                        userManager.setToken(result.getAccessToken().getToken());
                                        textView.setText(user.getString("name"));
                                    } catch (JSONException e) {

                                    }
                                    setResult(RESULT_OK);
                                }
                            }
                        });
                        Bundle parameters = new Bundle();
                        parameters.putString("fields", "id,name,email,gender,birthday");
                        request.setParameters(parameters);
                        request.executeAsync();
                        AccessToken.setCurrentAccessToken(result.getAccessToken());
                        startActivity(new Intent(LoginActivity.this,MainActivity.class));
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
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    //Login Check
    public boolean isLoggedIn() {
        return AccessToken.getCurrentAccessToken() != null;
    }


}
