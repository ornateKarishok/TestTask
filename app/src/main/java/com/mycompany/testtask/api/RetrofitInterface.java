package com.mycompany.testtask.api;

import com.mycompany.testtask.models.Users;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface RetrofitInterface {
    @GET("users")
    Call<List<Users>> getUsers();
}
