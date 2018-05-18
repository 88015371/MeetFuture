package com.zjman.meetfuture.data.net;
import android.os.Build;
import android.support.annotation.RequiresApi;

import com.zjman.meetfuture.data.net.converter.GsonConverterFactory;
import com.zjman.meetfuture.data.net.interceptor.LoggingInterceptor;
import com.zjman.meetfuture.util.GsonProvide;

import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

/**
 * Created by zchu on 16-11-16.
 */
public class UploadClient {


    private static volatile ServerAPI sServerAPI;//单例模式

    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    private static OkHttpClient getOkHttpClient() {

        OkHttpClient.Builder clientBuilder = getClientBuilder();


        //配置日志拦截器
        clientBuilder
                .addInterceptor(new LoggingInterceptor());
                //.addInterceptor(new JwtInterceptor());
        // 自定义一个信任所有证书的TrustManager，添加SSLSocketFactory的时候要用到
        final X509TrustManager trustAllCert =
                new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                    }

                    @Override
                    public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                    }

                    @Override
                    public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                        return new java.security.cert.X509Certificate[]{};
                    }
                };
        SSLSocketFactory sslSocketFactory = new SSLSocketFactoryCompat(trustAllCert);
        clientBuilder.sslSocketFactory(sslSocketFactory, trustAllCert);

        return clientBuilder.build();
    }


    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    public static ServerAPI getServerAPI() {
        if (sServerAPI == null) {
            synchronized (UploadClient.class) {
                if (sServerAPI == null) {
                    sServerAPI = getRetrofitBuilder(ServerAPI.UPLOAD_URL, getOkHttpClient()).build().create(ServerAPI.class);
                }
            }
        }
        return sServerAPI;

    }

    /**
     * @param url    域名
     * @param client okhttp请求客户端
     * @return retrofit的构建器
     */
    private static Retrofit.Builder getRetrofitBuilder(String url, OkHttpClient client) {
        return new Retrofit.Builder()
                //接口基地址
                .baseUrl(url)
                //String转换器
                .addConverterFactory(ScalarsConverterFactory.create())
                //Json转换器
                .addConverterFactory(GsonConverterFactory.create(GsonProvide.getGson()))
                //Rxjava转换器：将结果转成已Observable的形式返回
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client);
    }


    @RequiresApi(api = Build.VERSION_CODES.GINGERBREAD)
    private static OkHttpClient.Builder getClientBuilder() {
        return new OkHttpClient.Builder()
                .connectTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES)
                .retryOnConnectionFailure(true)//重试
         .writeTimeout(10, TimeUnit.MINUTES);
    }
}
