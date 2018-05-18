package com.zjman.meetfuture.util;

import com.google.gson.Gson;

/**
 * Created by zjman on 17-1-11.
 * 提供全局的Gson单例
 */

public class GsonProvide {

    public static final Gson GSON = new Gson();

    private GsonProvide() {
    }

    /**
     * 向下兼容,已弃用，请直接通过GsonProvide.GSON获取单例
     */
    @Deprecated
    public static Gson getGson() {
        return GSON;
    }
}
