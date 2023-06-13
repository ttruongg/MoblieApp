package com.example.mobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProductDetailActivity extends AppCompatActivity {


    Button btnBuyNow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_product_detail);
        btnBuyNow = (Button) findViewById(R.id.button_buy_now);
        btnBuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_Login = new Intent(ProductDetailActivity.this, DeliveryActivity.class);
                startActivity(intent_Login);
            }
        });
    }
}