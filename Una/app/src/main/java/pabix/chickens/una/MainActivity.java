package pabix.chickens.una;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.facebook.AccessToken;

import butterknife.BindView;
import butterknife.ButterKnife;
import pabix.chickens.una.Management.UnaApplication;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private String URL;
    private SwipeRefreshLayout mSwipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSwipeRefresh = (SwipeRefreshLayout)findViewById(R.id.activity_main_swipe_refresh_layout);
        ListView mListView = (ListView)findViewById(R.id.activity_main_listview);

        mSwipeRefresh.setOnRefreshListener(this);



        /*ButterKnife.bind(this);

        AccessToken accessToken = AccessToken.getCurrentAccessToken();

        URL = "https://graph.facebook.com/" + accessToken.getUserId() +"/picture?type=large";
        Log.e("Name",accessToken.getToken());
        Glide.with(UnaApplication.getContext()).load(URL).skipMemoryCache(true).into(imageView);*/ //MemoryCache Function OFF
    }

    @Override
    public void onRefresh() {
        //새로고침시 이벤트 구현

        //새로고침 작업이 끝나고 애니메이션효과도 닫혀야 되기 때문에 setRefreshing(false)로 하여 작업을 종료합니다.
        mSwipeRefresh.setRefreshing(false);
    }

}
