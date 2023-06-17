package com.example.mobileapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.Dialog;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ProductDetailActivity extends AppCompatActivity {


    Button btnBuyNow, btnAddToCart;

    ImageView Image;
    TextView txtPrice, txtProductName, txtDescription;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_product_detail);
        btnBuyNow = (Button) findViewById(R.id.button_buy_now);
        btnAddToCart = (Button) findViewById(R.id.button_add_to_cart);

        Image = (ImageView) findViewById(R.id.image_product);
        txtPrice = (TextView) findViewById(R.id.text_view_product_price);
        txtProductName = (TextView) findViewById(R.id.text_view_product_name);
        txtDescription = (TextView) findViewById(R.id.text_view_product_title);

        ShowDetail("Laptop Apple MacBook Air");

        btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferences = getSharedPreferences("UserEmail", MODE_PRIVATE);
                String userEmail = preferences.getString("User_Email", "");

                String productName = txtProductName.getText().toString();
                String price = txtPrice.getText().toString();

                FirebaseFirestore db = FirebaseFirestore.getInstance();
                CollectionReference collectionProduct = db.collection("Product");

                Query query = collectionProduct.whereEqualTo("ProductName", productName);
                query.get()
                        .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                            @Override
                            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                    String ProductId = documentSnapshot.getId();
                                    // insert data into Cart
                                    CollectionReference CartCollection = db.collection("Cart");










                                    Map<String, Object> Cart = new HashMap<>();
                                    Cart.put("Product_ID", ProductId);
                                    Cart.put("ProductName",productName);
                                    Cart.put("Picture",1);
                                    Cart.put("Price",price);
                                    Cart.put("Quantity", 1);
                                    Cart.put("Email", userEmail);

                                    db.collection("Cart").add(Cart);

                                    LayoutInflater inflater = getLayoutInflater();
                                    View layout = inflater.inflate(R.layout.custom_toast_layout, null);

                                    Toast toast = new Toast(getApplicationContext());
                                    toast.setDuration(Toast.LENGTH_SHORT);
                                    toast.setView(layout);
                                    toast.show();

                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Xử lý khi không thể lấy dữ liệu từ Firestore
                            }
                        });

            }
        });

        btnBuyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent_Login = new Intent(ProductDetailActivity.this, DeliveryActivity.class);
                startActivity(intent_Login);
            }
        });
    }

    private void ShowDetail(String x) {
        CollectionReference productCollection = db.collection("Product");
        productCollection.whereEqualTo("ProductName", x)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && !task.getResult().isEmpty()) {
                        DocumentSnapshot document = task.getResult().getDocuments().get(0);

                        // Kiểm tra xem trường "ProductName" có tồn tại trong tài liệu hay không
                        if (document.contains("ProductName")) {
                            String productname = document.getString("ProductName");
                            String description = document.getString("Description");
                            long price = document.getLong("Price");
                            String image = document.getString("Picture");
                            String Category = document.getString("Category");

                            InputStream inputStream = null;
                            try {
                                inputStream = getAssets().open(Category + "/" + image +".jpg");
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                            Drawable drawable = Drawable.createFromStream(inputStream, null);
                            Image.setImageDrawable(drawable);
                            txtPrice.setText(String.valueOf(price));
                            txtProductName.setText(productname);
                            txtDescription.setText(description);

                        }
                    }
                });
    }

}