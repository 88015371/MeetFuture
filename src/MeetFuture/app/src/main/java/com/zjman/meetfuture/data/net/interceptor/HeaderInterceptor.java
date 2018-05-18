package com.zjman.meetfuture.data.net.interceptor;


import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * @Description 为请求添加header的过虑器
 * @Author MoseLin
 * @Date 2016/7/4.
 */
/*public class HeaderInterceptor implements Interceptor
{
    public HeaderInterceptor(){

    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request.Builder builder = chain.request().newBuilder();
        boolean isLogin = UserManager.getInstance().isLogin();
        if(isLogin){
            builder.addHeader("xxg-authorized", UserManager.getInstance().getUser().getXxg_token());
        }

        return chain.proceed(builder.build());
    }


}*/
