package com.mycompany.testtask.ui.tablet;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mycompany.testtask.R;
import com.mycompany.testtask.models.User;

public class UserInfo extends Fragment implements OnMapReadyCallback {
    public static final String SCHEME = "mailto";
    private TextView nameTextView;
    private TextView emailTextView;
    private TextView phoneTextView;
    private User user;
    private GoogleMap mMap;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.user_info_tablet, container, false);
        nameTextView = view.findViewById(R.id.name_value);
        emailTextView = view.findViewById(R.id.email_value);
        phoneTextView = view.findViewById(R.id.phone_value);
        if (user != null) {
            nameTextView.setText(user.getName());
            emailTextView.setText(user.getEmail());
            emailTextView.setOnClickListener(v -> {
                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        SCHEME, user.getEmail(), null));
                startActivity(Intent.createChooser(intent, getResources().getString(R.string.choose_an_email)));
            });

            phoneTextView.setText(user.getPhone());
            phoneTextView.setOnClickListener(v -> {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse(getResources().getString(R.string.tel) + user.getPhone()));
                startActivity(callIntent);
            });

            WebView webView = (WebView) view.findViewById(R.id.webView);
            WebSettings webSettings = webView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webView.setWebViewClient(new WebViewClient() {
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    return false;
                }
            });
            webView.loadUrl(user.getWebsite());
            SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
            assert mapFragment != null;
            mapFragment.getMapAsync(this);
        }
        return view;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng coordinates = new LatLng(Double.parseDouble(user.getAddress().getGeo().getLat()),
                Double.parseDouble(user.getAddress().getGeo().getLng()));
        mMap.addMarker(new MarkerOptions().position(coordinates).title(user.getAddress().getStreet()));
        CameraUpdate location = CameraUpdateFactory.newLatLngZoom(
                coordinates, 15);
        mMap.animateCamera(location);
    }
    public void setNewData(User user) {
        this.user = user;
    }
}
