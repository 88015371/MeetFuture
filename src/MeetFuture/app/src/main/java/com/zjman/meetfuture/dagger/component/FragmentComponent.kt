package com.zjman.meetfuture.dagger.component

import android.app.Activity
import com.zjman.meetfuture.dagger.PerFragment
import com.zjman.meetfuture.dagger.module.FragmentModule
import dagger.Component

import retrofit2.Retrofit

/**
 * Created by wukewei on 16/7/19.
 */
@PerFragment
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(FragmentModule::class))
interface FragmentComponent {

    val retrofit: Retrofit

    val activity: Activity

    //**********************************************************************************************
    // Fragment
    //**********************************************************************************************
   // fun inject(homeFragment: HomeFragment)//首页


}
