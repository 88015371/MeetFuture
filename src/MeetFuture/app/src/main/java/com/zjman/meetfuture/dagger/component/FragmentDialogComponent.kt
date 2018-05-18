package com.zjman.meetfuture.dagger.component

import android.app.Activity
import com.zjman.meetfuture.dagger.PerFragmentDialog
import com.zjman.meetfuture.dagger.module.FragmentDialogModule
import dagger.Component
import retrofit2.Retrofit

@PerFragmentDialog
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(FragmentDialogModule::class))
interface FragmentDialogComponent {

    val retrofit: Retrofit

    val activity: Activity

  //  fun inject(aMusicDetailMenuDialog: MusicDetailMenuDialog)//音樂選單


}

