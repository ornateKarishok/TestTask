package com.mycompany.testtask.api;

import androidx.annotation.NonNull;

import com.mycompany.testtask.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiExecutor {

    public void getUsersList(OnUsersLoadListener listener) {
        new RetrofitBuilder();
        Call<List<User>> listCall = RetrofitBuilder.getApi().getUsers();
        listCall.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(@NonNull Call<List<User>> call, @NonNull Response<List<User>> response) {
                if (response.isSuccessful()) {
                    listener.onDataLoad(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<User>> call, @NonNull Throwable t) {
                listener.onError(t);
            }
        });
    }
}
