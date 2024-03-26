package com.example.appdoctintuc;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

public class DatabaseManager extends SQLiteOpenHelper {
    private static DatabaseManager instance;

    private DatabaseManager(Context context) {
        super(context, "csdl_Do_An.db", null, 1);
        // Khởi tạo cơ sở dữ liệu với các bảng
        initializeDatabase();
    }

    public static synchronized DatabaseManager getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseManager(context);
        }
        return instance;
    }

    private void initializeDatabase() {
        // Tạo bảng TheLoai
        getWritableDatabase().execSQL("CREATE TABLE IF NOT EXISTS TheLoai (" +
                "idLoai INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tenTheLoai TEXT);");

        // Tạo bảng BaiBao
        getWritableDatabase().execSQL("CREATE TABLE IF NOT EXISTS BaiBao (" +
                "idBaiBao INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tieudeBaiBao TEXT," +
                "noidungBaiBao TEXT," +
                "idLoai INTEGER," +
                "anhBaiBao BLOB," +
                "FOREIGN KEY (idLoai) REFERENCES TheLoai(idLoai));");

        // Tạo bảng User
        getWritableDatabase().execSQL("CREATE TABLE IF NOT EXISTS User (" +
                "idUser INTEGER PRIMARY KEY AUTOINCREMENT," +
                "tenUser TEXT," +
                "taikhoanUser TEXT," +
                "matkhauUser TEXT," +
                "emailUser TEXT," +
                "namsinhUser TEXT," +
                "gioitinhUser TEXT);");

        // Tạo bảng LuuTru
        getWritableDatabase().execSQL("CREATE TABLE IF NOT EXISTS LuuTru (" +
                "idLuuTru INTEGER PRIMARY KEY AUTOINCREMENT," +
                "idUser INTEGER," +
                "idBaiBao INTEGER," +
                "FOREIGN KEY (idUser) REFERENCES User(idUser)," +
                "FOREIGN KEY (idBaiBao) REFERENCES BaiBao(idBaiBao));");

        // Tạo bảng BinhLuan
        getWritableDatabase().execSQL("CREATE TABLE IF NOT EXISTS BinhLuan (" +
                "idBinhLuan INTEGER PRIMARY KEY AUTOINCREMENT," +
                "idUser INTEGER," +
                "idBaiBao INTEGER," +
                "noidungBinhLuan TEXT," +
                "ngayBinhLuan DATE," +
                "FOREIGN KEY (idUser) REFERENCES User(idUser)," +
                "FOREIGN KEY (idBaiBao) REFERENCES BaiBao(idBaiBao));");
    }

    // Các phương thức khác để thêm, cập nhật, xóa và truy vấn dữ liệu...

    // Phương thức để thực hiện truy vấn không trả về kết quả
    public void QueryData(String sql) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    // Phương thức để lấy dữ liệu trả về từ truy vấn
    public Cursor GetData(String sql) {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    // Phương thức để thêm bài báo mới
    public void InsertRecord(String tieudeBaiBao, String noidungBaiBao, byte[] anhBaiBao) {
        try {
            SQLiteDatabase database = getWritableDatabase();
            String sql = "INSERT INTO BaiBao (tieudeBaiBao, noidungBaiBao, anhBaiBao) VALUES (?, ?, ?)";
            SQLiteStatement statement = database.compileStatement(sql);
            statement.clearBindings();


            statement.bindString(1, tieudeBaiBao);
            statement.bindString(2, noidungBaiBao);
            statement.bindBlob(3, anhBaiBao);


            statement.executeInsert();
            database.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Phương thức để cập nhật bài báo
    public boolean updateArticle(int id, ContentValues values) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Cập nhật bài báo dựa trên id
        int result = db.update("BaiBao", values, "idBaiBao=?", new String[]{String.valueOf(id)});
        db.close();
        // Kiểm tra xem có cập nhật thành công hay không
        return result > 0;
    }

    // Phương thức để xóa bài báo
    public boolean deleteArticle(int idBaiBao) {
        SQLiteDatabase db = this.getWritableDatabase();
        // Xóa bài báo dựa trên id
        int rowsAffected = db.delete("BaiBao", "idBaiBao = ?", new String[]{String.valueOf(idBaiBao)});
        db.close();
        // Kiểm tra xem có hàng nào bị ảnh hưởng không và trả về kết quả
        return rowsAffected > 0;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Called when the database is created for the first time
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Called when the database needs to be upgraded
    }
}
