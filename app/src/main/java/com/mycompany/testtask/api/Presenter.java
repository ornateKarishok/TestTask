package com.mycompany.testtask.api;

import android.widget.Toast;

import com.mycompany.testtask.models.User;
import com.mycompany.testtask.ui.HomeActivity;
import com.mycompany.testtask.util.FileUtil;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Presenter {

    public void getUsersList(HomeActivity homeActivity) {
        Call<List<User>> listCall = new RetrofitBuilder().getApi().getUsers();
        listCall.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()) {
                    try {
                        FileUtil.writeFile(response.body(), homeActivity.getApplicationContext());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    homeActivity.setAdapter(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                List<User> users = FileUtil.readFile(homeActivity);
                if (users.isEmpty()) {
                    Toast.makeText(homeActivity.getApplicationContext(), "Failure " + t, Toast.LENGTH_LONG).show();
                    t.printStackTrace();
                } else {
                    homeActivity.setAdapter(users);
                }
            }
        });
    }
}
