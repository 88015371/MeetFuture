package com.zjman.meetfuture.dagger.module

import android.app.Activity
import dagger.Module
import dagger.Provides
import com.zjman.meetfuture.dagger.PerActivity

@Module
class ActivityModule(private val mActivity: Activity) {

    @Provides
    @PerActivity
    fun provideActivity(): Activity {
        return mActivity
    }
}
