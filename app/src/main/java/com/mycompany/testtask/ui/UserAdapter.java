package com.mycompany.testtask.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.mycompany.testtask.R;
import com.mycompany.testtask.models.User;
import com.mycompany.testtask.util.DownloadImageTask;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    public static final String BASE_URL_IMG = "https://quizee.app/storage/avatars/";
    public static final String EXTENSION = ".jpeg";
    private List<User> users = new ArrayList<>();
    private OnUserListener onUserListener;

    public UserAdapter(List<User> users, OnUserListener onUserListener) {
        this.users = users;
        this.onUserListener = onUserListener;
    }

    public UserAdapter() {
    }

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
                holder.onItemClick(holder.getAdapterPosition()));
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
            imageView = (ImageView) view.findViewById(R.id.image);
            nameView = (TextView) view.findViewById(R.id.name);
            descriptionView = (TextView) view.findViewById(R.id.description);
            infoView = (TextView) view.findViewById(R.id.info);
        }

        public Context getContext() {
            return view.getContext();
        }

        private void onItemClick(int position) {
            onUserListener.onUserClick(position);
        }
    }

    public interface OnUserListener {
        void onUserClick(int position);
    }
}
