package com.example.jetpack.navigation;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jetpack.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SafeArgsFragment extends Fragment {

    public SafeArgsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_safe_args, container, false);
        TextView textView1=view.findViewById(R.id.text1);
        TextView textView2 =view.findViewById(R.id.text2);
        TextView textView3 =view.findViewById(R.id.text3);
        SafeArgsFragmentArgs args =SafeArgsFragmentArgs.fromBundle(getArguments());
        textView1.setText("名字是： "+args.getArgName());
        textView2.setText("年龄是： "+args.getArgAge());
        textView3.setText(args.getUser().toString());
        return  view;
    }

}
