package by.of.servicebook.myapplication;

import android.content.Context;
import android.content.SharedPreferences;

import com.activeandroid.ActiveAndroid;
import com.activeandroid.app.Application;
import com.parse.Parse;

import by.of.servicebook.myapplication.utils.AppConst;

/**
 * Created by Pavel on 10.12.2014.
 */
public class MyApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);

        Parse.initialize(this, "ZjTI65G77ck4IneaorWJTVdZnUz6YBUTkC8GOpxh", "h9gPunO5xfdcEF4yVQXoRGhjkRaUzEc550GCZvi1");
    }
}
