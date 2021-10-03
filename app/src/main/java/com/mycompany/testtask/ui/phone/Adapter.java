package com.mycompany.testtask.ui.phone;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.mycompany.testtask.R;
import com.mycompany.testtask.models.User;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    private LayoutInflater inflater = null;
    private List<User> users = null;
    private Context context;


    public Adapter(Context context, List<User> users) {
        this.users = users;
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    public Adapter() {

    }

    @Override
    public Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.user_item, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(Adapter.ViewHolder holder, int position) {
        User user = users.get(position);
        holder.user = user;
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
        User user;

        ViewHolder(View view) {
            super(view);
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
            final Intent intent;
            Log.d("Tagfail ", user.getPhone());
            intent = new Intent(context, FragmentUserInfo.class).putExtra("User", user);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }
}
