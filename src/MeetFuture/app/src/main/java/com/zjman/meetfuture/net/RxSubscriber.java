package com.zjman.meetfuture.net;

import android.content.Context;

import com.zjman.meetfuture.net.error.ErrorHanding;

import rx.Subscriber;

public abstract class RxSubscriber<T> extends Subscriber<T> {

    private Context context;
    public RxSubscriber(Context context) {
        this.context = context;
    }

    @Override
    public void onCompleted() {
        _onCompleted();
    }

    @Override
    public void onNext(T t) {
        _onNext(t);
    }

    @Override
    public void onError(Throwable e) {
        _onError(ErrorHanding.handleError(e,context));
    }

    public abstract void _onNext(T t);
    public abstract void _onError(String msg);
    public abstract void _onCompleted();
}
