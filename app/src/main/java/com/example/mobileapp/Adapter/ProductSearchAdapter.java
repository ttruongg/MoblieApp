package com.example.mobileapp.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mobileapp.HomeActivity;
import com.example.mobileapp.MainActivity;
import com.example.mobileapp.model.Product;

import java.util.List;

public class ProductSearchAdapter extends BaseAdapter {
    private List<Product> products;
    public ProductSearchAdapter(List<Product> products){
        this.products = products;
    }

    @Override
    public int getCount() {
        return products.size();
    }

    @Override
    public Object getItem(int position) {
        return products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
        }
        TextView textView = convertView.findViewById(android.R.id.text1);
        textView.setText((CharSequence) products.get(position));
        return convertView;
    }

    public void filter(String searchText) {
//        products.clear();
//        if (searchText.length() == 0) {
//            products.addAll(HomeActivity.);
//        } else {
//            for (String product : MainActivity.songList) {
//                if (song.toLowerCase().contains(searchText.toLowerCase())) {
//                    songs.add(song);
//                }
//            }
//        }
        notifyDataSetChanged();
    }
}
