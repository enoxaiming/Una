package pabix.chickens.una;

import android.app.Application;
import android.content.Context;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

/**
 * Created by JunHyeok on 2016. 10. 6..
 */

public class UnaApplication extends Application {

    public static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        context = this;
    }

    public static Context getContext() {
        return context;
    }
}
