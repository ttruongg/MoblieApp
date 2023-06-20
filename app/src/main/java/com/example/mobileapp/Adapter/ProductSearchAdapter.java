package com.example.mobileapp.Adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileapp.R;
import com.example.mobileapp.model.Product;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ProductSearchAdapter extends RecyclerView.Adapter<ProductSearchAdapter.ViewHolder> {
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView = inflater.inflate(R.layout.item_product, parent, false);

        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ProductSearchAdapter.ViewHolder viewHolder, int position) {
        Product product = productList.get(position);

        TextView textViewProductName = viewHolder.productNameTextView;
        textViewProductName.setText(product.getProductName());

        ImageView imageView = viewHolder.productImageView;
        InputStream inputStream = null;
        try {
            inputStream = context.getAssets().open("/" + product.getImage() +".jpg");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Drawable drawable = Drawable.createFromStream(inputStream, null);
        imageView.setImageDrawable(drawable);

        TextView textViewProductPrice = viewHolder.productPriceTexView;
        textViewProductPrice.setText(product.getPrice());

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView productNameTextView;
        public ImageView productImageView;
        public TextView productPriceTexView;
        public ViewHolder(View view){
            super(view);
            productNameTextView = view.findViewById(R.id.txtproductName);
            productImageView = view.findViewById(R.id.imgProduct);
            productPriceTexView = view.findViewById(R.id.txtPrice);
        }
    }

    private List<Product> productList;
    private Context context;

    public ProductSearchAdapter(List<Product> products){
        productList = products;
    }

    public ProductSearchAdapter(List<Product> products, Context context){
        productList = products;
        context = context;
    }
}
