package com.zjman.meetfuture.dagger.component

import com.zjman.meetfuture.AppContext
import com.zjman.meetfuture.dagger.ContextLife
import com.zjman.meetfuture.dagger.module.AppModule
import dagger.Component

import retrofit2.Retrofit
import javax.inject.Singleton

/**
 * Created by wukewei on 16/7/19.
 */
@Singleton
@Component(modules = arrayOf(AppModule::class))
interface AppComponent {

    @get:ContextLife("Application")

    val context: AppContext

    val retrofit: Retrofit


    fun inject(myService: String)
}
