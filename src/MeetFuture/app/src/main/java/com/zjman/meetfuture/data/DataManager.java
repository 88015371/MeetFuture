package com.zjman.meetfuture.data;

import com.zchu.rxcache.RxCache;
import com.zchu.rxcache.diskconverter.SerializableDiskConverter;
import com.zjman.meetfuture.AppContext;
import com.zjman.meetfuture.data.bean.HttpResult;
import com.zjman.meetfuture.data.body.GetVerifyCodeBody;
import com.zjman.meetfuture.data.net.RequestClient;

import java.io.File;

import rx.Observable;

/**
 * Created by zjman on 2018/5/16.
 *
 * 负责提供数据，这里采用了 Repository 模式，DataManager 就是一个仓库管理员，业务层 需要什么东西只需告诉仓库管理员，由仓库管理员把东西拿给它，并不需要知道东西实际放在哪。
 * 常见的数据来源有，RestAPI、SQLite数据库、本地缓存等
 *
 * ps：有些时候，访问数据压根就涉及不到什么业务逻辑，如：请求数据给一个列表展示，这就压根没有业务逻辑
 * 这时present直接访问数据层就可以了，当然最好还是写一个业务逻辑类，什么也不干，只是转发一下数据，以后突然有业务逻辑了，就只要关注这个业务逻辑类就可以了
 */

public class DataManager {

    private volatile static RxCache rxCache;

    private static RxCache getRxCache() {
        if (rxCache == null) {
            synchronized (DataManager.class) {
                if (rxCache == null) {
                    rxCache = new RxCache.Builder()
                            .appVersion(1)//不设置，默认为1
                            .diskDir(new File(AppContext.context().getCacheDir().getPath() + File.separator + "data-cache"))
                            .diskConverter(new SerializableDiskConverter())//目前只支持Serializable缓存
                            .memoryMax(2 * 1024 * 1024)//不设置,默认为运行内存的8分之1
                            .diskMax(20 * 1024 * 1024)//不设置， 默认为50MB
                            .build();
                }
            }
        }
        return rxCache;
    }

    /**
     * 获取短信验证码-注册
     */
    public static Observable<HttpResult> getVerifyCodeByRegister(String mobile) {
        return RequestClient
                .getServerAPI()
                .getVerifyCodeByRegister(new GetVerifyCodeBody(mobile));
    }

}
