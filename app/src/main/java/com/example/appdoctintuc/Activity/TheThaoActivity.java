package com.example.appdoctintuc.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.appdoctintuc.Adapter.BaiBaoAdapter;
import com.example.appdoctintuc.DBHandle;
import com.example.appdoctintuc.DetailActivity;
import com.example.appdoctintuc.R;
import com.example.appdoctintuc.ThemBaiBaoActivity;
import com.example.appdoctintuc.model.BaiBao;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class TheThaoActivity extends AppCompatActivity {
    ListView lvBaiBao;
    Button btnOpenNew;
    ImageButton btnMore;
    ArrayList<BaiBao> arrayListBaiBao;
    BaiBaoAdapter adapterBaiBao;
    public static DBHandle database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_thao);
        addControls();
        addEventsOpen();
        xemchitiet();
        goToCrud();

        // Các phương thức khởi tạo...
        database = new DBHandle(this,"csdl_Do_An.db",null,1);

        // Tạo bảng TheLoai
        database.QueryData("CREATE TABLE IF NOT EXISTS TheLoai (\n" +
                "idLoai INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "tenTheLoai TEXT);\n");

        // Tạo bảng BaiBao
        database.QueryData("CREATE TABLE IF NOT EXISTS BaiBao (\n" +
                "idBaiBao INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "tieudeBaiBao TEXT,\n" +
                "noidungBaiBao TEXT,\n" +
                "idLoai INTEGER,\n" +
                "anhBaiBao BLOB,\n" +
                "FOREIGN KEY (idLoai) REFERENCES TheLoai(idLoai));\n");

        // Tạo bảng User
        database.QueryData("CREATE TABLE IF NOT EXISTS User (\n" +
                "idUser INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "tenUser TEXT,\n" +
                "taikhoanUser TEXT,\n" +
                "matkhauUser TEXT,\n" +
                "emailUser TEXT,\n" +
                "namsinhUser TEXT,\n" +
                "gioitinhUser TEXT);\n");

        // Tạo bảng LuuTru
        database.QueryData("CREATE TABLE IF NOT EXISTS LuuTru (\n" +
                "idLuuTru INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "idUser INTEGER,\n" +
                "idBaiBao INTEGER,\n" +
                "FOREIGN KEY (idUser) REFERENCES User(idUser),\n" +
                "FOREIGN KEY (idBaiBao) REFERENCES BaiBao(idBaiBao));\n");

        // Tạo bảng BinhLuan
        database.QueryData("CREATE TABLE IF NOT EXISTS BinhLuan (\n" +
                "idBinhLuan INTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "idUser INTEGER,\n" +
                "idBaiBao INTEGER,\n" +
                "noidungBinhLuan TEXT,\n" +
                "ngayBinhLuan DATE,\n" +
                "FOREIGN KEY (idUser) REFERENCES User(idUser),\n" +
                "FOREIGN KEY (idBaiBao) REFERENCES BaiBao(idBaiBao));\n");
        Cursor cursor = database.GetData("SELECT * FROM BaiBao");
        while (cursor.moveToNext()) {
            arrayListBaiBao.add(new BaiBao(
                    cursor.getInt(0),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3),
                    cursor.getBlob(4)
            ));
        }
        adapterBaiBao.notifyDataSetChanged();

    }

    private void goToCrud() {

    }


    public void xemchitiet() {
        lvBaiBao.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BaiBao baibao = (BaiBao) parent.getItemAtPosition(position);

                // Nén hình ảnh trước khi chuyển
                Bitmap bitmap = BitmapFactory.decodeByteArray(baibao.getAnhBaiBao(), 0, baibao.getAnhBaiBao().length);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream);
                byte[] byteArray = stream.toByteArray();

                // Tạo Intent để mở Activity mới
                Intent intent = new Intent(TheThaoActivity.this, DetailActivity.class);
                // Truyền dữ liệu sang Activity mới
                intent.putExtra("TieuDe", baibao.getTenBaiBao());
                intent.putExtra("NoiDung", baibao.getNdBaiBao());
                intent.putExtra("AnhBaiBao", byteArray); // Chuyển hình ảnh đã nén
                // Khởi chạy Activity mới
                startActivity(intent);
            }
        });
    }



    private void addEventsOpen() {
        btnOpenNew = findViewById(R.id.btnOpenNew);
        btnOpenNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TheThaoActivity.this, ThemBaiBaoActivity.class);
                startActivity(intent);
            }
        });
    }

    private void addControls() {
        lvBaiBao = findViewById(R.id.lvBaiBao);
        btnOpenNew = findViewById(R.id.btnOpenNew);
        arrayListBaiBao = new ArrayList<>();
        adapterBaiBao = new BaiBaoAdapter(this,R.layout.layout_item,arrayListBaiBao);
        lvBaiBao.setAdapter(adapterBaiBao);
    }


}