package com.mycompany.testtask.api;

import com.mycompany.testtask.models.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ApiExecutor {

    public void getUsersList(OnUsersLoadListener listener) {
        Call<List<User>> listCall = new RetrofitBuilder().getApi().getUsers();
        listCall.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()) {
                    listener.onDataLoad(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                listener.onError(t);
            }
        });
    }
}
