package com.mycompany.testtask.ui;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.mycompany.testtask.R;
import com.mycompany.testtask.models.User;
import com.mycompany.testtask.util.DeviceUtil;
import com.mycompany.testtask.util.FragmentUtil;

public class HomeActivity extends AppCompatActivity {
    boolean isFragmentLoaded = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (!DeviceUtil.isTablet(this.getApplicationContext())) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            setContentView(R.layout.fragment_tablet);
            if (!isFragmentLoaded) {
                FragmentUtil.addFragment(getSupportFragmentManager(),
                        UserListFragment.newInstance(), R.id.userList);
                FragmentUtil.addFragment(getSupportFragmentManager(),
                        UserInfoFragment.newInstance(new User()), R.id.userInfoFragment);
                isFragmentLoaded = true;
            }
        } else {
            setContentView(R.layout.activity_home);
            FragmentUtil.replaceFragment(getSupportFragmentManager(),
                    UserListFragment.newInstance(), R.id.container);
        }
    }
}