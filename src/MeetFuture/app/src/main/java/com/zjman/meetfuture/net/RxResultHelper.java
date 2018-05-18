package com.zjman.meetfuture.net;


import com.zjman.meetfuture.data.common.ApiResponse;
import com.zjman.meetfuture.net.error.NetworkConnectionException;
import com.zjman.meetfuture.net.error.ServerException;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subjects.PublishSubject;

/**
 * Created by wzc on 16/5/26.
 */
public class RxResultHelper {

    public static <T> Observable.Transformer<ApiResponse<T>, T> handleResult(final ActivityLifeCycleEvent event, final PublishSubject<ActivityLifeCycleEvent> lifecycleSubject) {
        return new Observable.Transformer<ApiResponse<T>, T>() {
            @Override
            public Observable<T> call(Observable<ApiResponse<T>> apiResponseObservable) {
                Observable<ActivityLifeCycleEvent> compareLifecycleObservable =
                        lifecycleSubject.takeFirst(new Func1<ActivityLifeCycleEvent, Boolean>() {//设置销毁位置
                            @Override
                            public Boolean call(ActivityLifeCycleEvent activityLifeCycleEvent) {
                                return activityLifeCycleEvent.equals(event);
                            }
                        });
                return apiResponseObservable.flatMap(
                        new Func1<ApiResponse<T>, Observable<T>>() {
                            @Override
                            public Observable<T> call(ApiResponse<T> tApiResponse) {
                                if (tApiResponse == null) {
                                    return Observable.error(new NetworkConnectionException());
                                } else if (tApiResponse.isSuccess()) {
                                    return createData(tApiResponse.getData());
                                } else {
                                    return Observable.error(new ServerException(tApiResponse.getCode(),"请求失败"));
                                }
                            }
                        }
                ).takeUntil(compareLifecycleObservable).subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io())
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }


    public static <T> Observable.Transformer<ApiResponse<T>, T> handleResultNew(final ActivityLifeCycleEvent event, final PublishSubject<ActivityLifeCycleEvent> lifecycleSubject) {
        return new Observable.Transformer<ApiResponse<T>, T>() {
            @Override
            public Observable<T> call(Observable<ApiResponse<T>> apiResponseObservable) {
                Observable<ActivityLifeCycleEvent> compareLifecycleObservable =
                        lifecycleSubject.takeFirst(new Func1<ActivityLifeCycleEvent, Boolean>() {//设置销毁位置
                            @Override
                            public Boolean call(ActivityLifeCycleEvent activityLifeCycleEvent) {
                                return activityLifeCycleEvent.equals(event);
                            }
                        });
                return apiResponseObservable.map(new Func1<ApiResponse<T>, ApiResponse<T>>() {
                    @Override
                    public ApiResponse<T>call(ApiResponse<T> tApiResponse) {
                        /**
                         * API不是Success將Data清空，避免Gson Cast Error
                         */
                        if(!tApiResponse.isSuccess()){
                            tApiResponse.setData(null);
                        }
                        return tApiResponse;
                    }
                }).flatMap(
                        new Func1<ApiResponse<T>, Observable<T>>() {
                            @Override
                            public Observable<T> call(ApiResponse<T> tApiResponse) {
                                if (tApiResponse == null) {
                                    return Observable.error(new NetworkConnectionException());
                                } else {
                                    return createData(tApiResponse.getData());
                                }
//                                else {
//                                    return Observable.error(new ServerException(tApiResponse.getCode()));
//                                }
                            }
                        }
                ).takeUntil(compareLifecycleObservable).subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io())
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    //无生命周期的rxjava
    public static <T> Observable.Transformer<ApiResponse<T>, T> handleNoLifeResult() {
        return new Observable.Transformer<ApiResponse<T>, T>() {
            @Override
            public Observable<T> call(Observable<ApiResponse<T>> apiResponseObservable) {
                return apiResponseObservable.flatMap(
                        new Func1<ApiResponse<T>, Observable<T>>() {
                            @Override
                            public Observable<T> call(ApiResponse<T> tApiResponse) {
                                if (tApiResponse == null) {
                                    return Observable.error(new NetworkConnectionException());
                                } else if (tApiResponse.isSuccess()) {
                                    return createData(tApiResponse.getData());
                                } else {
                                    return Observable.error(new ServerException(tApiResponse.getCode(),"请求失败"));
                                }
                            }
                        }
                ).subscribeOn(Schedulers.io()).unsubscribeOn(Schedulers.io())
                        .subscribeOn(AndroidSchedulers.mainThread())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    //用于java的rxjava
    public static <T> Observable.Transformer<ApiResponse<T>, T> handleResult() {
        return new Observable.Transformer<ApiResponse<T>, T>() {
            @Override
            public Observable<T> call(Observable<ApiResponse<T>> apiResponseObservable) {
                return apiResponseObservable.flatMap(
                        new Func1<ApiResponse<T>, Observable<T>>() {
                            @Override
                            public Observable<T> call(ApiResponse<T> tApiResponse) {
                                if (tApiResponse == null) {
                                    return Observable.error(new NetworkConnectionException());
                                } else if (tApiResponse.isSuccess()) {
                                    return createData(tApiResponse.getData());
                                } else {
                                    return Observable.error(new ServerException(tApiResponse.getCode(),"请求失败"));
                                }
                            }
                        }
                );
            }
        };
    }

    public static <T> Observable<T> createData(final T t) {
        return Observable.create(new Observable.OnSubscribe<T>() {
            @Override
            public void call(Subscriber<? super T> subscriber) {
                try {
                    subscriber.onNext(t);
                    subscriber.onCompleted();
                } catch (Exception e) {
                    subscriber.onError(e);
                }
            }
        });
    }
}

