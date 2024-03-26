package com.example.appdoctintuc.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import com.example.appdoctintuc.Adapter.BaiBaoAdapter;
import com.example.appdoctintuc.DatabaseManager;
import com.example.appdoctintuc.DetailActivity;
import com.example.appdoctintuc.MainActivity;
import com.example.appdoctintuc.R;
import com.example.appdoctintuc.ThemBaiBaoActivity;
import com.example.appdoctintuc.model.BaiBao;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class TheThaoActivity extends AppCompatActivity {
    ListView lvBaiBao;
    Button btnOpenNew;
    ImageButton imbBack;
    ArrayList<BaiBao> arrayListBaiBao;
    BaiBaoAdapter adapterBaiBao;
    DatabaseManager database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_the_thao);
        addControls();
        addEventsOpen();
        xemchitiet();
        imbBack.setOnClickListener(NhanVaoNutBack());

        // Sử dụng DatabaseManager để lấy thể hiện cơ sở dữ liệu
        database = DatabaseManager.getInstance(this); // Thay đổi ở đây

        // Lấy dữ liệu từ cơ sở dữ liệu và cập nhật ListView
        loadDataFromDatabase();
    }

    private void loadDataFromDatabase() {
        Cursor cursor = null;
        try {
            cursor = database.GetData("SELECT * FROM BaiBao"); // Thay đổi ở đây
            arrayListBaiBao.clear();
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
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }





    public void xemchitiet() {
        lvBaiBao.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final CharSequence[] items = {"Xem chi tiết", "Sửa bài báo hoặc Xóa bài báo"};
                AlertDialog.Builder builder = new AlertDialog.Builder(TheThaoActivity.this);
                builder.setTitle("Chọn hành động");
                builder.setItems(items, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        BaiBao baibao = (BaiBao) parent.getItemAtPosition(position);
                        // Nén hình ảnh trước khi chuyển
                        Bitmap bitmap = BitmapFactory.decodeByteArray(baibao.getAnhBaiBao(), 0, baibao.getAnhBaiBao().length);
                        ByteArrayOutputStream stream = new ByteArrayOutputStream();
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, stream);
                        byte[] byteArray = stream.toByteArray();
                        // Tạo Intent để mở Activity mới
                        switch (item) {
                            case 0: // Xem chi tiết

                                Intent intent = new Intent(TheThaoActivity.this, DetailActivity.class);
                                // Truyền dữ liệu sang Activity mới
                                intent.putExtra("idBaiBao", baibao.getIdBaiBao());
                                intent.putExtra("TieuDe", baibao.getTenBaiBao());
                                intent.putExtra("NoiDung", baibao.getNdBaiBao());
                                intent.putExtra("AnhBaiBao", byteArray); // Chuyển hình ảnh đã nén
                                // Khởi chạy Activity mới
                                startActivity(intent);
                                break;
                            case 1: // Sửa bài báo
                                // Mã để sửa bài báo
                                Intent intent1 = new Intent(TheThaoActivity.this, SuavaXoaActivity.class);
                                intent1.putExtra("idBaiBao", baibao.getIdBaiBao());
                                intent1.putExtra("TieuDe", baibao.getTenBaiBao());
                                intent1.putExtra("NoiDung", baibao.getNdBaiBao());
                                intent1.putExtra("AnhBaiBao", byteArray); // Chuyển hình ảnh đã nén
                                startActivity(intent1);
                                break;
                        }
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();
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
        imbBack = findViewById(R.id.imbBack);

    }

    @NonNull
    private  View.OnClickListener NhanVaoNutBack() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TheThaoActivity.this, MainActivity.class);
                startActivity(intent);
                // Kết thúc (finish) Activity hiện tại nếu bạn muốn
                finish();
            }
        };
    }

}