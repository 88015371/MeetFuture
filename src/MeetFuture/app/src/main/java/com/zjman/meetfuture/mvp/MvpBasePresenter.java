package com.zjman.meetfuture.mvp;

import android.support.annotation.NonNull;
import android.support.annotation.UiThread;

import java.lang.ref.WeakReference;

/**
 * Created by hank on 2017/6/8.
 */

public class MvpBasePresenter<V extends MvpView> implements Presenter<V> {

    private WeakReference<V> viewRef; //弱引用

    /**
     * Presenter与View建立连接
     */
    @UiThread
    @Override
    public void attachView(V view) {
        viewRef = new WeakReference<>(view);
    }

   @UiThread
   @NonNull
   public V getView(){

       return  viewRef == null ? null : viewRef.get();
   }


    /**
     * 每次调用业务请求的时候 即：getView().showXxx();时
     * 请先调用方法检查是否与View建立连接，没有则会空指针异常
     */
    @UiThread
    public boolean isViewBind() {
        return viewRef != null && viewRef.get() != null;
    }

    /**
     * Presenter与View连接断开
     */
    @Override
    public void detachView() {

        if (viewRef != null){
            viewRef.clear();
            viewRef = null;
        }

    }
}
