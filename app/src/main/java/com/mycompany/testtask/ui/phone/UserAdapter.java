package com.mycompany.testtask.ui.phone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.mycompany.testtask.R;
import com.mycompany.testtask.models.User;

import java.util.ArrayList;
import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {
    private List <User> users = new ArrayList<>();
    private OnUserListener onUserListener;
    public static final String BASE_URL_IMG = "https://quizee.app/storage/avatars/";
    public static final String EXTENSION = ".jpeg";

    public UserAdapter(List<User> users, OnUserListener onUserListener) {
        this.users = users;
        this.onUserListener = onUserListener;
    }

    public UserAdapter() {

    }

    @Override
    public UserAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.user_item, parent, false);
        return new ViewHolder(view, onUserListener);
    }


    @Override
    public void onBindViewHolder(UserAdapter.ViewHolder holder, int position) {
        User user = users.get(position);
        new DownloadImageTask(holder.imageView).execute(BASE_URL_IMG + user.getId() +EXTENSION );
        holder.nameView.setText(user.getName());
        holder.descriptionView.setText(user.getEmail());
        holder.infoView.setText(user.getCompany().getCatchPhrase());
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private final View view;
        private final TextView nameView, descriptionView, infoView;
        private final ImageView imageView;
        OnUserListener onUserListener;

        ViewHolder(View view, OnUserListener onUserListener) {
            super(view);
            this.view = view;
            ConstraintLayout item = (ConstraintLayout) view.findViewById(R.id.item_parent);
            imageView = (ImageView) view.findViewById(R.id.image);
            nameView = (TextView) view.findViewById(R.id.name);
            descriptionView = (TextView) view.findViewById(R.id.description);
            infoView = (TextView) view.findViewById(R.id.info);
            this.onUserListener = onUserListener;
            view.setClickable(true);
            view.setOnClickListener(this);
        }

        public Context getContext() {
            return view.getContext();
        }

        @Override
        public void onClick(View v) {
            onUserListener.onUserClick(getAdapterPosition());
        }
    }

    public interface OnUserListener {
        void onUserClick(int position);
    }
}
