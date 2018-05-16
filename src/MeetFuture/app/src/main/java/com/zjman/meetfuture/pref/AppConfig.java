package com.zjman.meetfuture.pref;

import com.zjman.meetfuture.AppContext;

import java.io.File;

/**
 * Created by zjman on 2018/5/16.
 * app设置管理类
 */

public class AppConfig {

    public final static String APP_CONFIG = "appConfig";
    // 默认存放图片的路径
    public static final String IMAGE_CACHE = AppContext.context().getCacheDir().getPath() + File.separator + "image-cache";

    // 默认存放数据缓存的路径
    public static final String DATA_CACHE = AppContext.context().getCacheDir().getPath() + File.separator + "data-cache";

    //图片文件存放路径
    public static final String IMAGE_FILE_PATH =  AppContext.context().getExternalFilesDir("image").getAbsolutePath();

    //APK文件存放路径
    public static final String APK_FILE_PATH =  AppContext.context().getExternalFilesDir("apk").getAbsolutePath();

    //日志文件存放路径
    public final static String LOG_FILE_PATH = AppContext.context().getFilesDir().getPath() + File.separator + "log";
}
