package com.mycompany.testtask.ui;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.mycompany.testtask.R;
import com.mycompany.testtask.api.Presenter;
import com.mycompany.testtask.models.User;
import com.mycompany.testtask.ui.phone.UserInfoFragment;
import com.mycompany.testtask.util.DeviceUtil;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements UserAdapter.OnUserClickListener {
    public static final String EXTRA_NAME = "User";
    private List<User> users = new ArrayList<>();
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (DeviceUtil.isTablet(this.getApplicationContext())) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            setContentView(R.layout.fragment_tablet);
        } else {
            setContentView(R.layout.fragment_users_list);
            recyclerView = findViewById(R.id.list);
            Presenter.getUsersList(this);
        }
    }

    @Override
    public void onUserClick(int position) {
        Intent intent = new Intent(this, UserInfoFragment.class).putExtra(EXTRA_NAME, users.get(position));
        startActivity(intent);
    }
    public void setAdapter(List<User> userList) {
        this.users = userList;
        UserAdapter userAdapter = new UserAdapter(userList, this);
        recyclerView.setAdapter(userAdapter);
    }
}