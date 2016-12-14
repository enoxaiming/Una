package pabix.chickens.una.Management;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginManager;

import org.json.JSONObject;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by JunHyeok on 2016. 10. 6..
 */

public class UnaApplication extends Application {

    public static Context context;
    private RealmConfiguration realmConfiguration;


    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        Realm.setDefaultConfiguration(getRealmConfig());
        FacebookSdk.sdkInitialize(this);
        AppEventsLogger.activateApp(this);
        context = this;
        LoginManager.getInstance().logOut();
    }

    public static Context getContext() {
        return context;
    }

    protected RealmConfiguration getRealmConfig() {
        if (realmConfiguration == null) {
            realmConfiguration = new RealmConfiguration
                    .Builder()
                    .deleteRealmIfMigrationNeeded()
                    .build();
        }
        return realmConfiguration;
    }


}
