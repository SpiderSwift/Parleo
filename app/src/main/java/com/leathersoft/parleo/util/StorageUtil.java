package com.leathersoft.parleo.util;

import android.content.Context;
import android.content.SharedPreferences;

public class StorageUtil {

    private static final String NAME = "Parleo";

    public static void save(Context context, String key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor ed = sharedPreferences.edit();
        ed.putString(key, value);
        ed.apply();
    }

    public static String load(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, "");
    }


}
