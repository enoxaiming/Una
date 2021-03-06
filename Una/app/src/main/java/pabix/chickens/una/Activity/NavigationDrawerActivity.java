package pabix.chickens.una.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.login.LoginManager;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

import io.realm.Realm;

import pabix.chickens.una.Fragments.CompetitionListFragment;
import pabix.chickens.una.Fragments.MyPageFragment;
import pabix.chickens.una.Fragments.ProjectLikedFragment;
import pabix.chickens.una.Fragments.ProjectListFragment;
import pabix.chickens.una.Management.UnaApplication;
import pabix.chickens.una.Management.UserManager;
import pabix.chickens.una.R;

public class NavigationDrawerActivity extends AppCompatActivity
        implements  NavigationView.OnNavigationItemSelectedListener, ProjectListFragment.OnFragmentInteractionListener, CompetitionListFragment.OnFragmentInteractionListener, ProjectLikedFragment.OnFragmentInteractionListener,MyPageFragment.OnFragmentInteractionListener{

    private SearchView mSearchView;
    private ImageView userImage;
    private TextView userName,userIntroduce;
    private boolean searchViewOn = true;
    private Activity activity;
    private long backKeyPressedTime = 0;
    private Toolbar toolbar;
    private MenuItem searchItem;
    public static Context contexts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation_drawer);
        Realm.init(UnaApplication.getContext());
        ButterKnife.bind(this);

        toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setBackgroundColor(getResources().getColor(R.color.maincolor));
        toolbar.setTitle("");

        toolbar.inflateMenu(R.menu.search);
        setSupportActionBar(toolbar);

        contexts = this;

        mSearchView = (SearchView) toolbar.getMenu().findItem(R.id.menu_search).getActionView();

        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


        activity = this;
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View view = navigationView.getHeaderView(0);

        userImage = (ImageView)view.findViewById(R.id.imageView);
        userName = (TextView)view.findViewById(R.id.name);
        userIntroduce = (TextView)view.findViewById(R.id.introduce);

        String URL = "https://graph.facebook.com/" + UserManager.getInstance().getId() +"/picture?type=large";
        Glide.with(UnaApplication.getContext()).load(URL).skipMemoryCache(true).into(userImage);

        userName.setText(UserManager.getInstance().getName());
        userIntroduce.setText(UserManager.getInstance().getId());




        int[] icons = {R.drawable.tab_home,
                R.drawable.tab_like,
                R.drawable.tab_info,
                R.drawable.tab_mypage
        };

        //String[] titles = {"홈","내 프로젝트","대회정보","마이페이지"};



        final TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        ViewPager viewPager = (ViewPager) findViewById(R.id.main_tab_content);

        setupViewPager(viewPager);

        tabLayout.setupWithViewPager(viewPager);

        for (int i = 0; i < icons.length; i++) {
            tabLayout.getTabAt(i).setIcon(icons[i]);

            //tabLayout.getTabAt(i).setText(titles[i]);
        }

        /*for (int i = 0; i < tabLayout.getTabCount(); i++) {
            ImageView imageView = new ImageView(UnaApplication.getContext());
            imageView.setImageResource(icons[i]);
            tabLayout.getTabAt(i).setCustomView(imageView);
        }*/

        /*for(int i = 0 ; i < tabLayout.getChildCount(); ) {
            tabLayout.getChildAt(i).setPadding(10,10,10,10);
        }*/


        tabLayout.getTabAt(0).select();


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(!searchViewOn) {
                    mSearchView = (SearchView) toolbar.getMenu().findItem(R.id.menu_search).getActionView();

                    mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                        @Override
                        public boolean onQueryTextSubmit(String query) {
                            return false;
                        }

                        @Override
                        public boolean onQueryTextChange(String newText) {
                            return false;
                        }
                    });
                    searchViewOn = true;
                }
                else {
                    searchViewOn = false;
                }
                int tabBarIcon = ContextCompat.getColor(UnaApplication.getContext(),R.color.maincolor);
                tab.getIcon().setColorFilter(tabBarIcon, PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                int tabBarIcon = ContextCompat.getColor(UnaApplication.getContext(),R.color.tabDark);
                tab.getIcon().setColorFilter(tabBarIcon, PorterDuff.Mode.SRC_IN);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.insertNewFragment(new ProjectListFragment());
        adapter.insertNewFragment(new ProjectLikedFragment());
        adapter.insertNewFragment(new CompetitionListFragment());
        adapter.insertNewFragment(new MyPageFragment());
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            //Add some code
            //to verify that it will not return null
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void insertNewFragment(Fragment fragment) {
            mFragmentList.add(fragment);
        }
    }




    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        Log.e("icon",String.valueOf(mSearchView.isIconified()));
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if(!mSearchView.isIconified()) {
                searchItem.collapseActionView();
            }
            if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
                backKeyPressedTime = System.currentTimeMillis();
                Toast.makeText(activity, "\'뒤로\'버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show();
                return;
            }
            if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
                activity.finish();
            }
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search, menu);
        searchItem = menu.findItem(R.id.menu_search);
        searchItem.expandActionView();
        MenuItemCompat.setOnActionExpandListener(searchItem, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {
                return true;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                return true;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menu_search) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            //startActivity(new Intent(NavigationDrawerActivity.this,SearchViewActivity.class));
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void onDestroy() {
        LoginManager.getInstance().logOut();
    }




}
