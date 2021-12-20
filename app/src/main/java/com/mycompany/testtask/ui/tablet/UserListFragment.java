package com.mycompany.testtask.ui.tablet;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.mycompany.testtask.R;
import com.mycompany.testtask.api.Presenter;
import com.mycompany.testtask.models.User;
import com.mycompany.testtask.ui.UserAdapter;

import java.util.ArrayList;
import java.util.List;

public class UserListFragment extends Fragment implements UserAdapter.OnUserClickListener {
    private RecyclerView recyclerView;
    private List<User> users = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.users_list_tablet, null);

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        recyclerView = (RecyclerView) getView().findViewById(R.id.list);
        Presenter.getUsersList(this);
    }

    public void setAdapter(List<User> userList) {
        this.users = userList;
        UserAdapter userAdapter = new UserAdapter(userList, this);
        recyclerView.setAdapter(userAdapter);
    }

    @Override
    public void onUserClick(int position) {
        FragmentManager fragmentManager = ((AppCompatActivity) getActivity()).getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        UserInfoFragment userInfoFragment = new UserInfoFragment();
        fragmentTransaction.replace(R.id.userInfoFragment, userInfoFragment).commit();
        userInfoFragment.setNewData(this.users.get(position));
    }
}
