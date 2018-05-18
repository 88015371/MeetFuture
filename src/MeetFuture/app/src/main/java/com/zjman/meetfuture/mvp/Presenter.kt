package com.zjman.meetfuture.mvp

interface Presenter<in V : MvpView> {

    fun attachView(view: V)

    fun detachView()

}
