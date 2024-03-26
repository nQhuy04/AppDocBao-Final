package com.example.appdoctintuc.Home;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.appdoctintuc.R;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    List<DataClass> dataList;
    MyAdapter adapter;
    DataClass androidData;
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        recyclerView = findViewById(R.id.recyclerView);
        /* searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                searchList(newText);
                return true;
            }
        }); */
        GridLayoutManager gridLayoutManager = new GridLayoutManager(HomeActivity.this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        dataList = new ArrayList<>();
        androidData = new DataClass("Thể Thao", R.string.Thể_Thao, "Java", R.drawable.thethao);
        dataList.add(androidData);
        androidData = new DataClass("Thế Giới", R.string.Thế_Giới, "Kotlin", R.drawable.thegioi);
        dataList.add(androidData);
        androidData = new DataClass("Thời Sự", R.string.Thời_Sự, "Java", R.drawable.thoisu);
        dataList.add(androidData);
        androidData = new DataClass("Sức Khỏe", R.string.Sức_Khỏe, "Kotlin", R.drawable.suckhoe);
        dataList.add(androidData);
        androidData = new DataClass("Khoa Học", R.string.Khoa_Học, "Java", R.drawable.khoahoc);
        dataList.add(androidData);
        androidData = new DataClass("Kinh Doanh", R.string.Kinh_Doanh, "Java", R.drawable.kinhdoanh);
        dataList.add(androidData);
        androidData = new DataClass("Giáo Dục", R.string.Giáo_Dục, "Java", R.drawable.giaoduc);
        dataList.add(androidData);
        adapter = new MyAdapter(HomeActivity.this, dataList);
        recyclerView.setAdapter(adapter);
    }
    private void searchList(String text){
        List<DataClass> dataSearchList = new ArrayList<>();
        for (DataClass data : dataList){
            if (data.getDataTitle().toLowerCase().contains(text.toLowerCase())) {
                dataSearchList.add(data);
            }
        }
        if (dataSearchList.isEmpty()){
            Toast.makeText(this, "Not Found", Toast.LENGTH_SHORT).show();
        } else {
            adapter.setSearchList(dataSearchList);
        }

    }
}