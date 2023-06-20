package com.example.mobileapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileapp.Adapter.ProductSearchAdapter;
import com.example.mobileapp.fragment.HomeFragment;
import com.example.mobileapp.model.Product;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SearchResultActivity extends AppCompatActivity {
    public static List<Product> productFound = HomeFragment.productFound;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actiity_search_result);

        RecyclerView rcvProduct = findViewById(R.id.rcvSearchProduct);
        ProductSearchAdapter adapter = new ProductSearchAdapter(productFound);
        rcvProduct.setAdapter(adapter);
        rcvProduct.setLayoutManager(new LinearLayoutManager(this));
    }

}
