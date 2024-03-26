package com.example.appdoctintuc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.appdoctintuc.Activity.TheThaoActivity;

import java.io.ByteArrayOutputStream;

public class ThemBaiBaoActivity extends AppCompatActivity {

    EditText edtTieuDe,edtNoiDungBaiBao;
    Spinner spinIdTheLoai;
    ImageView ivHinhBaiBao;
    ImageButton btnThemHinh;
    Button btnThemMoi,btnQuayLai;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_bai_bao);
        addControls();
        addEventsOpenIMGLibrary();
        addEventsThemBaiBao();
        Event();
    }
    private void Event(){
        btnQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void addEventsThemBaiBao() {
        btnThemMoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BitmapDrawable bitmapDrawable = (BitmapDrawable) ivHinhBaiBao.getDrawable();
                Bitmap bitmap = bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
                byte[]hinh = byteArrayOutputStream.toByteArray();
                DatabaseManager.getInstance(ThemBaiBaoActivity.this).InsertRecord(
                        edtTieuDe.getText().toString().trim(),
                        edtNoiDungBaiBao.getText().toString().trim(),
                        hinh
                );
                Toast.makeText(ThemBaiBaoActivity.this, "Insert Successfull", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    private static final int PICK_IMAGE_REQUEST = 1; // Mã yêu cầu khi mở thư viện ảnh

    private void addEventsOpenIMGLibrary() {
        btnThemHinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tạo Intent để mở thư viện ảnh
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Chọn Hình Ảnh"), PICK_IMAGE_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            // Lấy URI của hình ảnh đã chọn
            Uri selectedImageUri = data.getData();
            // Sử dụng URI này để hiển thị hình ảnh trong ImageView
            ivHinhBaiBao.setImageURI(selectedImageUri);
        }
    }




    private void addControls() {
        edtTieuDe = findViewById(R.id.edtTieude);
        edtNoiDungBaiBao = findViewById(R.id.edtNoiDungBaiBao);
        spinIdTheLoai = findViewById(R.id.spinIdTheLoai);
        ivHinhBaiBao = findViewById(R.id.ivHinhBaiBao);
        btnThemHinh = findViewById(R.id.btnThemhinh);
        btnThemMoi = findViewById(R.id.btnThemMoi);
        btnQuayLai = findViewById(R.id.btnQuaylai);

    }
}