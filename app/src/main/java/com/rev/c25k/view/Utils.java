package com.rev.c25k.view;

import android.content.Context;

import com.rev.c25k.R;

public class Utils {

    public static String formatTimeText(int time, Context context) {
        if (time < 60) {
            return String.format("%s %s", time, context.getString(R.string.seconds));
        } else {
            int formattedTime = time / 60;
            return String.format("%s %s", formattedTime, context.getString(R.string.minutes));
        }
    }
}
