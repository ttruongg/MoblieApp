package com.example.mobileapp.Adapter;

import android.content.ClipData;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.view.menu.MenuView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mobileapp.R;
import com.example.mobileapp.model.Cart;
import com.example.mobileapp.model.Product;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ProductAdapter  extends RecyclerView.Adapter<ProductAdapter.ViewHolder>{
    private Context context;
    private List<Product> ListProduct;


    public void setData(List<Product> listProduct ){
        this.ListProduct = listProduct;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return ListProduct.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView productImg;
        private TextView ProductName;
        private TextView Price;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            productImg = itemView.findViewById(R.id.imgProduct);
            ProductName = itemView.findViewById(R.id.txtproductName);
            Price = itemView.findViewById(R.id.txtPrice);



        }
    }









}
