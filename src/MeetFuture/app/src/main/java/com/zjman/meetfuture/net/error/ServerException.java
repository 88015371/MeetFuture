package com.zjman.meetfuture.net.error;

import java.io.IOException;

/**
 * Created by wukewei on 16/5/30.
 */
public class ServerException extends IOException {


    private int mCode;
    private String message;
    public ServerException(int code,String message) {
        this.mCode = code;
        this.message = message;
    }

    /***
     * 这里可以获取code来判断具体是什么错误
     * @param
     * @param
     * @return
     */
    public int getCode() {
        return mCode;
    }


    @Override
    public String getMessage() {
        return message;
    }
}
