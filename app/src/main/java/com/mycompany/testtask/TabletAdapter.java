package com.mycompany.testtask;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.mycompany.testtask.models.User;
import com.mycompany.testtask.ui.phone.DownloadImageTask;
import com.mycompany.testtask.ui.tablet.UserInfo;

import java.util.List;

public class TabletAdapter extends RecyclerView.Adapter<TabletAdapter.ViewHolder> {
    private LayoutInflater inflater = null;
    protected List<User> users = null;
    private Context context;
    private Activity activity;


    public TabletAdapter(Context context, List<User> users, Activity activity) {
        this.users = users;
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.activity = activity;
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
        User user = users.get(position);
        holder.user = user;
        holder.activity = activity;
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
        View view;
        User user;
        Activity activity;

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
            FragmentManager fragmentManager = ((AppCompatActivity) activity).getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            UserInfo userInfo = new UserInfo();
            fragmentTransaction.replace(R.id.userInfo, userInfo).commit();
            userInfo.setNewData(user);
        }
    }
}
