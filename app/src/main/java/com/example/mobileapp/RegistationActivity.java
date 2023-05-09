package com.example.mobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class RegistationActivity extends AppCompatActivity {

    public FloatingActionButton btnBack;
    public TextView txtSignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registation);
        btnBack = (FloatingActionButton) findViewById(R.id.floatingActionButton_Back);
        btnBack.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        startActivity(new Intent(RegistationActivity.this, LoginActivity.class));
                    }
                }
        );
        txtSignIn = (TextView) findViewById(R.id.TextView_SignIn);
        txtSignIn.setOnClickListener(
                new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        Intent intent_Login = new Intent(RegistationActivity.this, LoginActivity.class);
                        startActivity(intent_Login);
                    }
                }
        );
    }
}