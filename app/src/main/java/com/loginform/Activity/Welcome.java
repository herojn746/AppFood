package com.loginform.Activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;
import com.loginform.Database.Database;
import com.loginform.R;

public class Welcome extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public static Welcome newInstance(String param1, String param2) {
        Welcome fragment = new Welcome();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_welcome, container, false);
    }
    int ma;
    String hoten, sdt, tentk, matkhau;
    private static final String DB_NAME = "QuanLyQuanAn.db";
    TextInputEditText userName, sdtUser, id, userLogin, userPass;
    Button btnChange, btnLogout;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getContext() == null) {
            return;
        }
        userName = view.findViewById(R.id.txtNameWC);
        sdtUser = view.findViewById(R.id.txtPhoneNumber);
        userLogin = view.findViewById(R.id.txtTK);
        id = view.findViewById(R.id.txtID);
        id.setEnabled(false);
        userLogin.setEnabled(false);
        userPass = view.findViewById(R.id.txtMK);
        SQLiteDatabase db = Database.initDatabase(getActivity(), DB_NAME);
        Cursor cursor = db.rawQuery("select * from NguoiDung where MaND = '"+LoginActivity.MaND+"'",null);
        if (cursor.moveToFirst()) {
             ma = cursor.getInt(0);
            tentk = cursor.getString(1);
            matkhau = cursor.getString(2);
             hoten = cursor.getString(3);
             sdt = cursor.getString(4);
        }
        sdtUser.setText(sdt);
        id.setText(ma+"");
        userName.setText(hoten);
        userLogin.setText(tentk);
        userPass.setText(matkhau);
        btnChange =view.findViewById(R.id.btnUpdate);
        btnChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ten = userName.getText().toString();
                String phone = sdtUser.getText().toString();
                String mk = userPass.getText().toString();
                ContentValues cv = new ContentValues();
                cv.put("HoTen",ten);
                cv.put("SDT", phone);
                cv.put("MatKhau",mk);
                db.update("NguoiDung", cv, "MaND =?", new String[]{ma+""});
                Toast.makeText(getActivity(), "Đổi thành công!", Toast.LENGTH_SHORT).show();

            }
        });
        btnLogout = view.findViewById(R.id.btnLogout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}