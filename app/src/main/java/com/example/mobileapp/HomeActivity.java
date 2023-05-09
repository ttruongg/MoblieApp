package com.example.mobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {

    ImageView imgPhone;
    TextView txtPhone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        imgPhone = (ImageView) findViewById(R.id.imageView_MobileCategory);
        txtPhone = (TextView) findViewById(R.id.textView_MobileCategory) ;

        imgPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ViewpageActivity.class);
                startActivity(intent);
            }
        });

        txtPhone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ViewpageActivity.class);
                startActivity(intent);
            }
        });
    }
}