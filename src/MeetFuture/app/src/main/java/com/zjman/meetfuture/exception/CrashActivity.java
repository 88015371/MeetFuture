package com.zjman.meetfuture.exception;

import android.app.Dialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.annotation.NonNull;


import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.GravityEnum;
import com.afollestad.materialdialogs.MaterialDialog;
import com.afollestad.materialdialogs.Theme;
import com.zchu.log.Logger;
import com.zjman.meetfuture.AppManager;
import com.zjman.meetfuture.BuildConfig;
import com.zjman.meetfuture.R;
import com.zjman.meetfuture.base.BaseActivity;
import com.zjman.meetfuture.pref.AppConfig;
import com.zjman.meetfuture.util.DateUtil;
import com.zjman.meetfuture.util.FileUtil;
import com.zjman.meetfuture.util.ToastUtil;

import java.io.File;

/**
 * Created by zjman on 17-1-13.
 */

public class CrashActivity extends BaseActivity {

    private static final String EXTRA_MESSAGE = "extra_message";

    private static final String EXTRA_ERROR_INFO = "extra_error_info";

    public static Intent newIntent(Context context, String message, String errorInfo) {
        Intent intent = new Intent(context, CrashActivity.class);
        intent.putExtra(EXTRA_MESSAGE, message);
        intent.putExtra(EXTRA_ERROR_INFO, errorInfo);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }


    @Override
    protected void onWindowFocusFirstObtain() {
        super.onWindowFocusFirstObtain();
        if(BuildConfig.DEBUG) {
            getCrashDialog(getIntent().getStringExtra(EXTRA_MESSAGE), getIntent().getStringExtra(EXTRA_ERROR_INFO)).show();
        }else {
            finish();
        }
    }

    public Dialog getCrashDialog(String message, final String errorInfo) {
        Logger.e(errorInfo);

        return new MaterialDialog.Builder(this)
                .title("程序遇到错误，已崩溃")
                .content("错误信息：" + message)
                .positiveText("保存日志")
                .negativeText("关闭程序")
                .neutralText("复制信息")
                .titleGravity(GravityEnum.CENTER)
                .titleColorRes(R.color.md_red_400)
                .contentColorRes(android.R.color.white)
                .backgroundColorRes(R.color.material_blue_grey_800)
                .cancelable(false)
                .dividerColorRes(R.color.colorPrimary)
                .positiveColor(Color.WHITE)
                .negativeColorAttr(android.R.attr.textColorSecondaryInverse)
                .theme(Theme.DARK)
                .onNeutral(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        try {
                            ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                            // 将文本内容放到系统剪贴板里。
                            cm.setText(errorInfo);
                            ToastUtil.showToast("复制成功，快去爆程序员菊花吧");
                            finish();
                        } catch (Exception e) {
                            Logger.e(e);
                        }
                    }
                })
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                        try {
                            AppManager.INSTANCE.AppExit();
                        } catch (Exception e) {
                            Logger.e(e);
                        }

                    }
                })
                .onPositive(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        try {
                            File file = FileUtil.writeLog(AppConfig.LOG_FILE_PATH, DateUtil.getCurrentTimeStr() + ".txt", errorInfo);
                            if (file == null) {
                                ToastUtil.showToast("保存失败");
                            } else {
                                ToastUtil.showToast("日志已保存至" + file.getPath());
                            }

                            finish();
                        } catch (Exception e) {
                            Logger.e(e);
                        }

                    }
                })
                .build();
    }
}
