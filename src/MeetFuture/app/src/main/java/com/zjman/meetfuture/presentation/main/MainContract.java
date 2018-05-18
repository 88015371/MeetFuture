package com.zjman.meetfuture.presentation.main;

import com.zjman.meetfuture.mvp.MvpView;
import com.zjman.meetfuture.mvp.Presenter;

/**
 * Created by zjman on 2018/5/18.
 */

public interface MainContract {

    interface mView extends MvpView {}


    interface mPresenter extends Presenter<mView> {}
}
