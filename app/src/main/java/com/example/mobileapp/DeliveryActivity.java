package com.example.mobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;

public class DeliveryActivity extends AppCompatActivity {

    Button btnNextDelivery;
    TextView txtShippingFee;
    EditText edtName, edtPhone, edtAddress;
    RadioGroup RdoShipping;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery);
        btnNextDelivery = (Button) findViewById(R.id.btnNext);
        txtShippingFee = (TextView) findViewById(R.id.txtShippingFee);

        edtName = (EditText) findViewById(R.id.editFullName);
        edtPhone = (EditText) findViewById(R.id.editPhone);
        edtAddress =(EditText) findViewById(R.id.editAdress);
        RdoShipping = (RadioGroup) findViewById(R.id.RdoShipping);

        // function
        showInfoCustomer();



        btnNextDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DeliveryActivity.this, PaymentActivity.class);
                startActivity(intent);
            }
        });


        RdoShipping.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.radioFast:
                        txtShippingFee.setText("150000");
                        break;
                    case R.id.radioVeryFast:
                        txtShippingFee.setText("200000");
                        break;
                    case R.id.radioHoaToc:
                        txtShippingFee.setText("300000");
                        break;




                }
            }
        });
    }

    private void showInfoCustomer(){
        SharedPreferences preferences = getSharedPreferences("UserEmail", MODE_PRIVATE);
        String userEmail = preferences.getString("User_Email", "");

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference collectionUser = db.collection("Users");

        collectionUser.whereEqualTo("Email", userEmail)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && !task.getResult().isEmpty()) {
                        DocumentSnapshot document = task.getResult().getDocuments().get(0);

                        // Kiểm tra xem trường "ProductName" có tồn tại trong tài liệu hay không
                        if (document.contains("Email")) {
                            String fullname = document.getString("FullName");
                            String phone = document.getString("Phone");
                            String address = document.getString("Address");


                            edtName.setText(fullname);
                            edtPhone.setText(phone);
                            edtAddress.setText(address);

                        }
                    }
                });














    }
}