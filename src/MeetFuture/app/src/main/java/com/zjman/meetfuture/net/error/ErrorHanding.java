package com.zjman.meetfuture.net.error;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.zjman.meetfuture.AppContext;
import com.zjman.meetfuture.pref.Ext;
import com.zjman.meetfuture.util.SharedHelper;
import com.zjman.meetfuture.util.ToastUtil;

import java.net.ConnectException;
import java.net.SocketTimeoutException;



/**
 * Created by wukewei on 16/6/1.
 */
public class ErrorHanding {

    public ErrorHanding() {}

    public static String handleError(Throwable e, Context context) {
        Log.e("Handle Error : " , e.getMessage());
        String message;
        if (!Ext.g().isAvailable()) {
            message = "网络中断，请检查您的网络状态";
        } else if (e instanceof SocketTimeoutException) {
            message = "网络中断，请检查您的网络状态";
        } else if (e instanceof ConnectException) {
            message = "网络中断，请检查您的网络状态";
        } else if (e instanceof NetworkConnectionException) {
            message = "网络中断，请检查您的网络状态";
        } else if (e instanceof ServerException) {
            int code  = ((ServerException) e).getCode();
            message = e.getMessage();

            if (code==401){//401和500都跳转
                //清除数据
                AppContext.context().setmUserId("");
                AppContext.context().setmAccessToken("");
                SharedHelper.remove(AppContext.context().getTOKEN_KEY());
                SharedHelper.remove(AppContext.context().getUSER_ID());
              //  Intent intent = new Intent(context, LoginActivity.class);
              //  context.startActivity(intent);
                message = "请登录";
            }

//            else if (code==500){
//                message = "服务器内部出错，请稍后再试";
//            }

            //在这里你可以获取code来判断是什么类型
        } else {
            message = "连接服务器失败,请稍后再试";
        }
        ToastUtil.showToast(message);
        return message;
    }

}
