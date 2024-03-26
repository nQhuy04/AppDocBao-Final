package com.example.appdoctintuc.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.appdoctintuc.Activity.TheThaoActivity;
import com.example.appdoctintuc.LoginActivity;
import com.example.appdoctintuc.MainActivity;
import com.example.appdoctintuc.R;


public class SettingFragment extends Fragment {
    public static SettingFragment newInstance() {
        Bundle args = new Bundle();
        // Thêm dữ liệu vào args nếu cần
        SettingFragment fragment = new SettingFragment(context);
        fragment.setArguments(args);
        return fragment;
    }
    private static Context context;
    public SettingFragment(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        View suckhoe = (View) view.findViewById(R.id.settings_item_logout);
        suckhoe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NhanVaoLogout();
            }
        });
        return view;

    }

    public void NhanVaoLogout () {
        Intent intent = new Intent(context, LoginActivity.class);
        startActivityForResult(intent, 0);
    }
}