package com.example.jetpack.databinding;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.example.jetpack.R;


public class DataBindingFragment extends Fragment {
    FragmentViewBindingBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_view_binding, container, false);
        binding = FragmentViewBindingBinding.bind(rootview);
        binding = DataBindingUtil.bind(rootview);//通过bind View的方式
        String key =getArguments().getString("key","没找到");
        binding.setTextone(key);
        binding.tv2.setText(key);
        binding.setTextTwo("tv3Default");
        return rootview;
    }

}
