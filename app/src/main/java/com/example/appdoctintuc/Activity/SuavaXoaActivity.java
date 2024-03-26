package com.example.appdoctintuc.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.appdoctintuc.DatabaseManager;
import com.example.appdoctintuc.R;

import java.io.ByteArrayOutputStream;

public class SuavaXoaActivity extends AppCompatActivity {
    EditText edtTieuDe, edtNoiDungBaiBao;
    ImageView ivHinhBaiBao;
    ImageButton btnThemhinh;
    Button btnQuayLai, btnCapNhat, btnXoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suava_xoa);
        addControls();
        addEventsOpenIMGLibrary();
        addEventsCapnhat();
        addEventsXoa();
        Event();
    }

    private void addEventsXoa() {
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lấy id của bài báo từ Intent
                int idBaiBao = getIntent().getIntExtra("idBaiBao", -1);
                if(idBaiBao != -1) {
                    boolean kq = DatabaseManager.getInstance(SuavaXoaActivity.this).deleteArticle(idBaiBao);
                    if(kq) {
                        Toast.makeText(SuavaXoaActivity.this, "Xóa thành công", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(SuavaXoaActivity.this, "Xóa không thành công", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SuavaXoaActivity.this, "Không tìm thấy ID bài báo", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void Event(){
        btnQuayLai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    private void addEventsCapnhat() {
        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContentValues values = new ContentValues();
                values.put("tieudeBaiBao",edtTieuDe.getText().toString());
                values.put("noidungBaiBao",edtNoiDungBaiBao.getText().toString());
                Bitmap bitmap = ((BitmapDrawable) ivHinhBaiBao.getDrawable()).getBitmap();
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                values.put("anhBaiBao", byteArray);

                // Lấy id của bài báo từ Intent
                int idBaiBao = getIntent().getIntExtra("idBaiBao", -1);
                if(idBaiBao != -1) {
                    // Gọi phương thức updateArticle từ lớp DatabaseManager
                    boolean kq = DatabaseManager.getInstance(SuavaXoaActivity.this).updateArticle(idBaiBao, values);
                    if(kq) {
                        Toast.makeText(SuavaXoaActivity.this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                        finish();
                    } else {
                        Toast.makeText(SuavaXoaActivity.this, "Cập nhật không thành công", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(SuavaXoaActivity.this, "Không tìm thấy ID bài báo", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private void addControls() {
        edtTieuDe = findViewById(R.id.edtTieude);
        edtNoiDungBaiBao = findViewById(R.id.edtNoiDungBaiBao);
        ivHinhBaiBao = findViewById(R.id.ivHinhBaiBao);
        btnQuayLai = findViewById(R.id.btnQuaylai);
        btnCapNhat = findViewById(R.id.btnCapNhat);
        btnXoa = findViewById(R.id.btnXoa);
        btnThemhinh = findViewById(R.id.btnThemhinh);
        Intent intent = getIntent();
        String tieuDe = intent.getStringExtra("TieuDe");
        String noiDung = intent.getStringExtra("NoiDung");
        byte[] anhBaiBao = intent.getByteArrayExtra("AnhBaiBao");
        edtTieuDe.setText(tieuDe);
        edtNoiDungBaiBao.setText(noiDung);
        Bitmap bitmap = BitmapFactory.decodeByteArray(anhBaiBao, 0, anhBaiBao.length);
        ivHinhBaiBao.setImageBitmap(bitmap);

    }

    private static final int PICK_IMAGE_REQUEST = 1; // Mã yêu cầu khi mở thư viện ảnh

    private void addEventsOpenIMGLibrary() {
        btnThemhinh.setOnClickListener(new View.OnClickListener() {
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



}