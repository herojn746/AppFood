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


import com.loginform.Class.MonAn;
import com.loginform.R;

import java.util.ArrayList;

public class AdapterMonAn extends BaseAdapter {
    Context context;
    ArrayList<MonAn> list;

    public AdapterMonAn(Context context, ArrayList<MonAn> list) {
        this.context = context;
        this.list = list;
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
        View row=inflater.inflate(R.layout.listview_monan,null);
        ImageView imgHinhDaiDien=(ImageView) row.findViewById(R.id.imgHinhMonAn);
        TextView txtTenQuan=(TextView) row.findViewById(R.id.txtTenMonAn);
        TextView txtGia=(TextView)row.findViewById(R.id.txtGia);
        final MonAn monAn = list.get(position);
        txtGia.setText(monAn.gia+"");
        txtTenQuan.setText(monAn.tenMA);
        Bitmap bmHinhDaiDien = BitmapFactory.decodeByteArray(monAn.HinhAnh,0,monAn.HinhAnh.length);
        imgHinhDaiDien.setImageBitmap(bmHinhDaiDien);
        return row;
    }

}
