package com.diogo.oliveira.mymovies.util.network.retrofit.authority;

import android.support.annotation.NonNull;

import com.diogo.oliveira.mymovies.util.Extras;
import com.diogo.oliveira.mymovies.util.json.JsonKey;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created in 13/01/18 11:39.
 *
 * @author Diogo Oliveira.
 */
public class TokenRequestInterceptor implements Interceptor
{
    @Override
    public Response intercept(@NonNull Chain chain) throws IOException
    {
        if(!chain.request().url().encodedPath().contains(JsonKey.API_KEY))
        {
            Request request = chain.request();

            HttpUrl url = request.url().newBuilder().addQueryParameter(JsonKey.API_KEY, Extras.URL.KEY).build();

            Request.Builder requestBuilder = request.newBuilder().url(url);
            return chain.proceed(requestBuilder.build());
        }

        return chain.proceed(chain.request());
    }
}
