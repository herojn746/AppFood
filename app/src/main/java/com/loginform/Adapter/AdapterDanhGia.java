package com.loginform.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.loginform.Activity.LoginActivity;
import com.loginform.Class.BinhLuan;
import com.loginform.Class.MonAn;
import com.loginform.Class.NguoiDung;
import com.loginform.Database.MyDbHelper;
import com.loginform.R;

import java.util.ArrayList;

public class AdapterDanhGia extends BaseAdapter {
    Context context;
    ArrayList<BinhLuan> list;
    MyDbHelper dbHelper;

    public AdapterDanhGia(Context context, ArrayList<BinhLuan> list) {
        this.context = context;
        this.list = list;

    }
//tạo nay dùng bên adapterDanhGia
    public AdapterDanhGia(Context context, ArrayList<BinhLuan> list, MyDbHelper dbHelper) {
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

    @Override
    public View getView(int position, View convertview, ViewGroup parent)
    {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View row=inflater.inflate(R.layout.listview_binhluan,null);
        ImageView imgHinhDG=(ImageView) row.findViewById(R.id.imgHinhDG);
        TextView txtTenND=(TextView) row.findViewById(R.id.txtTenND);
        TextView txtSaoDG=(TextView)row.findViewById(R.id.txtSaoDG);
        TextView txtNoiDung=(TextView) row.findViewById(R.id.txtNoiDung);
        final BinhLuan binhLuan = list.get(position);
        txtNoiDung.setText(binhLuan.ndDG+"");
        //txtTenND.setText(LoginActivity.HoTen);
        //ten nguoi cmt
        MyDbHelper dbHelper = new MyDbHelper(context);
        AdapterDanhGia adapter = new AdapterDanhGia(context, list, dbHelper);
        txtTenND.setText(dbHelper.getTenND(binhLuan.maND));
        txtSaoDG.setText(String.valueOf(binhLuan.saoDG));

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


    //dbHelper = new MyDbHelper(this);

}
