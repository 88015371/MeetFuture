package com.zjman.meetfuture.net;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;

import com.zjman.meetfuture.R;
import com.zjman.meetfuture.net.error.ErrorHanding;

import rx.Subscriber;

/**
 * Created by Macx on 2017/9/19.
 */

public abstract class ProgressSubscriber<T> extends Subscriber<T> {
    private ProgressDialog progressDialog;
    private Context context;
    public ProgressSubscriber(Activity context) {
        progressDialog = new ProgressDialog(context);
        this.context = context;
//        progressDialog.setTitle(context.getString(R.string.quest));
        progressDialog.setMessage(context.getString(R.string.wait));
        progressDialog.setCanceledOnTouchOutside(false);
    }

    @Override
    public void onStart() {
        showProgressDialog();
        super.onStart();
    }

    @Override
    public void onCompleted() {
        dismissDialog();
        _onCompleted();
    }

    public void showProgressDialog(){
        if (progressDialog!=null){
            progressDialog.show();
        }
    }

    @Override
    public void onError(Throwable e) {
        dismissDialog();
        _onError(ErrorHanding.handleError(e,context));

//        SharedHelper.put(SixRoomApplication.instance.TOKEN_KEY, t.access_token)
//        SharedHelper.put(SixRoomApplication.instance.USER_ID, t.id)
    }

    @Override
    public void onNext(T t) {
        _onNext(t);
    }


    /**
     * 隐藏dialog
     */
    private void dismissDialog(){
        if (progressDialog!=null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    protected abstract void _onCompleted();
    protected abstract void _onNext(T t);
    protected abstract void _onError(String message);
}
