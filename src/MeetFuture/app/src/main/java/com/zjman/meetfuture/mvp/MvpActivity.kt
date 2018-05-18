package com.zjman.meetfuture.mvp

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.view.menu.MenuView
import android.view.View
import android.view.WindowManager
import com.zjman.meetfuture.AppContext
import com.zjman.meetfuture.R

import com.zjman.meetfuture.base.BaseActivity
import com.zjman.meetfuture.dagger.component.AppComponent
import com.zjman.meetfuture.dagger.module.ActivityModule
import io.realm.Realm

import javax.inject.Inject

/**
 * Created by zjman on 2018/5/18.
 */

abstract class MvpActivity<in V :MvpView,P : Presenter<V>> : BaseActivity(), MvpView {
    @Inject
    lateinit var mPresenter:P
    lateinit var realm: Realm
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //设置状态栏全透明
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
        }
        Realm.init(this)
        realm = Realm.getDefaultInstance()
        getLayout()
        setupActivityComponent(AppContext.context().appComponent, ActivityModule(this))
         mPresenter.attachView(this as V)
       // back()
        init(savedInstanceState)
    }

    //统一设置返回
 /*   open fun back(){
        mActivity.findViewById(R.id.ivBack)?.setOnClickListener {
            realm.close()
            //mActivity.onBackPressed()
            mAppManager.finishActivity()
        }
    }*/

    protected abstract fun getLayout()


    override fun onDestroy() {
        if (mPresenter != null) mPresenter.detachView()
        //mAppManager.removeActivity(mActivity)//移除activity
        if (realm !=null){
            realm.close()//关闭数据库
        }

        super.onDestroy()
    }
    /**
     * 依赖注入的入口
     * @param appComponent appComponent
     */
    protected abstract fun setupActivityComponent(appComponent: AppComponent, activityModule: ActivityModule)

    protected abstract fun init(savedInstanceState: Bundle?)
}
