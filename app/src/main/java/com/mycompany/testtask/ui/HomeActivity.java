package com.mycompany.testtask.ui;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.mycompany.testtask.R;
import com.mycompany.testtask.util.DeviceUtil;
import com.mycompany.testtask.util.FragmentUtil;

public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (DeviceUtil.isTablet(this.getApplicationContext())) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            setContentView(R.layout.fragment_tablet);
        } else {
            setContentView(R.layout.activity_home);
            FragmentUtil.replaceFragment(getSupportFragmentManager(),
                    UserListFragment.newInstance());
        }
    }
}