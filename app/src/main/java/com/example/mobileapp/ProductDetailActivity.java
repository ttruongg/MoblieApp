package com.example.mobileapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.Dialog;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.IOException;
import java.io.InputStream;

public class ProductDetailActivity extends AppCompatActivity {


    Button btnBuyNow;

    ImageView Image;
    TextView Price, ProductName, Description;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_product_detail);
        btnBuyNow = (Button) findViewById(R.id.button_buy_now);

        Image = (ImageView) findViewById(R.id.image_product);
        Price = (TextView) findViewById(R.id.text_view_product_price);
        ProductName = (TextView) findViewById(R.id.text_view_product_name);
        Description = (TextView) findViewById(R.id.text_view_product_title);

        ShowDetail("Laptop Apple MacBook Air");


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

                            /*String imagePath = "/res/image/1.jpg"; // Ví dụ: "/sdcard/images/image_file_name.jpg"
                            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
                            Image.setImageBitmap(bitmap);*/
                            Price.setText(String.valueOf(price));
                            ProductName.setText(productname);
                            Description.setText(description);

                        }
                    }
                });
    }
}