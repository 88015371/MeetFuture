package com.zjman.meetfuture.data.net.util;


import com.zjman.meetfuture.net.network.NetworkType;

public interface NetChangeObserver {
    /**
     * 网络连接回调 type为网络类型
     */
    void onNetConnected(NetworkType type);

    /**
     * 没有网络
     */
    void onNetDisConnect();

}
