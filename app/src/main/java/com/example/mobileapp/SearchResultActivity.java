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
    private EditText editSearchText;
    private ConstraintLayout constraintLayout;
    List<Product> productSearch;
    @SuppressLint("MissingInflatedId")
    @Override
//    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);
        editSearchText = findViewById(R.id.Input_Search_Home);
        constraintLayout = findViewById(R.id.searchProductLayout);
        productSearch = initListProductForSearchName();
        String searchKey = editSearchText.getText().toString();
        findProduct(searchKey);
    }
    public void findProduct(String searchKey){
        List<Product> productFound = productSearch.stream()
                .filter(product -> (product.getProductName().toLowerCase().contains(searchKey.toLowerCase())))
                .collect(Collectors.toList());
        System.out.println(productFound);
        RecyclerView rcvProduct = findViewById(R.id.rcvSearchProduct);

        ProductSearchAdapter adapter = new ProductSearchAdapter(productFound);
        rcvProduct.setAdapter(adapter);

        rcvProduct.setLayoutManager(new LinearLayoutManager(this));
    }
    public List<Product> initListProductForSearchName(){
        List<Product> products1 = new ArrayList<>();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        CollectionReference collectionProduct = db.collection("Product");
        collectionProduct.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        Product product = new Product(
                                document.getString("Picture"),
                                document.getString("ProductName"),
                                document.getString("Price"));
                        products1.add(product);
                    }
                } else {
                    Log.d("ERROR", "Error getting documents: ", task.getException());
                }
            }
        });
        return products1;
    }

}
