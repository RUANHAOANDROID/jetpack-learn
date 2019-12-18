package com.example.jetpack.viewmodule;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class ViewModuleViewModel extends ViewModel {
    private MutableLiveData<List<User>> users;

    public LiveData<List<User>> getUsers() {
        if (users == null) {
            users = new MutableLiveData<List<User>>();
            loadUsers();
        }
        return users;
    }

    private void loadUsers() {
        // Do an asynchronous operation to fetch users.

        new Thread(() -> {
            List<User> us = new ArrayList<>();
            us.add(new User("mumu", 19));
            try {
                Thread.sleep(1000);//模拟1秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            us.add(new User("akl", 18));
            //执行完异步通过postValue赋值
            users.postValue(us);
        }).start();

    }
}
