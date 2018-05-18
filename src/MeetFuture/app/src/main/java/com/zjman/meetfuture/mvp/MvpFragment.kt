package com.zjman.meetfuture.mvp

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.zjman.meetfuture.AppContext
import com.zjman.meetfuture.base.BaseFragment
import com.zjman.meetfuture.dagger.component.AppComponent
import com.zjman.meetfuture.dagger.module.FragmentModule
import javax.inject.Inject

/**
 * Created by zjman on 2018/5/18.
 */

abstract class MvpFragment<in V :MvpView,P : Presenter<V>> : BaseFragment(),MvpView{
    @Inject
    lateinit var mPresenter:P
  //  protected lateinit var rootView: View
    protected lateinit var mContext: Activity
    private var hasStarted = false

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
      //  rootView = inflater!!.inflate(getLayoutId(),container, false)

        setupActivityComponent((activity.application as AppContext).appComponent, FragmentModule(this))
        mContext = activity!!
        mPresenter.attachView(this as V)
        return inflater!!.inflate(getLayoutId(), container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initEventAndData()
    }

    override fun onDestroy() {
        if (mPresenter != null) mPresenter.detachView()
        super.onDestroy()
    }

    /**
     * 依赖注入的入口
     * @param appComponent appComponent
     */


    protected abstract fun getLayoutId(): Int

    protected abstract fun setupActivityComponent(appComponent: AppComponent, fragmentModule: FragmentModule)

    protected abstract fun initEventAndData()

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            hasStarted = true
            showFragment()

        } else {
            if (hasStarted) {
                hasStarted = false
                hideFragment()

            }
        }
    }

    open fun showFragment(){}

    open fun hideFragment(){}



}
