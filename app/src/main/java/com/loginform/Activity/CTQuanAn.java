package com.loginform.Activity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


import com.loginform.Adapter.AdapterDanhGia;
import com.loginform.Adapter.AdapterMonAn;
import com.loginform.Class.BinhLuan;
import com.loginform.Class.MonAn;
import com.loginform.Database.Database;
import com.loginform.Database.MyDbHelper;
import com.loginform.R;

import java.util.ArrayList;

public class CTQuanAn extends AppCompatActivity {
    final String DATABASE_NAME="QuanLyQuanAn.db";
    int id = 1;
    public Context context;

    ImageView imgHinhDaiDien;
    TextView txtTenQuan,txtDiaChi,txtSDT,txtGT,txtMenu,txtChuDanhGia;
    RatingBar ratingbar;

    //
    RatingBar rtbSaoDG;

    ListView lstMonAn,lstBinhLuan;
    ArrayList<MonAn> list;
    ArrayList<BinhLuan> list2;
    AdapterMonAn adapterMonAn;
    AdapterDanhGia adapterDanhGia;
    SQLiteDatabase database1,database2;
    Button btnComment;

    ImageButton btnSend, btnUpload, btnCamera;
    EditText edtComment;
    ImageView imgComment;
///
    AdapterDanhGia adapter;
    ArrayList<BinhLuan> binhLuanList = new ArrayList<>();
    MyDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.chitiet_quanan);

        ActionBar actionBar;
        actionBar = getSupportActionBar();
        actionBar.hide();

        addControl();
        initUI();

        btnComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(CTQuanAn.this,BinhLuanActivity.class));

                Intent intent2 = new Intent(CTQuanAn.this, BinhLuanActivity.class);
                intent2.putExtra("MaQA", id);
                CTQuanAn.this.startActivity(intent2);
            }
        });
//xoa binh luận đang làm
        lstBinhLuan.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                BinhLuan binhLuan = list2.get(position);
                if (binhLuan.getMaND() == Integer.parseInt(LoginActivity.MaND)) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(CTQuanAn.this);
                    builder.setTitle("Xác nhận xóa bình luận");
                    builder.setMessage("Bạn có chắc chắn muốn xóa bình luận này?"+Integer.parseInt(LoginActivity.MaND));
                    builder.setPositiveButton("Yes",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // Xóa bình luận trong cơ sở dữ liệu
                            if (dbHelper != null) {  // Kiểm tra đối tượng dbHelper có khác null hay không
                                dbHelper.deleteBinhLuan(Integer.parseInt(LoginActivity.MaND)); // Xóa bình luận trong cơ sở dữ liệu
                            }
                            // Xóa phần tử khỏi danh sách và cập nhật lại Adapter
                            if (position < list2.size()) {
                                list2.remove(position);
                                adapterDanhGia.notifyDataSetChanged(); // Cập nhật lại Adapter
                                Toast.makeText(CTQuanAn.this, "Đã xóa bình luận", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    builder.setNegativeButton("No", null);
                    builder.show();
                } else {
                    Toast.makeText(CTQuanAn.this, "Bạn không có quyền xóa bình luận của người dùng khác", Toast.LENGTH_SHORT).show();
                }
                return true;
            }
        });




    }
    private void addControl()
    {
        rtbSaoDG = (RatingBar) findViewById(R.id.saodanhgia);
        imgHinhDaiDien=(ImageView) findViewById(R.id.imgHinhDaiDien);
        txtTenQuan=(TextView) findViewById(R.id.txttenQuan);
        lstMonAn=(ListView) findViewById(R.id.listMonAn);
        lstBinhLuan=(ListView) findViewById(R.id.listBinhLuan);
        txtDiaChi = (TextView) findViewById(R.id.txtdiachi);
        txtSDT = (TextView) findViewById(R.id.txtsdt);
        txtGT = (TextView) findViewById(R.id.txtgioithieu);
        txtMenu = (TextView) findViewById(R.id.txtmenu);
        list=new ArrayList<>();
        list2=new ArrayList<>();
        adapterMonAn = new AdapterMonAn(this,list);
        adapterDanhGia = new AdapterDanhGia(this,list2);
        lstMonAn.setAdapter(adapterMonAn);
        lstBinhLuan.setAdapter(adapterDanhGia);
        btnComment=(Button) findViewById(R.id.btnComment);
        txtChuDanhGia = (TextView) findViewById(R.id.txtchudanhgia);
        //

        btnUpload = (ImageButton) findViewById(R.id.btn_Upload);
        btnCamera = (ImageButton) findViewById(R.id.btn_Camera);
        edtComment = (EditText) findViewById(R.id.comment_content);
        imgComment = (ImageView) findViewById(R.id.img_comment);
        //userName = (TextView) findViewById(R.id.username);
        ratingbar = (RatingBar) findViewById(R.id.ratingBar);
    }
    private void initUI() {

        Intent intent =getIntent();
        id =intent.getIntExtra("MaQA",1);
        //test

        //
        SQLiteDatabase database = Database.initDatabase( this, DATABASE_NAME);
        Cursor cursor=database.rawQuery("SELECT*FROM QuanAn Where MaQA = "+id,null);
        cursor.moveToFirst();
        String ten = cursor.getString(1);
        String diachi = cursor.getString(2);
        String sdt = cursor.getString(3);
        int saodanhgia = cursor.getInt(4);
        String gioithieu = cursor.getString(5);
        byte[] anh = cursor.getBlob(6);
        Bitmap bitmap = BitmapFactory.decodeByteArray(anh, 0, anh.length);
        rtbSaoDG.setRating(saodanhgia);
        imgHinhDaiDien.setImageBitmap(bitmap);
        txtTenQuan.setText(ten);
        txtSDT.setText("Số điện thoại: "+sdt);
        txtDiaChi.setText(" Địa chỉ: "+diachi);
        txtGT.setText("Giới thiệu: "+gioithieu);
        //Hien thi ds mon an
        database1 = Database.initDatabase(this, DATABASE_NAME);
        Cursor cursor1 = database.rawQuery("SELECT * FROM MonAn Where MaQA="+id,null);
        list.clear();
        for(int i = 0; i < cursor1.getCount(); i++){
            cursor1.moveToPosition(i);
            int idMA=cursor1.getInt(0);
            String tenMA = cursor1.getString(1);
            String gia = cursor1.getString(2);
            byte[] anhMA = cursor1.getBlob(3);
            int maQA = id;
            list.add(new MonAn(idMA,maQA,tenMA,gia,anhMA));
        }
        adapterMonAn.notifyDataSetChanged();





        //Hien thi ds binh luan
        database2 = Database.initDatabase(this, DATABASE_NAME);

        Cursor cursor2 = database2.rawQuery("SELECT * FROM DanhGia Where MaQA="+id,null);
        list2.clear();
        for(int i = 0; i < cursor2.getCount(); i++){
            cursor2.moveToPosition(i);
            int idDG = cursor2.getInt(0);
            String Noidung = cursor2.getString(1);
            int saoDG = cursor2.getInt(2);


            byte[] anhBL = cursor2.getBlob(3);
            int maND = cursor2.getInt(5);

            list2.add(new BinhLuan(idDG,saoDG,id,maND,Noidung,anhBL));
        }
        adapterDanhGia.notifyDataSetChanged();
    }

    // truyen list binh luận qua bên comment


}
