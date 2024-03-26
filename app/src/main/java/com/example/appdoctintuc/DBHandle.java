package com.example.appdoctintuc;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;
public class DBHandle extends SQLiteOpenHelper {
    public DBHandle(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    //tạo hàm truy vấn create, update, delete
    public void QueryData(String sql) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public Cursor GetData(String sql) {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }

    public void InsertRecord(String tieudeBaiBao, String noidungBaiBao, byte[] anhBaiBao) {
        try {
            SQLiteDatabase database = getWritableDatabase();
            String sql = "INSERT INTO BaiBao (tieudeBaiBao, noidungBaiBao, anhBaiBao) VALUES (?, ?, ?)";
            SQLiteStatement statement = database.compileStatement(sql);
            statement.clearBindings();

            // Bind the values to the statement
            statement.bindString(1, tieudeBaiBao);
            statement.bindString(2, noidungBaiBao);
            statement.bindBlob(3, anhBaiBao);

            // Execute the insert statement
            statement.executeInsert();
        }

        catch (Exception e){
            e.printStackTrace();
        }
    }
}
