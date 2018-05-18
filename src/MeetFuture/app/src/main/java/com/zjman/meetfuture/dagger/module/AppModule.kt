/*
 *
 *  *
 *  *  * Copyright (C) 2014 Antonio Leiva Gordillo.
 *  *  *
 *  *  * Licensed under the Apache License, Version 2.0 (the "License");
 *  *  * you may not use this file except in compliance with the License.
 *  *  * You may obtain a copy of the License at
 *  *  *
 *  *  *      http://www.apache.org/licenses/LICENSE-2.0
 *  *  *
 *  *  * Unless required by applicable law or agreed to in writing, software
 *  *  * distributed under the License is distributed on an "AS IS" BASIS,
 *  *  * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  *  * See the License for the specific language governing permissions and
 *  *  * limitations under the License.
 *  *
 *
 */

package com.zjman.meetfuture.dagger.module

import android.util.Log
import com.zjman.meetfuture.AppContext
import com.zjman.meetfuture.dagger.ContextLife
import com.zjman.meetfuture.net.network.LoggingInterceptor
import com.zjman.meetfuture.net.network.ResponseConverterFactory
import com.zjman.meetfuture.net.network.SSLSocketFactoryCompat
import com.zjman.meetfuture.pref.C
import com.zjman.meetfuture.pref.Constants
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import java.security.cert.CertificateException
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import javax.net.ssl.X509TrustManager


@Module
class AppModule(private val app: AppContext) {

    @Provides
    @Singleton
    @ContextLife("Application")
    fun provideApp(): AppContext {
        return app
    }

    @Provides
    @Singleton
    internal fun provideOkHttpClient(): OkHttpClient {
        //頭部參數拦截
        var paramsInterceptor = Interceptor { chain ->
            var request = chain.request()
            val originalHttpUrl = request.url()
            if(AppContext.context().mAccessToken!!.isEmpty()){
                val updateHttpUrl = originalHttpUrl.newBuilder()
//                        .addQueryParameter("access_token", SixRoomApplication.instance.mAccessToken)
                        .build()
                val requestBuilder = request.newBuilder().url(updateHttpUrl)

                //重新指定request
                request = requestBuilder.build()
                chain.proceed(request)
            }else{
                val updateHttpUrl = originalHttpUrl.newBuilder()
                        .addQueryParameter("access_token", AppContext.context().mAccessToken)
                        .build()
                val requestBuilder = request.newBuilder().url(updateHttpUrl)

                //重新指定request
                request = requestBuilder.build()
                chain.proceed(request)
            }
//            val updateHttpUrl = originalHttpUrl.newBuilder()
//                    .addQueryParameter("access_token", SixRoomApplication.instance.mAccessToken)
//                    .build()
//            val requestBuilder = request.newBuilder().url(updateHttpUrl)
//
//            //重新指定request
//            request = requestBuilder.build()
//            chain.proceed(request)
        }

        //拦截
        val loggingInterceptor = HttpLoggingInterceptor(HttpLoggingInterceptor.Logger { message -> Log.e("Retrofit", message) })

        //LoggingInterceptor
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

//        val originalResponse = chain.proceed(request)
//
//        //读取retrofit接口上的@Headers里的配置，并设置到response header里
//        val cacheControl = request.cacheControl().toString()

        val interceptor = Interceptor { chain ->
            val request = chain.request()
                    .newBuilder()
                    .build()
            chain.proceed(request)
        }

        // 自定义一个信任所有证书的TrustManager，添加SSLSocketFactory的时候要用到
        val trustAllCert = object: X509TrustManager {
            @Throws(CertificateException::class)
            public override fun checkClientTrusted(chain:Array<java.security.cert.X509Certificate>, authType:String) {}

            @Throws(CertificateException::class)
            public override fun checkServerTrusted(chain:Array<java.security.cert.X509Certificate>, authType:String) {}

            public override fun getAcceptedIssuers():Array<java.security.cert.X509Certificate> {
                return arrayOf<java.security.cert.X509Certificate>()
            }
        }
        val sslSocketFactory = SSLSocketFactoryCompat(trustAllCert)

        val okHttpClient = OkHttpClient.Builder()
                // .sslSocketFactory(sslSocketFactory, trustAllCert)  // https 请求需要加这个
                .readTimeout(Constants.HTTP_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                .connectTimeout(Constants.HTTP_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                .addInterceptor(paramsInterceptor)
                .addInterceptor(LoggingInterceptor())
                .addInterceptor(interceptor)
                .build()
        return okHttpClient
    }

    @Provides
    @Singleton
    internal fun provideHotApi(okHttpClient: OkHttpClient): Retrofit {
        val retrofit1 = Retrofit.Builder()
                .baseUrl(C.URL_SERVER_API)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(ResponseConverterFactory.create())
                .build()

        return retrofit1
    }
}
