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
    private static Retrofit retrofit;
    private static RetrofitInterface retrofitInterface;

    public RetrofitBuilder() {
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setReadTimeout(60 * 1000, TimeUnit.MILLISECONDS);
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
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
    private static Interceptor provideAuthInterceptor() {
        return chain -> {
            Request request = chain.request();
            Request.Builder builder = request.newBuilder();
            Request authorizedRequest = builder.build();
            Response response = chain.proceed(authorizedRequest);
            String bodyString = null;
            MediaType contentType = null;

            if (response.body() != null) {
                bodyString = response.body().string();
                contentType = response.body().contentType();
            }
            return response.newBuilder().body(ResponseBody.create(contentType, bodyString)).build();
        };
    }
}
