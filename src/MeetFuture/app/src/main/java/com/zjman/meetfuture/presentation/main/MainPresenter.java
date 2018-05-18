package com.zjman.meetfuture.presentation.main;


import android.app.Activity;

import com.zjman.meetfuture.mvp.MvpRxPresenter;

import javax.inject.Inject;

import retrofit2.Retrofit;

/**
 * Created by zjman on 2018/5/18.
 */

public class MainPresenter extends MvpRxPresenter<MainContract.mView> implements MainContract.mPresenter {

    @Inject
    public MainPresenter(Retrofit retrofit, Activity activity) {
        super(retrofit, activity);
    }
}
