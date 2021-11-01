package com.mycompany.testtask.ui;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.mycompany.testtask.R;
import com.mycompany.testtask.api.RetrofitBuilder;
import com.mycompany.testtask.models.User;
import com.mycompany.testtask.ui.phone.UserAdapter;
import com.mycompany.testtask.util.DeviceUtil;
import com.mycompany.testtask.util.FileUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (DeviceUtil.isTablet(this.getApplicationContext())) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            setContentView(R.layout.fragment_tablet);
        } else {
            setContentView(R.layout.fragment_users_list);
            displayUsers();
        }

    }

    private void displayUsers() {
        Call<List<User>> listCall = new RetrofitBuilder().getApi().getUsers();
        listCall.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()) {
                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
                    try {
                        FileUtil.writeFile(response.body(), HomeActivity.this.getApplicationContext());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    UserAdapter userAdapter = new UserAdapter(response.body());
                    recyclerView.setAdapter(userAdapter);

                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                try {
                    BufferedReader br = new BufferedReader(new InputStreamReader(
                            openFileInput(FileUtil.FILE_NAME)));
                    List<User> userList;
                    StringBuilder objectsStr = new StringBuilder();
                    String tmp;
                    Gson gson = new Gson();
                    while ((tmp = br.readLine()) != null) {
                        objectsStr.append(tmp);
                    }
                    userList = Arrays.asList(gson.fromJson(objectsStr.toString(), User[].class));
                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
                    UserAdapter userAdapter = new UserAdapter(userList);
                    recyclerView.setAdapter(userAdapter);
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(), "Failure " + t, Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });
    }
}