package com.mycompany.testtask.api;

import com.mycompany.testtask.models.User;

import java.util.List;

public interface OnUsersLoadListener {
    void onDataLoad(List<User> users);

    void onError(Throwable t);
}
