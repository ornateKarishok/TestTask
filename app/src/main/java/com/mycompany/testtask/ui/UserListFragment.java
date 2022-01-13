package com.mycompany.testtask.ui;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.mycompany.testtask.R;
import com.mycompany.testtask.api.ApiExecutor;
import com.mycompany.testtask.api.OnUsersLoadListener;
import com.mycompany.testtask.models.User;
import com.mycompany.testtask.util.DeviceUtil;
import com.mycompany.testtask.util.FileUtil;
import com.mycompany.testtask.util.FragmentUtil;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UserListFragment extends Fragment implements UserAdapter.OnUserClickListener, OnUsersLoadListener {
    private RecyclerView recyclerView;
    private List<User> users = new ArrayList<>();

    @SuppressLint("InflateParams")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.users_list_tablet, null);

    }

    public static UserListFragment newInstance() {
        return new UserListFragment();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = requireView().findViewById(R.id.list);
        new ApiExecutor().getUsersList(this);
    }

    @Override
    public void onUserClick(int position) {
        if (DeviceUtil.isTablet(requireContext())) {
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.userInfoFragment, UserInfoFragment.newInstance(this.users.get(position))).commit();
        } else {
            FragmentUtil.replaceFragment(getParentFragmentManager(),
                    UserInfoFragment.newInstance(this.users.get(position)), R.id.container);
        }
    }

    @Override
    public void onDataLoad(List<User> users) {
        this.users = users;
        try {
            if (getContext() != null) {
                FileUtil.writeFile(users, getContext());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        UserAdapter userAdapter = new UserAdapter(users, this);
        recyclerView.setAdapter(userAdapter);
    }

    @Override
    public void onError(Throwable t) {
        List<User> users = FileUtil.readFile(requireContext());
        if (!users.isEmpty()) {
            this.users = users;
            UserAdapter userAdapter = new UserAdapter(users, this);
            recyclerView.setAdapter(userAdapter);
        } else {
            Toast.makeText(getContext(), Resources.getSystem().getString(R.string.failure) + t, Toast.LENGTH_LONG).show();
        }
    }
}
