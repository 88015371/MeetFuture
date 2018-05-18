package com.zjman.meetfuture.dagger.module

import android.app.Activity
import android.support.v4.app.Fragment
import com.zjman.meetfuture.dagger.PerFragment
import dagger.Module
import dagger.Provides

/**
 * Created by wukewei on 16/7/19.
 */
@Module
class FragmentModule(private val fragment: Fragment) {
    @Provides
    @PerFragment
    fun provideActivity(): Activity {
        return fragment.activity
    }
    @PerFragment
    @Provides
    fun provieFragment(): Fragment {
        return fragment
    }
}
