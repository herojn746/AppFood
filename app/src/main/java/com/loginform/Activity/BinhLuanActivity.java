package com.loginform.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.loginform.Adapter.AdapterDanhGia;

import com.loginform.Class.BinhLuan;
import com.loginform.Class.QuanAn;
import com.loginform.Database.MyDbHelper;
import com.loginform.R;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;

public class BinhLuanActivity extends AppCompatActivity {
    ImageView imgANHBL;
    ImageButton btnSend, btnUpload, btnCamera;
    EditText edtComment;
    ImageView imgComment;
    TextView userName;
    int REQUEST_CODE_CAMERA=123;
    int REQUEST_CODE_FOLDER=456;
    RatingBar ratingbar;
    int maQA=-1, maND=-1;
    MyDbHelper dbHelper;
    //
    AdapterDanhGia adapter;
    ArrayList<BinhLuan> binhLuanList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_binh_luan);
        addControl();
        //load ten len cmt

        userName.setText(LoginActivity.HoTen);

        //
        
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,REQUEST_CODE_CAMERA );
            }
        });
        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_FOLDER);
            }
        });
        dbHelper = new MyDbHelper(this); // khởi tạo đối tượng MyDbHelper




        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String TenTK = getIntent().getStringExtra("TenTK");
                String MatKhau = getIntent().getStringExtra("MatKhau");

                // chuyen data imageView-> byte[]
                BitmapDrawable bitmapDrawable=(BitmapDrawable)  imgComment.getDrawable();
                Bitmap bitmap=bitmapDrawable.getBitmap();
                ByteArrayOutputStream byteArray=new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArray);
                byte[] hinhAnh=byteArray.toByteArray();

                //thêm
                Intent intent =getIntent();
                LoginActivity lg=new LoginActivity();
                dbHelper.INSERT_BinhLuan(
                        edtComment.getText().toString().trim(),
                        ratingbar.getRating(),

                        hinhAnh,
                       // maQA =  intent.getIntExtra("MaQA", 4),
                        maQA= intent.getIntExtra("MaQA",-1),
                        //maQA= Integer.parseInt(getIntent().getStringExtra("MaQA")),
                        maND = Integer.parseInt(LoginActivity.MaND)

                        //maND = intent.getIntExtra("MaND", -1)

                );


                    Toast.makeText(BinhLuanActivity.this, "Đã thêm", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(BinhLuanActivity.this, EateryMainActivity.class));
                }


        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==REQUEST_CODE_CAMERA&& resultCode==RESULT_OK&&data!=null)
        {
            Bitmap bitmap=(Bitmap)  data.getExtras().get("data");
            imgComment.setImageBitmap(bitmap);

        }
        if(requestCode==REQUEST_CODE_FOLDER&& resultCode==RESULT_OK&&data!=null)
        {
            Uri uri=data.getData();
            try {
                InputStream inputStream=getContentResolver().openInputStream(uri);
                Bitmap bitmap= BitmapFactory.decodeStream(inputStream);
                imgComment.setImageBitmap(bitmap);
            }catch (FileNotFoundException e)
            {
                e.printStackTrace();
            }

        }

        super.onActivityResult(requestCode, resultCode, data);
    }
    private void addControl()
    {
        btnSend= (ImageButton) findViewById(R.id.btnSend);
        btnUpload=(ImageButton) findViewById(R.id.btn_Upload);
        btnCamera=(ImageButton) findViewById(R.id.btn_Camera);
        edtComment=(EditText) findViewById(R.id.comment_content);
        imgComment=(ImageView) findViewById(R.id.img_comment);
        userName=(TextView) findViewById(R.id.username);
        ratingbar=(RatingBar)findViewById(R.id.ratingBar);
        imgANHBL=(ImageView) findViewById(R.id.imgHinhDG);
    }
}