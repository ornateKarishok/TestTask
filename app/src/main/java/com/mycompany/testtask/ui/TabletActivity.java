package com.mycompany.testtask.ui;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.mycompany.testtask.R;
import com.mycompany.testtask.models.User;
import com.mycompany.testtask.util.FragmentUtil;

public class TabletActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.fragment_tablet);
        FragmentUtil.replaceFragment(getSupportFragmentManager(),
                UserListFragment.newInstance(), R.id.userList);
        FragmentUtil.replaceFragment(getSupportFragmentManager(),
                UserInfoFragment.newInstance(null), R.id.userInfoFragment);
    }
}
