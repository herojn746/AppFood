package com.loginform.Activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;



import com.loginform.Class.QuanAn;
import com.loginform.Database.Database;
import com.loginform.R;

import java.util.ArrayList;

public class DSQuanAn extends AppCompatActivity {
    final String DATABASE_NAME = "QuanLyQuanAn.db";
    SQLiteDatabase database;
    ListView listView;
    ArrayList<QuanAn> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ds_quanan);
        ActionBar actionBar;
        actionBar = getSupportActionBar();
        actionBar.hide();
        anhxa();
        readData();
    }
    private void anhxa()
    {
        listView=(ListView) findViewById(R.id.listView);
        list=new ArrayList<>();

    }
    private void readData()
    {
        database = Database.initDatabase(this, DATABASE_NAME);
        Cursor cursor = database.rawQuery("SELECT * FROM QuanAn",null);
        list.clear();
        for(int i = 0; i < cursor.getCount(); i++){
            cursor.moveToPosition(i);
            int id=cursor.getInt(0);
            String ten = cursor.getString(1);
            String diachi = cursor.getString(2);
            String sdt = cursor.getString(3);
            int saodanhgia = cursor.getInt(4);
            String gioithieu = cursor.getString(5);
            byte[] anh = cursor.getBlob(6);
            list.add(new QuanAn(id,ten,anh,diachi,sdt,saodanhgia,gioithieu));
        }

    }
}
