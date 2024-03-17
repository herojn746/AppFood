package com.loginform.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.loginform.R;

public class MainActivity extends AppCompatActivity {
    ImageView imageView;
    TextView textTitle, textCreator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        addControl();
        addAnimate();
    }

    private void addAnimate() {
        imageView.animate().alpha(0f).setDuration(0);
        textTitle.animate().alpha(0f).setDuration(0);
        textCreator.animate().alpha(0f).setDuration(0);
        imageView.animate().alpha(1f).setDuration(1000).setListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                textTitle.animate().alpha(1f).setDuration(800);
                textCreator.animate().alpha(1f).setDuration(800);
            }
        });
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },3000);
    }

    private void addControl() {
        imageView = findViewById(R.id.imgLogo);
        textTitle = findViewById(R.id.txtfoodStore);
        textCreator = findViewById(R.id.txtCreator);
    }
}