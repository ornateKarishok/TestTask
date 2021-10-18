package com.mycompany.testtask.util;

import android.content.Context;
import android.content.res.Configuration;

public class DeviceUtil {
    public static boolean isTablet(Context context) {
        return (context.getResources().getConfiguration().screenLayout
                & Configuration.SCREENLAYOUT_SIZE_MASK)
                >= Configuration.SCREENLAYOUT_SIZE_LARGE;
    }
}
