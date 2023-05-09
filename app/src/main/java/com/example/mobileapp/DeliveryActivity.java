package com.example.mobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DeliveryActivity extends AppCompatActivity {

    Button btnNextDelivery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);
        btnNextDelivery = (Button) findViewById(R.id.btnNext);
        btnNextDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DeliveryActivity.this, PaymentActivity.class);
                startActivity(intent);
            }
        });
    }
}