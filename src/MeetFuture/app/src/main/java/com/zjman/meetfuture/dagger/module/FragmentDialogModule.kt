package com.zjman.meetfuture.dagger.module

import android.app.Activity
import android.support.v4.app.DialogFragment
import android.support.v4.app.Fragment
import com.zjman.meetfuture.dagger.PerFragmentDialog
import dagger.Module
import dagger.Provides


/**
 * Created by wukewei on 16/7/19.
 */
@Module
class FragmentDialogModule(private val fragment: DialogFragment) {
    @PerFragmentDialog
    @Provides
    fun provideActivity(): Activity {
        return fragment.activity
    }
    @PerFragmentDialog
    @Provides
    fun provieFragment(): Fragment {
        return fragment
    }
}
