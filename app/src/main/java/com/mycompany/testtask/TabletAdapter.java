package com.mycompany.testtask;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mycompany.testtask.models.Users;
import com.mycompany.testtask.ui.phone.DownloadImageTask;
import com.mycompany.testtask.ui.phone.FragmentUserInfo;
import com.mycompany.testtask.ui.tablet.UserInfo;

import java.util.List;

public class TabletAdapter extends RecyclerView.Adapter<TabletAdapter.ViewHolder> {
    private LayoutInflater inflater = null;
    protected List<Users> users = null;
    private Context context;
    FragmentManager manager;


    public TabletAdapter(Context context, List<Users> users, FragmentManager manager) {
        this.users = users;
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.manager = manager;
    }

    public TabletAdapter() {

    }

    @Override
    public TabletAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.user_item, parent, false);
        return new TabletAdapter.ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(TabletAdapter.ViewHolder holder, int position) {
        Users user = users.get(position);
        holder.user = user;
        holder.manager = manager;
        new DownloadImageTask(holder.imageView).execute("https://quizee.app/storage/avatars/" + user.getId() + ".jpeg");
        holder.nameView.setText(user.getName());
        holder.descriptionView.setText(user.getEmail());
        holder.infoView.setText(user.getCompany().getCatchPhrase());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        final TextView nameView, descriptionView, infoView;
        final ImageView imageView;
        ConstraintLayout item;
        private final Context context;
        FragmentManager manager;
        View view;
        Users user;

        ViewHolder(View view) {
            super(view);
            this.view = view;
            context = view.getContext();
            item = (ConstraintLayout) view.findViewById(R.id.item_parent);
            imageView = (ImageView) view.findViewById(R.id.image);
            nameView = (TextView) view.findViewById(R.id.name);
            descriptionView = (TextView) view.findViewById(R.id.description);
            infoView = (TextView) view.findViewById(R.id.info);
            view.setClickable(true);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
         UserInfo f = (UserInfo) manager.findFragmentByTag("unique_tag");
               f.setNewData(user);

        }
    }
}
