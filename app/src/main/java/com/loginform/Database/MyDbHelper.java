package com.loginform.Database;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import com.loginform.Class.BinhLuan;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Scanner;



public class MyDbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "QuanLyQuanAn.db";
    private static final int DB_VERSION = 1;
    private Context context;
    public SQLiteDatabase database;



    public MyDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        InputStream inputStream = null;
        try {
            inputStream = context.getAssets().open(DB_NAME);
            Scanner scanner = new Scanner(inputStream);
            scanner.useDelimiter(";\\s*");
            while (scanner.hasNext()) {
                String sql = scanner.next();
                db.execSQL(sql);
            }
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // handle upgrade if needed
    }
    public void INSERT_BinhLuan(String noiDung, double saoDG, byte[] hinh, int maQA, int maND)
    {

        database=getWritableDatabase();
        String sql="INSERT INTO DanhGia VALUES(null,?,?,?,?,?)";
        SQLiteStatement statement=database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1,noiDung);
        statement.bindDouble(2,saoDG);
        statement.bindBlob(3,hinh);
        statement.bindLong(4,maQA);
        statement.bindLong(5,maND);

        statement.executeInsert();
    }
    public void UPDATE_BinhLuan(int maDG, String noiDung, double saoDG, byte[] hinh, int maQA, int maND)
    {
        database = getWritableDatabase();
        String sql = "UPDATE DanhGia SET NoiDung=?, SaoDG=?, Hinh=?, MaQA=?, MaND=? WHERE MaDG=?";
        SQLiteStatement statement = database.compileStatement(sql);
        statement.clearBindings();

        statement.bindString(1, noiDung);
        statement.bindDouble(2, saoDG);
        statement.bindBlob(3, hinh);
        statement.bindLong(4, maQA);
        statement.bindLong(5, maND);
        statement.bindLong(6, maDG);

        statement.executeUpdateDelete();
    }
    public void deleteBinhLuan(int maND) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM BinhLuan WHERE maND = ?", new String[] { String.valueOf(maND) });
        db.close();
    }
    public ArrayList<BinhLuan> getAllBinhLuan(int maQA) {
        ArrayList<BinhLuan> binhLuanList = new ArrayList<>();

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM DanhGia WHERE maQA = ?", new String[] { String.valueOf(maQA) });

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String noiDung = cursor.getString(1);
                int saoDG = cursor.getInt(2);
                byte[] hinh = cursor.getBlob(3);
                int maND = cursor.getInt(4);

                BinhLuan binhLuan = new BinhLuan(id, saoDG, maQA,maND , noiDung, hinh );
                binhLuanList.add(binhLuan);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return binhLuanList;
    }

    //bỏ

    public String getTenND(int maND) {
        SQLiteDatabase db = getReadableDatabase();
        String[] columns = {"HoTen"};
        String selection = "MaND" + "=?";
        String[] selectionArgs = {String.valueOf(maND)};
        Cursor cursor = db.query("NguoiDung", columns, selection, selectionArgs, null, null, null);
        String tenND = null;
        if (cursor.moveToFirst()) {
            tenND = cursor.getString(cursor.getColumnIndexOrThrow("HoTen"));
        }
        cursor.close();
        db.close();
        return tenND;
    }


}


