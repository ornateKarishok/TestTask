package com.mycompany.testtask.api;

import com.mycompany.testtask.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitInterface {
    @GET("users")
    Call<List<User>> getUsers();
}
