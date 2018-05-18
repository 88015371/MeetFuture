package com.zjman.meetfuture.net;

import com.zjman.meetfuture.data.common.ApiResponse;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by wukewei on 16/5/26.
 */
public class SchedulersCompat {


    private final static Observable.Transformer ioTransformer = new Observable.Transformer() {
        @Override
        public Object call(Object o) {
            return ((Observable)o).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());//.onErrorResumeNext(new HttpResponseFunc<>())
        }
    };

    public static <T> Observable.Transformer<T, ApiResponse<T>> applyIoSchedulers() {
        return (Observable.Transformer<T, ApiResponse<T>>) ioTransformer;
    }
}
