package com.loginform.Database;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.loginform.Adapter.EateryAdapter;
import com.loginform.Class.Eatery;
import com.loginform.Class.EateryContract;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class EateryDbHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "QuanLyQuanAn.db";
    int id = 1;
    private Context context;
    List<Eatery> eateryList;
    EateryAdapter eateryAdapter;
    private SQLiteDatabase vDatabase;


    public EateryDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
        if (!checkDataBase()) {
            copyDB();
        }
        vDatabase = this.getWritableDatabase();
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
    }
    public List<Eatery> getAllEatery() {
        List<Eatery> foodList = new ArrayList<>();
        // Lấy toàn bộ dữ liệu từ bảng Food
        String selectQuery = "SELECT * FROM QuanAn";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Eatery food = new Eatery();
                food.setName(cursor.getString(1));
                food.setAddress(cursor.getString(2));
                food.setStarRating(cursor.getFloat(4));
                food.setDescription(cursor.getString(5));
                food.setImage(cursor.getBlob(6));
                food.setMaQA(cursor.getInt(0));
                foodList.add(food);

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return foodList;
    }

    public List<Eatery> searchEateryByName(String query) {
        List<Eatery> eateryList = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + EateryContract.FoodEntry.TABLE_NAME + " WHERE " + EateryContract.FoodEntry.COLUMN_NAME + " LIKE '%" + query + "%'", null);
        if (cursor.moveToFirst()) {
            do {
                Eatery food = new Eatery();
                food.setName(cursor.getString(1));
                food.setAddress(cursor.getString(2));
                food.setStarRating(cursor.getFloat(4));
                food.setDescription(cursor.getString(5));
                food.setImage(cursor.getBlob(6));
                eateryList.add(food);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return eateryList;
    }
    private boolean checkDataBase() {
        File db = new File(context.getDatabasePath(DATABASE_NAME).getPath());
        if (db.exists()) return true;
        File dbdir = db.getParentFile();
        if (!dbdir.exists()) {
            db.getParentFile().mkdirs();
            dbdir.mkdirs();
        }
        return false;
    }
    public void copyDB() throws SQLiteException {
        try {
            InputStream myInput = context.getAssets().open(DATABASE_NAME);
            String outputFileName = context.getDatabasePath(DATABASE_NAME).getPath();
            OutputStream myOutput = new FileOutputStream(outputFileName);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = myInput.read(buffer)) > 0) {
                myOutput.write(buffer, 0, length);
            }
            myOutput.flush();
            myOutput.close();
            myInput.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + EateryContract.FoodEntry.TABLE_NAME);
        onCreate(db);
    }
}

