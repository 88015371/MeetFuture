package com.zjman.meetfuture.data.net.interceptor;


import com.google.api.client.repackaged.org.apache.commons.codec.binary.Base64;
import com.zchu.log.Logger;

import java.io.IOException;
import java.util.HashMap;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


/**
 * Created by jiajie.liao on 2017/8/14.
 */

/*public class JwtInterceptor implements Interceptor {

    private static final String secret = "xxgunicornsaupload";
    private static final String HEADER_OAUTH_TOKEN = "Authorization";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request()
                .newBuilder()
                .addHeader(HEADER_OAUTH_TOKEN, "Bearer " + getJWT())
                .build();

        return chain.proceed(request);
    }

    private String getJWT() {
        String jwt = "";
        HashMap header = new HashMap();
        header.put("typ", "JWT");
        header.put("alg", "HS256");

        try{
            jwt = Jwts.builder()
                    .setHeader(header)
                    .setIssuer(secret)
//                    .setIssuedAt(start)
//                    .setExpiration(new Date(System.currentTimeMillis() + 3600000))
                    .signWith(SignatureAlgorithm.HS256, Base64.encodeBase64String(secret.getBytes("UTF-8")))
                    .compact();
        }catch (Exception e){
            Logger.e(e);
        }
        Logger.e(jwt);
        return jwt;
    }
}*/
