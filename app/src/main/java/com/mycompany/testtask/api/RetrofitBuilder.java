package com.mycompany.testtask.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private static Retrofit retrofit;
    private static RetrofitInterface retrofitInterface;

    public RetrofitBuilder() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
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
