package com.mycompany.testtask.api;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.mycompany.testtask.models.Users;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitBuilder {

    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";
    private static Retrofit retrofit;
    static RetrofitInterface retrofitInterface;

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
