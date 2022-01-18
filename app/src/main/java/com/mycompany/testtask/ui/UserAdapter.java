package com.mycompany.testtask.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.mycompany.testtask.R;
import com.mycompany.testtask.models.User;
import com.mycompany.testtask.util.DownloadImageTask;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    public static final String BASE_URL_IMG = "https://quizee.app/storage/avatars/";
    public static final String EXTENSION = ".jpeg";
    private List<User> users;
    private final OnUserClickListener onUserClickListener;

    public UserAdapter(List<User> users, OnUserClickListener onUserClickListener) {
        this.users = users;
        this.onUserClickListener = onUserClickListener;
    }

    @NonNull
    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(UserAdapter.ViewHolder holder, int position) {
        User user = users.get(position);
        new DownloadImageTask(holder.imageView).execute(BASE_URL_IMG + user.getId() + EXTENSION);
        holder.nameView.setText(user.getName());
        holder.descriptionView.setText(user.getEmail());
        holder.infoView.setText(user.getCompany().getCatchPhrase());
        holder.itemView.setOnClickListener(v ->
                holder.onItemClick(user));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private final View view;
        private final TextView nameView, descriptionView, infoView;
        private final ImageView imageView;

        ViewHolder(View view) {
            super(view);
            this.view = view;
            imageView = view.findViewById(R.id.image);
            nameView = view.findViewById(R.id.name);
            descriptionView = view.findViewById(R.id.description);
            infoView = view.findViewById(R.id.info);
        }

        public Context getContext() {
            return view.getContext();
        }

        private void onItemClick(User user) {
            onUserClickListener.onUserClick(user);
        }
    }

    public interface OnUserClickListener {
        void onUserClick(User user);
    }
}
