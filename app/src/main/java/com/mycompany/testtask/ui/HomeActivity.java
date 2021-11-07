package com.mycompany.testtask.ui;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.mycompany.testtask.R;
import com.mycompany.testtask.api.Presenter;
import com.mycompany.testtask.api.RetrofitBuilder;
import com.mycompany.testtask.models.User;
import com.mycompany.testtask.ui.phone.FragmentUserInfo;
import com.mycompany.testtask.ui.phone.UserAdapter;
import com.mycompany.testtask.util.DeviceUtil;
import com.mycompany.testtask.util.FileUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity implements UserAdapter.OnUserListener {
    private List<User> users = new ArrayList<>();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (DeviceUtil.isTablet(this.getApplicationContext())) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            setContentView(R.layout.fragment_tablet);
        } else {
            setContentView(R.layout.fragment_users_list);
        }
        recyclerView = findViewById(R.id.list);
        new Presenter().getUsersList(this);
    }

    public void setAdapter(List<User> userList) {
        this.users = userList;
        UserAdapter userAdapter = new UserAdapter(userList, this);
        recyclerView.setAdapter(userAdapter);
    }

    @Override
    public void onUserClick(int position) {
        Intent intent = new Intent(this, FragmentUserInfo.class).putExtra("User", users.get(position));
        startActivity(intent);
    }
}