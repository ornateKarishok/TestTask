package com.mycompany.testtask.ui.tablet;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mycompany.testtask.R;
import com.mycompany.testtask.models.Users;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.OutputStreamWriter;

public class UserInfo extends Fragment {
    TextView nameTextView;
    TextView emailTextView;
    TextView phoneTextView;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.user_info_tablet, null);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        nameTextView = view.findViewById(R.id.name_value);
        emailTextView = view.findViewById(R.id.email_value);
        phoneTextView = view.findViewById(R.id.phone_value);

    }

    public void setNewData(Users user) {

        nameTextView.setText(" " + user.getName());


        emailTextView.setText(" " + user.getEmail());
        emailTextView.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", user.getEmail(), null));
            startActivity(Intent.createChooser(intent, "Choose an Email client :"));
        });

        phoneTextView.setText(" " + user.getPhone());
        phoneTextView.setOnClickListener(v -> {
            Intent callIntent = new Intent(Intent.ACTION_DIAL);
            callIntent.setData(Uri.parse("tel:" + user.getPhone()));
            startActivity(callIntent);
        });
//        WebView webView = (WebView) user(R.id.webView);
//        WebSettings webSettings = webView.getSettings();
//        webSettings.setJavaScriptEnabled(true);
//        webView.setWebViewClient(new WebViewClient() {
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                return false;
//            }
//        });
//        webView.loadUrl(user.getWebsite());
//        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
//        assert mapFragment != null;
//        mapFragment.getMapAsync(this);


    }

//    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        mMap = googleMap;
//        Log.d("mMap", (user.getAddress().getGeo().getLat()));
//        LatLng coordinates = new LatLng(Double.parseDouble(user.getAddress().getGeo().getLat()),
//                Double.parseDouble(selectedUser.getAddress().getGeo().getLng()));
//        mMap.addMarker(new MarkerOptions().position(coordinates).title(selectedUser.getAddress().getStreet()));
//        CameraUpdate location = CameraUpdateFactory.newLatLngZoom(
//                coordinates, 15);
//        mMap.animateCamera(location);
//    }
//
//    @Override
//    public void onPointerCaptureChanged(boolean hasCapture) {
//
//    }
}
