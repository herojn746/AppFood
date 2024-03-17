package com.loginform.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.loginform.Database.Database;
import com.loginform.R;



public class RegisterActivity extends AppCompatActivity {
    private static final String DB_NAME = "QuanLyQuanAn.db";
    public static String TenTK;
    TextInputEditText textUserName, textFullName, textPhoneNumber, textPassword;
    Button btnLogin, btnRegister;
    SQLiteDatabase sqLiteDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);
        textFullName = findViewById(R.id.textFullNameRegis);
        textPassword = findViewById(R.id.textPassRegis);
        textPhoneNumber = findViewById(R.id.textPhoneNumber);
        textUserName = findViewById(R.id.textUserNameRegis);
        btnRegister = findViewById(R.id.btnRegisRe);
        btnLogin = findViewById(R.id.btnLoginRegis);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        String userName = textUserName.getText().toString();
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                if(CheckIsDataAlreadyInDBorNot()==true)
                {
                    Toast.makeText(RegisterActivity.this, "Tên đăng nhập đã tồn tại!", Toast.LENGTH_SHORT).show();
                    return;
                }
                else if (checkEmpty()==true) {
                    return;

                }
                else {
                    insert();
                    Toast.makeText(RegisterActivity.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                }

            }
        });
    }

    private void insert(){
        String userName = textUserName.getText().toString();
        String password = textPassword.getText().toString();
        String fullName = textFullName.getText().toString();
        String phoneNumber = textPhoneNumber.getText().toString();
        if (userName.isEmpty() && password.isEmpty() && fullName.isEmpty() && phoneNumber.isEmpty()) {
            Toast.makeText(RegisterActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
            return;
        }

        ContentValues contentValues = new ContentValues();
        contentValues.put("TenTk", userName);
        contentValues.put("MatKhau", password);
        contentValues.put("HoTen", fullName);
        contentValues.put("SDT", phoneNumber);
        SQLiteDatabase database = Database.initDatabase(this, "QuanLyQuanAn.db");
        database.insert("NguoiDung",null, contentValues);


    }
    public boolean CheckIsDataAlreadyInDBorNot()
    {
        String userName = textUserName.getText().toString();
        SQLiteDatabase database = Database.initDatabase( this, DB_NAME);
        String Query = "Select * from NguoiDung where TenTK = '" + userName+"'";
        Cursor cursor = database.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }
    public boolean checkEmpty() {
        String userName = textUserName.getText().toString();
        String password = textPassword.getText().toString();
        String fullName = textFullName.getText().toString();
        String phoneNumber = textPhoneNumber.getText().toString();

        if (fullName.matches("")) {
            Toast.makeText(this, "Bạn chưa nhập Họ và tên!", Toast.LENGTH_SHORT).show();
            return true;

        }
        else if (phoneNumber.matches("")) {
            Toast.makeText(this, "Bạn chưa nhập số điện thoại!", Toast.LENGTH_SHORT).show();
            return true;

        }
        else if (userName.matches("")) {
            Toast.makeText(this, "Tên đăng nhập không được để trống!", Toast.LENGTH_SHORT).show();
            return true;

        }
        else if (password.matches("")) {
            Toast.makeText(this, "Mật khẩu không được để trống!", Toast.LENGTH_SHORT).show();
            return true;

        }
        else {
            return false;
        }
    }
}