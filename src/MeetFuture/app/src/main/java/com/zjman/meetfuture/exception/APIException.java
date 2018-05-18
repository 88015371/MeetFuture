package com.zjman.meetfuture.exception;


/**
 * Created by zjman on 16-12-29.
 * Server API 响应结果含error_code且error_code值不为0时，抛出此异常
 */

public class APIException extends RuntimeException {


    //----用户相关----
    public static final int CODE_BINDED = 1001;
    public static final int CODE_ERR = 10009;
    public static final int INCORRECT_PASSWORD = 20012;//密码不正确
    public static final int OLD_INCORRECT_PASSWORD = 20016;//密码不正确
    public static final int MOBILE_REGISTERD = 10001;//该手机号已被注册
    public static final int GET_USER_BY_MOBILE_ERR = 20039;//该手机号未被注册
    public static final int NOT_LOGIN = -99;
    public static final int LOGIN_TOKEN_ERR = -100;//	网络错误

    private int code;//错误码


    public APIException(int code , String defaultMessage){
        this(getErrorMessage(code, defaultMessage));
        this.code = code;
    }
    public APIException(int code) {
        this(getErrorMessage(code, null));
        this.code = code;
    }

    public APIException(String message) {
        super(message);
    }


    /**
     * 由于服务器传递过来的错误信息直接给用户看的话，用户未必能够理解
     * 需要根据错误码对错误信息进行一个转换，在显示给用户
     */
    private static String getErrorMessage(int code, String defaultMessage) {
        String message = "";
        switch (code) {
            case GET_USER_BY_MOBILE_ERR:
                message = "该手机号未被注册";
                break;
            case INCORRECT_PASSWORD:
                message = "密码不正确";
                break;
            case OLD_INCORRECT_PASSWORD:
                message = "原密码不正确";
                break;
            case CODE_ERR:
                message = "手机验证码不正确";
                break;
            case MOBILE_REGISTERD:
                message = "该手机号已被注册";
                break;
            case -9527:
                message = "网络出错了!";
                break;
            default:
                if (defaultMessage != null) {
                    message = defaultMessage;
                } else {
                    message = "网络出了点问题，请稍后再试 ～";
                }

        }
        return message;
    }

    public int getCode() {
        return code;
    }
}
