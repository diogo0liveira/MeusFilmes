package com.diogo.oliveira.mymovies.util.network.retrofit;


import com.diogo.oliveira.mymovies.util.Extras;
import com.diogo.oliveira.mymovies.util.json.GsonHelper;
import com.diogo.oliveira.mymovies.util.network.retrofit.authority.TokenRequestInterceptor;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created on 14/04/2016 16:06.
 *
 * @author Diogo Oliveira.
 */
public class ServiceGenerator
{
    //@formatter:off
    private final static HttpLoggingInterceptor LOGGING = new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);
    private final static OkHttpClient.Builder INTERCEPTOR = new OkHttpClient.Builder().addInterceptor(new TokenRequestInterceptor()).addNetworkInterceptor(LOGGING);

    private final static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(Extras.URL.API)
            .addConverterFactory(GsonConverterFactory.create(GsonHelper.build()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create());
    //@formatter:on

    public static <S> S  get(Class<S> serviceClass)
    {
        return builder.client(INTERCEPTOR.build()).build().create(serviceClass);
    }
}
