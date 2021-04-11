package com.rev.c25k.model;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class Settings {
    public static final String PREFERENCES = "user_preferences";
    private static final String SETTINGS_WARM_UP = "SETTINGS_WARM_UP";
    private static final String DEFAULT_WARM_UP_TIME = "5";

    public static String getWarmUpTime(Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCES, MODE_PRIVATE);

        if (!preferences.contains(SETTINGS_WARM_UP)) {
            return DEFAULT_WARM_UP_TIME;
        }

        return preferences.getString(SETTINGS_WARM_UP, DEFAULT_WARM_UP_TIME);
    }

    public static void saveWarmUpTime(String warmUpTime, Context context) {
        SharedPreferences preferences = context.getSharedPreferences(PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(SETTINGS_WARM_UP, warmUpTime);
        editor.apply();
    }
}
