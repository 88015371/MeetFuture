/*
package com.zjman.meetfuture.data.net.util;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import java.util.ArrayList;

//import com.earlydata.pillar.util.NetworkUtils;

*/
/**
 * Created by zchu on 17-3-2.
 *//*


public class NetStateReceiver extends BroadcastReceiver {

    private final static String ANDROID_NET_CHANGE_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";
    private final static String TAG = NetStateReceiver.class.getSimpleName();

    private static boolean isNetAvailable = false;
    private static NetworkUtils.NetworkType mNetType;
    private static ArrayList<NetChangeObserver> mNetChangeObservers = new ArrayList<NetChangeObserver>();
    private static BroadcastReceiver mBroadcastReceiver;

    private static BroadcastReceiver getReceiver() {
        if (null == mBroadcastReceiver) {
            synchronized (NetStateReceiver.class) {
                if (null == mBroadcastReceiver) {
                    mBroadcastReceiver = new NetStateReceiver();
                }
            }
        }
        return mBroadcastReceiver;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        mBroadcastReceiver = NetStateReceiver.this;
        if (intent.getAction().equalsIgnoreCase(ANDROID_NET_CHANGE_ACTION)) {
            if (!NetworkUtils.isConnected(context)) {
//                Log.e(this.getClass().getName(), "<--- network disconnected --->");
                isNetAvailable = false;
            } else {
//                Log.e(this.getClass().getName(), "<--- network connected --->");
                isNetAvailable = true;
                mNetType = NetworkUtils.getNetworkType(context);
            }
            notifyObserver();
        }
    }

    */
/**
     * 注册
     *
     * @param mContext
     *//*

    public static void registerNetworkStateReceiver(Context mContext) {
        IntentFilter filter = new IntentFilter();
        filter.addAction(ANDROID_NET_CHANGE_ACTION);
        mContext.getApplicationContext().registerReceiver(getReceiver(), filter);
    }

    */
/**
     * 清除
     *
     * @param mContext
     *//*

    public static void checkNetworkState(Context mContext) {
        Intent intent = new Intent();
        intent.setAction(ANDROID_NET_CHANGE_ACTION);
        mContext.sendBroadcast(intent);
    }

    */
/**
     * 反注册
     *
     * @param mContext
     *//*

    public static void unRegisterNetworkStateReceiver(Context mContext) {
        if (mBroadcastReceiver != null) {
            try {
                mContext.getApplicationContext().unregisterReceiver(mBroadcastReceiver);
            } catch (Exception e) {

            }
        }

    }

    public static boolean isNetworkAvailable() {
        return isNetAvailable;
    }

    public static NetworkUtils.NetworkType getAPNType() {
        return mNetType;
    }

    private void notifyObserver() {
        if (mNetChangeObservers.size()>0) {
            for (NetChangeObserver observer : mNetChangeObservers) {
                if (isNetworkAvailable()) {
                    observer.onNetConnected(mNetType);
                } else {
                    observer.onNetDisConnect();
                }
            }
        }
    }

    */
/**
     * 添加网络监听
     *
     * @param observer
     *//*

    public static void registerObserver(NetChangeObserver observer) {
        if (mNetChangeObservers == null) {
            mNetChangeObservers = new ArrayList<>();
        }
        if (observer != null) {
            mNetChangeObservers.add(observer);
        }
    }

    */
/**
     * 移除网络监听
     *
     * @param observer
     *//*

    public static void removeRegisterObserver(NetChangeObserver observer) {
        mNetChangeObservers.remove(observer);
    }
}


*/
