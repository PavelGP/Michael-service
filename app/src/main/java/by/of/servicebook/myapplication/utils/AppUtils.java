package by.of.servicebook.myapplication.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Pavel on 28.04.2015.
 */
public class AppUtils {
    private static final String APP_PREFERENCES = "app_preferences";
    private static final String APP_TIMESTAMP = "app_timestamp";

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni == null) {
            return false;
        } else
            return true;
    }

    public static void setTimeStamp(Context context, long timeStamp){
        SharedPreferences preferences = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putLong(APP_TIMESTAMP, timeStamp);
        editor.commit();
    }

    public static long getTimeStamp(Context context){
        SharedPreferences preferences = context.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
        return preferences.getLong(APP_TIMESTAMP, 0);
    }

}
