package pabix.chickens.una;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.facebook.AccessToken;

import butterknife.BindView;
import butterknife.ButterKnife;
import pabix.chickens.una.Management.UnaApplication;
import pabix.chickens.una.Management.UserManager;

public class MainActivity extends AppCompatActivity {
    private String URL;
    @BindView(R.id.imageView2) ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        URL = "https://graph.facebook.com/" + accessToken.getUserId() +"/picture?type=large";
        Glide.with(UnaApplication.getContext()).load(URL).skipMemoryCache(true).into(imageView); //MemoryCache Function OFF
    }
}
