package com.example.myapplication.services;

import android.os.Build;

import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceBuilder {
    private static final String URL = "https://gadsapi.herokuapp.com/api/";

    //create logger
    private static HttpLoggingInterceptor logger =
            new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY);

    //create okhttp client
    private static OkHttpClient.Builder okHttp =
            new OkHttpClient.Builder()
                    .addInterceptor(logger);
    private static Retrofit.Builder builder = new Retrofit.Builder().baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttp.build());
    private static Retrofit retrofit = builder.build();

    public static <S> S builderService(Class<S> serviceType) {
        return retrofit.create(serviceType);
    }
}
