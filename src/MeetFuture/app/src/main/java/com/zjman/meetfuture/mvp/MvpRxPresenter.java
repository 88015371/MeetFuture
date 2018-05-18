package com.zjman.meetfuture.mvp;


import android.app.Activity;

import java.util.HashMap;

import retrofit2.Retrofit;
import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by hank on 2017/6/8.
 *  方便使用Rxjava的Presenter类
 */

public abstract class MvpRxPresenter<V extends MvpView> extends MvpBasePresenter<V> {

    // 管理所有的Subscription, 便于回收资源
    private CompositeSubscription mSubParent;
    private HashMap<String,Subscription> mSubMap;
    protected V mView;
    protected Activity activity;
    protected Retrofit retrofit;

     public MvpRxPresenter(Retrofit retrofit, Activity activity) {
        this.activity = activity;
        this.retrofit = retrofit;

    }

    @Override
    public void attachView(V view) {
        //super.attachView(view);
        this.mView = view;
        mSubParent = new CompositeSubscription();
        mSubMap = new HashMap<>();
    }


    @Override
    public void detachView() {
       // super.detachView();
        this.mView = null;
        // 与View断开联系时,取消注册Rxjava,防止内存溢出
        if (mSubParent.hasSubscriptions()){
            mSubParent.unsubscribe();
            mSubParent= null;
        }

        if (!mSubMap.isEmpty()){
            mSubMap.clear();
            mSubMap = null;

        }
    }

    /**
     * 通过该方法添加的Subscription，会在Presenter与View解绑时unSubscribe
     */
    protected void addSubscription(Subscription s) {
        if (mSubParent != null)
            mSubParent.add(s);
    }

    /**
     * 通过该方法添加相同的key的Subscription，会把原来的Subscription remove and unSubscribe,并会在Presenter与View解绑时unSubscribe
     */

    protected void addSubscription(Subscription s, String key){
        if (mSubMap == null || mSubParent == null){
            return;
        }
        Subscription subscription = mSubMap.get(key);
        if (subscription != null){
            mSubParent.remove(subscription);
        }
        mSubMap.put(key,s);
        mSubParent.add(s);
    }

}
