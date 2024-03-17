package com.loginform.Adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.loginform.Activity.BinhLuanActivity;
import com.loginform.Activity.EateryMainActivity;
import com.loginform.Activity.LoginActivity;
import com.loginform.Class.BinhLuan;
import com.loginform.Database.Database;
import com.loginform.Database.MyDbHelper;
import com.loginform.R;

import java.util.ArrayList;

public class AdapterLSBL extends BaseAdapter {
    Context context;
    ArrayList<BinhLuan> list;
    MyDbHelper dbHelper;
    int id;
    private static final String DB_NAME = "QuanLyQuanAn.db";
    public AdapterLSBL(Context context, ArrayList<BinhLuan> list) {
        this.context = context;
        this.list = list;

    }
    //tạo nay dùng bên adapterDanhGia
    public AdapterLSBL(Context context, ArrayList<BinhLuan> list, MyDbHelper dbHelper) {
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View getView(int position, View convertview, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row=inflater.inflate(R.layout.listview_lichsubinhluan,null);
        ImageView imgHinhDG=(ImageView) row.findViewById(R.id.imgHinhDG1);
        TextView txtTenND=(TextView) row.findViewById(R.id.txtTenND1);
        TextView txtSaoDG=(TextView)row.findViewById(R.id.txtSaoDG1);
        TextView txtNoiDung=(TextView) row.findViewById(R.id.txtNoiDung1);
        Button btnXoa;
        Button btnSua;
        btnXoa = (Button) row.findViewById(R.id.btnXoa);
        btnSua= (Button) row.findViewById(R.id.btnSua);
        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setIcon(android.R.drawable.ic_delete);
                builder.setTitle("Xác nhận xóa");
                builder.setMessage("Bạn có chắc chắn muốn xóa bình luận này?");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SQLiteDatabase database = Database.initDatabase2(context, DB_NAME);
                        Cursor cursor = database.rawQuery("select * from DanhGia where MaND = '" + LoginActivity.MaND + "'", null);



                        for (int i = 0; i < cursor.getCount(); i++) {
                            cursor.moveToPosition(i);
                            int idDG = cursor.getInt(0);
                            id = idDG;
                        }
                        database.delete("DanhGia", "MaDG = ?", new String[]{id + ""});
                        list.remove(position);
                        notifyDataSetChanged();//load lại
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

//        btnSua.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(context);
//
//                SQLiteDatabase sqLiteDatabase = Database.initDatabase2(context,DB_NAME);
//
//            }
//        });

        final BinhLuan binhLuan = list.get(position);
        txtNoiDung.setText(binhLuan.ndDG+"");
        //txtTenND.setText(LoginActivity.HoTen);
        //ten nguoi cmt
        MyDbHelper dbHelper = new MyDbHelper(context);
        AdapterLSBL adapter = new AdapterLSBL(context, list, dbHelper);
        txtTenND.setText(dbHelper.getTenND(binhLuan.maND));
        txtSaoDG.setText(String.valueOf(binhLuan.saoDG)+" Sao");

        byte[] hinhAnh = binhLuan.getHinhAnh();
        if (hinhAnh != null) {
            Glide.with(context)
                    .load(hinhAnh)
                    .into(imgHinhDG);
        } else {
            // xử lý trường hợp hinhAnh là null
        }
        return row;
    }




}
