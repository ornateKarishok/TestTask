package com.mycompany.testtask.api;

import android.content.res.Resources;
import android.widget.Toast;

import com.mycompany.testtask.R;
import com.mycompany.testtask.models.User;
import com.mycompany.testtask.ui.HomeActivity;
import com.mycompany.testtask.ui.tablet.UserList;
import com.mycompany.testtask.util.FileUtil;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Presenter {

    public static void getUsersList(HomeActivity activity) {
        Call<List<User>> listCall = new RetrofitBuilder().getApi().getUsers();
        listCall.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()) {
                    try {
                        FileUtil.writeFile(response.body(), activity.getApplicationContext());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    activity.setAdapter(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                List<User> users = FileUtil.readFile(activity);
                if (users.isEmpty()) {
                    Toast.makeText(activity.getApplicationContext(), Resources.getSystem().getString(R.string.failure) + t, Toast.LENGTH_LONG).show();
                    t.printStackTrace();
                } else {
                    activity.setAdapter(users);
                }
            }
        });
    }

    public static void getUsersList(UserList fragment) {
        Call<List<User>> listCall = new RetrofitBuilder().getApi().getUsers();
        listCall.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()) {
                    try {
                        FileUtil.writeFile(response.body(), fragment.getContext());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    fragment.setAdapter(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                List<User> users = FileUtil.readFile(fragment);
                if (users.isEmpty()) {
                    Toast.makeText(fragment.getContext(), Resources.getSystem().getString(R.string.failure) + t, Toast.LENGTH_LONG).show();
                    t.printStackTrace();
                } else {
                    fragment.setAdapter(users);
                }
            }
        });
    }
}
