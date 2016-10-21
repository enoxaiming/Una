package pabix.chickens.una;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private SwipeRefreshLayout mSwipeRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSwipeRefresh = (SwipeRefreshLayout)findViewById(R.id.activity_main_swipe_refresh_layout);

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

        //새로고침 작업이 끝나고 애니메이션효과도 닫혀야 되기\ 때문에 setRefreshing(false)로 하여 작업을 종료합니다.
        mSwipeRefresh.setRefreshing(false);
    }

}
