package com.example.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Log;

/**
 * Created by nadeem on 16-06-2015.
 */
public class SharedPreferenceHandler {
    static SharedPreferences commonSharedPreference = null;

    public static boolean writeValue(Context context, String key, String value) {
        boolean flag = false;
        try {
            commonSharedPreference = PreferenceManager.getDefaultSharedPreferences(context);
            SharedPreferences.Editor editor = commonSharedPreference.edit();
            editor.putString(key, value);
            editor.apply();
            flag = true;
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    public static String readValue(Context context, String key) {
        String value = "";
        try {
            commonSharedPreference = PreferenceManager.getDefaultSharedPreferences(context);
            value = commonSharedPreference.getString(key, "");
            if (!value.equalsIgnoreCase("")) {
                Log.d("value:", value);
            }

        } catch (Exception e) {

        }
        return value;
    }
}
