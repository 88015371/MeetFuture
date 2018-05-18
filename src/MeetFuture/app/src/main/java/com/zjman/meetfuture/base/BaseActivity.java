package com.zjman.meetfuture.base;

import android.os.Bundle;
import android.support.annotation.Nullable;


/**
 * 作者: zjman on 2016/7/13.
 * 职责：为所有的activity封装功能
 */
public class BaseActivity extends BaseSuperActivity{
    /**
     * 窗体第一次获取焦点
     */
    private boolean isFirstFocus = true;

    //protected SystemBarTintManager systemBarTintManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置沉淀式状态栏
      //  systemBarTintManager = new SystemBarTintManager(this);
       // systemBarTintManager.setStatusBarTintEnabled(true);
       // systemBarTintManager.setStatusBarTintColor(ContextCompat.getColor(this, R.color.colorPrimary));
      //  PushAgent.getInstance(this).onAppStart();

    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && isFirstFocus) {
            onWindowFocusFirstObtain();
            isFirstFocus=false;
        }
    }

    /**
     * 当窗体第一次获取到焦点会回调该方法
     */
    protected void onWindowFocusFirstObtain() {}


}
