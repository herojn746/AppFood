package com.loginform.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.loginform.Class.NguoiDung;
import com.loginform.Database.Database;
import com.loginform.R;



public class LoginActivity extends AppCompatActivity {
    private static final String DB_NAME = "QuanLyQuanAn.db";
    public static String MaND,HoTen;

    TextInputEditText textPass, textUserName;
    Button btnRegister;
    Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);
        textPass = findViewById(R.id.textPassLogin);
        textUserName = findViewById(R.id.textUserNameLogin);
        btnRegister = findViewById(R.id.btnRegisterLog);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);

                        startActivity(intent);
                        finish();

                    }
                },1000);
            }
        });

        btnLogin = findViewById(R.id.btnLoginLog);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = textUserName.getText().toString();
                String password = textPass.getText().toString();
                boolean isExists = checkEsxists(username,password);

               if(isExists)
               {
                   //put qua comment
                   Intent intent1 = new Intent(LoginActivity.this, BinhLuanActivity.class);
                   intent1.putExtra("MaND", MaND);
                   intent1.putExtra("HoTen", HoTen);
                   startActivity(intent1);
               }

                if (isExists){


                    Intent intent = new Intent(LoginActivity.this, EateryMainActivity.class);
                    intent.putExtra("MaND", MaND);
                    intent.putExtra("HoTen", HoTen);
                    startActivity(intent);





                }else {
                    textPass.setText(null);
                    Toast.makeText(LoginActivity.this, "Loginfailed", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    public Boolean checkEsxists(String TenTK, String MatKhau){
        String[] columns = {"TenTK"};
        String selection = "TenTK = ? and MatKhau = ?";
        SQLiteDatabase db = Database.initDatabase(this, DB_NAME);
        String[] selectionArgs = {TenTK,MatKhau};
        Cursor cursor1 = db.rawQuery("select MaND from NguoiDung where TenTK = '"+textUserName.getText().toString()+"'",null);
        if (cursor1.moveToFirst()){
            MaND = cursor1.getString(0);
        }
        Cursor cursor2 = db.rawQuery("select HoTen from NguoiDung where TenTK = '"+textUserName.getText().toString()+"'",null);
        if (cursor2.moveToFirst()){
            HoTen = cursor2.getString(0);
        }
        Cursor cursor = db.query("NguoiDung",columns,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();
        if (count>0)
            return true;
        else
            return false;
    }


}