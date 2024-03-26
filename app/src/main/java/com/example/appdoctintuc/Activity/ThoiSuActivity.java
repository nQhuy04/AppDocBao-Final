package com.example.appdoctintuc.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.appdoctintuc.R;
import com.example.appdoctintuc.fragment.HomeFragment;

public class ThoiSuActivity extends AppCompatActivity {

    ImageButton imbBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thoi_su);
        imbBack.setOnClickListener(NhanVaoNutBack());
    }

    @NonNull
    private  View.OnClickListener NhanVaoNutBack() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThoiSuActivity.this, HomeFragment.class);
                startActivity(intent);
                // Kết thúc (finish) Activity hiện tại nếu bạn muốn
                finish();
            }
        };
    }
}