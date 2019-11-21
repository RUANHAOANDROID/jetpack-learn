package com.example.jetpack.viewmodule;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.jetpack.R;
import com.example.jetpack.databinding.ViewModuleFragmentBinding;

import java.util.List;

public class ViewModuleFragment extends Fragment implements Observer<List<User>> {
    ViewModuleFragmentBinding binding;
    private ViewModuleViewModel mViewModel;

    public static ViewModuleFragment newInstance() {
        return new ViewModuleFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.bind(inflater.inflate(R.layout.view_module_fragment, container, false));

        return binding.getRoot();
    }

    private void setUses() {
        /**
         * 异步监听Users
         * 直接getUsers会空指针
         */
        mViewModel.getUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                User user = users.get(0);
                binding.name1.setText(user.name);
                binding.age1.setText(String.valueOf(user.age));
            }
        });
        /**
         * 这种方式要注意手动取消观察。并未绑定LifecycleOwner
         */
        mViewModel.getUsers().observeForever(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mViewModel.getUsers().removeObserver(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ViewModuleViewModel.class);
        setUses();
    }

    @Override
    public void onChanged(List<User> users) {
        User user = users.get(1);
        binding.name2.setText(user.name);
        binding.age2.setText(String.valueOf(user.age));
    }
}
