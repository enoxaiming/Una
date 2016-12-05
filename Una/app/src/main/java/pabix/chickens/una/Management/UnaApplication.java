package pabix.chickens.una.Management;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.facebook.AccessToken;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;

import org.json.JSONObject;

import io.realm.Realm;

/**
 * Created by JunHyeok on 2016. 10. 6..
 */

public class UnaApplication extends Application {

    public static Context context;


    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        FacebookSdk.sdkInitialize(this);
        AppEventsLogger.activateApp(this);
        context = this;
        if (AccessToken.getCurrentAccessToken() != null) {
            GraphRequest.newMeRequest(AccessToken.getCurrentAccessToken(), new GraphRequest.GraphJSONObjectCallback() {

                @Override
                public void onCompleted(JSONObject user, GraphResponse response) {
                    if (response.getError() != null) {

                    } else {
                        //TODO JSON OBJECT 생성해서 UserManager에 넣기
                        Log.i("TAG", "user: " + user.toString());
                        Log.i("TAG", "AccessToken: " + AccessToken.getCurrentAccessToken().getToken());
                        Log.i("ID", "ID : " + AccessToken.getCurrentAccessToken().getUserId());
                    }
                }
            });
        }
    }

    public static Context getContext() {
        return context;
    }


}
