package com.zjman.meetfuture.util;

import android.Manifest;
import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;



/**
 * Created by Teprinciple on 2016/11/15.
 */
public class UpdateAppUtils {

    private final String TAG = "UpdateAppUtils";
    public static final int CHECK_BY_VERSION_NAME = 1001;
    public static final int CHECK_BY_VERSION_CODE = 1002;
    public static final int DOWNLOAD_BY_APP = 1003;
    public static final int DOWNLOAD_BY_BROWSER = 1004;

    private Activity activity;
    private int checkBy = CHECK_BY_VERSION_CODE;
    private int downloadBy = DOWNLOAD_BY_APP;
    private int serverVersionCode = 0;
    private String apkPath = "";
    private String serverVersionName = "";
    private String serverVersionContent = "";
    private boolean isForce = false; //是否强制更新
    private int localVersionCode = 0;
    private String localVersionName = "";


    private UpdateAppUtils(Activity activity) {
        this.activity = activity;
        getAPPLocalVersion(activity);
    }

    public static UpdateAppUtils from(Activity activity) {
        return new UpdateAppUtils(activity);
    }

    public UpdateAppUtils checkBy(int checkBy) {
        this.checkBy = checkBy;
        return this;
    }

    public UpdateAppUtils apkPath(String apkPath) {
        this.apkPath = apkPath;
        return this;
    }

    public UpdateAppUtils downloadBy(int downloadBy) {
        this.downloadBy = downloadBy;
        return this;
    }

    public UpdateAppUtils serverVersionCode(int serverVersionCode) {
        this.serverVersionCode = serverVersionCode;
        return this;
    }

    public UpdateAppUtils serverVersionName(String serverVersionName) {
        this.serverVersionName = serverVersionName;
        return this;
    }

    public UpdateAppUtils serverVersionContent(String serverVersionContent) {
        this.serverVersionContent = serverVersionContent;
        return this;
    }

    public UpdateAppUtils isForce(boolean isForce) {
        this.isForce = isForce;
        return this;
    }

    //获取apk的版本号 currentVersionCode
    private void getAPPLocalVersion(Context ctx) {
        PackageManager manager = ctx.getPackageManager();
        try {
            PackageInfo info = manager.getPackageInfo(ctx.getPackageName(), 0);
            localVersionName = info.versionName; // 版本名
            localVersionCode = info.versionCode; // 版本号
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        switch (checkBy) {
            case CHECK_BY_VERSION_CODE:
                if (serverVersionCode > localVersionCode) {
                    toUpdate();
                } else {
                    Log.i(TAG, "当前版本是最新版本" + serverVersionCode + "/" + serverVersionName);
                }
                break;

            case CHECK_BY_VERSION_NAME:
                if (!serverVersionName.equals(localVersionName)) {
                    toUpdate();
                } else {
                    Log.i(TAG, "当前版本是最新版本" + serverVersionCode + "/" + serverVersionName);
                }
                break;
        }

    }

    private void toUpdate() {

        realUpdate();

    }

    private void realUpdate() {
   /*     ConfirmDialog dialog = new ConfirmDialog(activity, new ConfirmDialog.Callback() {
            @Override
            public void callback(int position) {
                switch (position) {
                    case 0:  //cancle
                        if (isForce) {
                            NotificationManager notificationManager = (NotificationManager) AppContext.context()
                                    .getSystemService(Activity.NOTIFICATION_SERVICE);
                            notificationManager.cancelAll();
                            AppManager.INSTANCE.finishAllActivity();
                        }
                        break;
                    case 1:  //sure
                        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
                            realUpdate();
                        } else {
                            if (ContextCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE)
                                    == PackageManager.PERMISSION_GRANTED) {
                                downLoadApp();
                            } else {//申请权限
                                ActivityCompat.requestPermissions(activity,
                                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                            }
                        }
                        break;
                }
            }
        });
        dialog.setTitle("发现新版本");
        dialog.setContent(serverVersionContent);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        dialog.show();*/
    }

    public void downLoadApp() {
        if (downloadBy == DOWNLOAD_BY_APP) {
            DownloadAppUtils.downloadForAutoInstall(activity, apkPath, getAppName() + ".apk", getAppName() + serverVersionName);
        } else if (downloadBy == DOWNLOAD_BY_BROWSER) {
            DownloadAppUtils.downloadForWebView(activity, apkPath);
        }
    }


    private String getAppName() {

        if (SystemTool.getAppName(activity) != null) {
            return SystemTool.getAppName(activity);
        } else {
            return "萌犸象";
        }


    }


}
