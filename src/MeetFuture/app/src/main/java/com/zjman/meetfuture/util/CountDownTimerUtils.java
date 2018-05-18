package com.zjman.meetfuture.util;

import android.os.CountDownTimer;
import android.widget.TextView;


/**
 * Created by zjman on 2017/6/17.
 */

public class CountDownTimerUtils extends CountDownTimer {

    private TextView tx_verify;

    public CountDownTimerUtils(TextView textView, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.tx_verify = textView;
    }

    @Override
    public void onTick(long l) {

        tx_verify.setClickable(false);
        tx_verify.setText("("+l/1000+"s"+")"+"重新发送");
        getTime(l);
    }

    @Override
    public void onFinish() {

        tx_verify.setText("发送验证码");
        tx_verify.setClickable(true);

    }

    public static long getTime(long time){
        return time;
    }

}
