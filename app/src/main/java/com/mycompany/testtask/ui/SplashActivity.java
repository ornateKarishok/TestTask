package com.mycompany.testtask.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.mycompany.testtask.R;
import com.mycompany.testtask.util.DeviceUtil;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    Intent openStartingPoint;
                    if (DeviceUtil.isTablet(getApplicationContext())) {
                        openStartingPoint = new Intent(SplashActivity.this, TabletActivity.class);
                    } else {
                        openStartingPoint = new Intent(SplashActivity.this, HomeActivity.class);
                    }
                    startActivity(openStartingPoint);
                    finish();
                }
            }
        };
        timer.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}