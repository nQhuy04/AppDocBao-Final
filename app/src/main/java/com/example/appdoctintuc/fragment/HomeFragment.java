package com.example.appdoctintuc.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.appdoctintuc.Activity.GiaoDucActivity;
import com.example.appdoctintuc.Activity.KhoaHocActivity;
import com.example.appdoctintuc.Activity.KinhDoanhActivity;
import com.example.appdoctintuc.Activity.SucKhoeActivity;
import com.example.appdoctintuc.Activity.TheGioiActivity;
import com.example.appdoctintuc.Activity.TheThaoActivity;
import com.example.appdoctintuc.Activity.ThoiSuActivity;

import com.example.appdoctintuc.R;





public class HomeFragment extends Fragment {

    private static Context context;

    public HomeFragment(Context context) {
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        TextView thethao = view.findViewById(R.id.thethao_card);
        thethao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NhanVaoTheThao();
            }
        });

        TextView thegioi = view.findViewById(R.id.thegioi_card);
        thegioi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NhanVaoTheGioi();
            }
        });

        TextView thoisu = view.findViewById(R.id.thoisu_card);
        thegioi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NhanVaoThoiSu();
            }
        });

        TextView khoahoc = view.findViewById(R.id.khoahoc_card);
        thegioi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NhanVaoKhoaHoc();
            }
        });

        TextView kinhdoanh = view.findViewById(R.id.kinhdoanh_card);
        thegioi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NhanVaoKinhDoanh();
            }
        });

        TextView giaoduc = view.findViewById(R.id.giaoduc_card);
        thegioi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NhanVaoGiaoDuc();
            }
        });

        TextView suckhoe = view.findViewById(R.id.suckhoe_card);
        thegioi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NhanVaoSucKhoe();
            }
        });

        return view;
    }

    public void NhanVaoTheThao () {
        Intent intent = new Intent(context, TheThaoActivity.class);
        startActivityForResult(intent, 0);
    }

    public void NhanVaoTheGioi () {
        Intent intent = new Intent(context, TheGioiActivity.class);
        startActivityForResult(intent, 0);
    }

    public void NhanVaoThoiSu () {
        Intent intent = new Intent(context, ThoiSuActivity.class);
        startActivityForResult(intent, 0);
    }

    public void NhanVaoKhoaHoc () {
        Intent intent = new Intent(context, KhoaHocActivity.class);
        startActivityForResult(intent, 0);
    }

    public void NhanVaoKinhDoanh () {
        Intent intent = new Intent(context, KinhDoanhActivity.class);
        startActivityForResult(intent, 0);
    }

    public void NhanVaoGiaoDuc () {
        Intent intent = new Intent(context, GiaoDucActivity.class);
        startActivityForResult(intent, 0);
    }

    public void NhanVaoSucKhoe () {
        Intent intent = new Intent(context, SucKhoeActivity.class);
        startActivityForResult(intent, 0);
    }

}