package com.zjman.meetfuture.dagger.component

import android.app.Activity
import dagger.Component
import com.zjman.meetfuture.dagger.PerActivity
import com.zjman.meetfuture.dagger.module.ActivityModule
import com.zjman.meetfuture.presentation.main.MainActivity


import retrofit2.Retrofit

/**
 * Created by wali on 16/7/19.
 */
@PerActivity
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    val retrofit: Retrofit

    val activity: Activity

   fun inject(mainActivity: MainActivity)//主页面


}
