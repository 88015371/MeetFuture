package com.zjman.meetfuture.presentation.main;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.zjman.meetfuture.R;
import com.zjman.meetfuture.dagger.component.AppComponent;
import com.zjman.meetfuture.dagger.component.DaggerActivityComponent;
import com.zjman.meetfuture.dagger.module.ActivityModule;
import com.zjman.meetfuture.mvp.MvpActivity;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Created by zjman on 2018/5/18.
 */

public class MainActivity extends MvpActivity<MainContract.mView,MainPresenter> implements MainContract.mView  {



    @Override
    protected void getLayout() {

        setContentView(R.layout.activity_main);

    }

    @Override
    protected void setupActivityComponent(@NotNull AppComponent appComponent, @NotNull ActivityModule activityModule) {

        DaggerActivityComponent.builder()
                .appComponent(appComponent)
                .activityModule(activityModule)
                .build()
                .inject(this);

    }

    @Override
    protected void init(@Nullable Bundle savedInstanceState) {

    }


}
