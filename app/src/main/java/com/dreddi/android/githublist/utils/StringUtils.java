package com.dreddi.android.githublist.utils;

import android.content.Context;

import com.dreddi.android.githublist.R;

public class StringUtils {
    public static String formatCount(Context context, long count) {
        if (count > 1000) {
            int countK = (int)(count / 1000);
            return context.getString(R.string.format_count_k, countK);
        } else {
            return String.valueOf(count);
        }
    }
}
