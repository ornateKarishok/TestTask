package com.mycompany.testtask.ui.phone;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mycompany.testtask.R;
import com.mycompany.testtask.models.User;

public class FragmentUserInfo extends AppCompatActivity implements OnMapReadyCallback {
    private User selectedUser;
    SupportMapFragment mapFragment;
    private GoogleMap mMap;
    Marker marker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_user_info);
        selectedUser = getIntent().getParcelableExtra("User");
        Log.d("testtag", selectedUser.getName());

        TextView nameTextView = findViewById(R.id.name_value);
        nameTextView.setText(" " + selectedUser.getName());

        TextView emailTextView = findViewById(R.id.email_value);
        emailTextView.setText(" " + selectedUser.getEmail());
        emailTextView.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", selectedUser.getEmail(), null));
            startActivity(Intent.createChooser(intent, "Choose an Email client :"));
        });

        TextView phoneTextView = findViewById(R.id.phone_value);
        phoneTextView.setText(" " + selectedUser.getPhone());
        phoneTextView.setOnClickListener(v -> {
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:" + selectedUser.getPhone()));
            startActivity(callIntent);
        });
        WebView webView = (WebView) findViewById(R.id.webView);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });
        webView.loadUrl(selectedUser.getWebsite());
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);


    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        Log.d("mMap", (selectedUser.getAddress().getGeo().getLat()));
        LatLng coordinates = new LatLng(Double.parseDouble(selectedUser.getAddress().getGeo().getLat()),
                Double.parseDouble(selectedUser.getAddress().getGeo().getLng()));
        mMap.addMarker(new MarkerOptions().position(coordinates).title(selectedUser.getAddress().getStreet()));
        CameraUpdate location = CameraUpdateFactory.newLatLngZoom(
                coordinates, 15);
        mMap.animateCamera(location);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
