package com.mycompany.testtask.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.mycompany.testtask.R;
import com.mycompany.testtask.api.RetrofitBuilder;
import com.mycompany.testtask.models.User;
import com.mycompany.testtask.ui.phone.Adapter;

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
        if (isTablet(this.getApplicationContext())) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            setContentView(R.layout.fragment_tablet);
        } else {
            setContentView(R.layout.fragment_users_list);
            printUsers();
        }

    }

    public boolean isTablet(Context context) {
        boolean xlarge = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == 4);
        boolean large = ((context.getResources().getConfiguration().screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE);
        return (xlarge || large);
    }

    private void printUsers() {
        Call<List<User>> listCall = new RetrofitBuilder().getApi().getUsers();
        listCall.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(Call<List<User>> call, Response<List<User>> response) {
                if (response.isSuccessful()) {
                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
                    writeFile(response.body());
                    Adapter adapter = new Adapter(getApplicationContext(), response.body());
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<User>> call, Throwable t) {
                try {
                    BufferedReader br = new BufferedReader(new InputStreamReader(
                            openFileInput("FILENAME.obj")));
                    List<User> userList;
                    StringBuilder objectsStr = new StringBuilder();
                    String tmp;
                    Gson gson = new Gson();
                    while ((tmp = br.readLine()) != null) {
                        objectsStr.append(tmp);
                    }
                    userList = Arrays.asList(gson.fromJson(objectsStr.toString(), User[].class));
                    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.list);
                    Adapter adapter = new Adapter(getApplicationContext(), userList);
                    recyclerView.setAdapter(adapter);
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(), "Failure " + t, Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        });

    }

    void writeFile(List<User> list) {
        Gson gson = new Gson();
        String objectsStr = gson.toJson(list);
        BufferedWriter bw;
        try {
            bw = new BufferedWriter(new OutputStreamWriter(
                    openFileOutput("FILENAME.obj", MODE_PRIVATE)));
            bw.write(objectsStr);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}