package com.example.appdoctintuc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appdoctintuc.model.BaiBao;

public class DetailActivity extends AppCompatActivity {
    TextView txtTieudeBB,txtnoidungBB;
    ImageView ivAnhBB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        addControls();
    }

    private void addControls() {
        txtTieudeBB = findViewById(R.id.txtTieudeBB);
        txtnoidungBB = findViewById(R.id.txtNoiDungBB);
        ivAnhBB = findViewById(R.id.ivAnhBB);
        Intent intent = getIntent();
        String tieuDe = intent.getStringExtra("TieuDe");
        String noiDung = intent.getStringExtra("NoiDung");
        byte[] anhBaiBao = intent.getByteArrayExtra("AnhBaiBao");
        txtTieudeBB.setText(tieuDe);
        txtnoidungBB.setText(noiDung);
        Bitmap bitmap = BitmapFactory.decodeByteArray(anhBaiBao, 0, anhBaiBao.length);
        ivAnhBB.setImageBitmap(bitmap);
    }
}