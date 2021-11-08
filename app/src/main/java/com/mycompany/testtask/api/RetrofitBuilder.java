package com.mycompany.testtask.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.squareup.okhttp.ResponseBody;

import java.util.concurrent.TimeUnit;


import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";
    private static Retrofit retrofit;
    private static RetrofitInterface retrofitInterface;

    public RetrofitBuilder() {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setReadTimeout(60 * 1000, TimeUnit.MILLISECONDS);
        Gson gson = new GsonBuilder()
                .setDateFormat(DATE_FORMAT)
                .create();
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        getApi();
    }

    public static RetrofitInterface getApi() {
        if (retrofitInterface == null) {
            retrofitInterface = retrofit.create(RetrofitInterface.class);
        }
        return retrofitInterface;

    }
}
