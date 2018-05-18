package com.zjman.meetfuture;

import android.app.ActivityManager;
import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.r0adkll.slidr.model.SlidrConfig;
import com.squareup.leakcanary.LeakCanary;
import com.zchu.log.LogLevel;
import com.zchu.log.Logger;
import com.zchu.log.Settings;
import com.zjman.meetfuture.dagger.component.AppComponent;
import com.zjman.meetfuture.dagger.component.DaggerAppComponent;
import com.zjman.meetfuture.util.SharedHelper;
import com.zjman.meetfuture.util.SysUtil;
import com.zjman.meetfuture.util.ToastUtil;
import com.zjman.meetfuture.dagger.module.AppModule;

import java.util.List;

import io.realm.DynamicRealm;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;


/**
 * Created by zjman on 2018/5/16.
 */

public class AppContext extends MultiDexApplication {

    private static AppContext sContext = null;

    public static long sStartTime = System.currentTimeMillis();

    public String mUserId;

    public String mAccessToken;

    public String getTOKEN_KEY() {
        return TOKEN_KEY;
    }

    public String getUSER_ID() {
        return USER_ID;
    }

    public final String  TOKEN_KEY = "TOKEN_KEY";

    public final String  USER_ID = "USER_ID";

    public AppComponent appComponent;

    public String realmName = "first.realm"; // 数据库名称


    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        sContext = this;

    }


    @Override
    public void onCreate() {
        super.onCreate();
        // 做些初始化的工作

        //初始化AppManager
        registerActivityLifecycleCallbacks(AppManager.INSTANCE);
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
        //初始化全局异常捕获
        Thread.setDefaultUncaughtExceptionHandler(AppException.getAppExceptionHandler());
        //初始化内存泄漏检测器
        LeakCanary.install(this);
        //初始化日志打印器
        Settings settings = Logger.init("Xxg");

        ToastUtil.init(this);

        if (!BuildConfig.DEBUG) {
            settings.setLogLevel(LogLevel.NONE);
        }
        SysUtil.init(this);

        creatRealm();

        initToken();
    }

    private void creatRealm() {

        Realm.init(this);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder().schemaVersion(1).name(realmName).build();
        RealmMigration realmMigration = new RealmMigration() {
            @Override
            public void migrate(DynamicRealm realm, long oldVersion, long newVersion) {

                if (oldVersion == 0L) {
                    realm.getSchema().get("UserModel")
                            .addField("name_auth", String.class);
//                        .addField("id", Long::class.javaPrimitiveType, FieldAttribute.PRIMARY_KEY)
//                        .addRealmObjectField("favoriteDog", schema.get("Dog"))
//                        .addRealmListField("dogs", schema.get("Dog"))
                    oldVersion++;


                }
            }

        };
        Realm.setDefaultConfiguration(realmConfiguration);


    }

    public String getmUserId() {
        return mUserId;
    }

    public void setmUserId(String mUserId) {
        this.mUserId = mUserId;
    }

    public String getmAccessToken() {
        return mAccessToken;
    }

    public void setmAccessToken(String mAccessToken) {
        this.mAccessToken = mAccessToken;
    }

    private void initToken() {

        mAccessToken = (String) SharedHelper.get(TOKEN_KEY,"");
        mUserId = (String) SharedHelper.get(USER_ID, "");

    }





    public static AppContext context() {
        return sContext;
    }


    /**
     * 侧滑返回的样式
     /*  */
    public static SlidrConfig getSlidrConfig() {
        return new SlidrConfig
                .Builder()
                .edge(true)
                .edgeSize(0.18f)// The % of the screen that counts as the edge, default 18%
                .build();
    }

    /**
     * 获取当前进程名
     */
    public String getProcessName() {
        ActivityManager am = (ActivityManager) this.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> runningApps = am.getRunningAppProcesses();
        if (runningApps != null && !runningApps.isEmpty()) {
            for (ActivityManager.RunningAppProcessInfo procInfo : runningApps) {
                if (procInfo.pid == android.os.Process.myPid()) {
                    return procInfo.processName;
                }
            }
        }
        return null;
    }
}
