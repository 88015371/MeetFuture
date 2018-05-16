package com.zjman.meetfuture;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

/**
 * Created by zjman on 2018/5/16.
 */

public class AppContext extends MultiDexApplication {

    private static AppContext sContext = null;

    public static long sStartTime = System.currentTimeMillis();

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        sContext = this;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        // 做些初始化的工作
    }


    public static AppContext context() {
        return sContext;
    }
}
