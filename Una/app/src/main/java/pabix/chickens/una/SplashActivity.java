package pabix.chickens.una;

import android.support.v7.app.ActionBar;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.facebook.AccessToken;

public class SplashActivity extends AppCompatActivity {

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
                //로그인 상태 확인 후 액티비티 이동
                //Login이 true 일때,
                if(isLoggedIn()) {
                    startActivity(new Intent(SplashActivity.this,MainActivity.class));
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
}
