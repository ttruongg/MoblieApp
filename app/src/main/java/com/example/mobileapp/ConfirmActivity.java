package com.example.mobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.w3c.dom.Text;

public class ConfirmActivity extends AppCompatActivity {

    String temporaryPrice ="", ShipFee = "", TotalPrice ="", PaymentMethod ="", DeliveryMeothod ="";
    Button btnFinish;
    TextView txtInfo, txtAddress, txtPaymentMetohd, textViewTemFee2, textViewTranFee2, textViewTotalFee2, textView14;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm);

        btnFinish = (Button) findViewById(R.id.buttonFinish);
        txtInfo = (TextView) findViewById(R.id.textViewDetail);
        txtAddress = (TextView) findViewById(R.id.textViewAddress);

        textView14 = (TextView) findViewById(R.id.textView14);
        txtPaymentMetohd = (TextView) findViewById(R.id.textView17);
        textViewTemFee2 = (TextView) findViewById(R.id.textViewTemFee2);
        textViewTranFee2 = (TextView) findViewById(R.id.textViewTranFee2);
        textViewTotalFee2 = (TextView) findViewById(R.id.textViewTotalFee2);

        Intent intent_Receive = getIntent();
        if (intent_Receive != null) {
            temporaryPrice = intent_Receive.getStringExtra("TemporaryPrice");
            ShipFee = intent_Receive.getStringExtra("ShipFee");
            TotalPrice = intent_Receive.getStringExtra("Total_Price");
            PaymentMethod = intent_Receive.getStringExtra("Payment_Method");
            DeliveryMeothod = intent_Receive.getStringExtra("Delevery_Method");
        }

        txtPaymentMetohd.setText(PaymentMethod);
        textViewTemFee2.setText(temporaryPrice);
        textViewTranFee2.setText(ShipFee);
        textViewTotalFee2.setText(TotalPrice);
        textView14.setText(DeliveryMeothod);



        showInfoCustomer();
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_Login = new Intent(ConfirmActivity.this, HomeActivity.class);
                LayoutInflater inflater = getLayoutInflater();
                View layout = inflater.inflate(R.layout.custom_toast_layout, null);

                Toast toast = new Toast(getApplicationContext());
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.setView(layout);
                toast.show();
                startActivity(intent_Login);
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
                            String tmp = fullname + " - " + phone;
                            String address = document.getString("Address");
                            txtInfo.setText(tmp);
                            txtAddress.setText(address);

                        }
                    }
                });
    }

}