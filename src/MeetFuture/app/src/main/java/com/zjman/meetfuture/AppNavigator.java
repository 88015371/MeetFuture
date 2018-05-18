package com.zjman.meetfuture;

import android.content.Context;

import com.zjman.meetfuture.exception.CrashActivity;

/**
 * Created by zjman on 2018/5/16.
 */

public class AppNavigator {


    /**
     * 显示崩溃提升
     */
    public static void showCrashActivity(Context context, String message, final String errorInfo) {
        context.startActivity(CrashActivity.newIntent(context, message, errorInfo));
    }


}
