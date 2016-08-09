package com.example.tacademy.samplenetwork;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


import com.example.tacademy.samplenetwork.autodata.Product;
import com.example.tacademy.samplenetwork.autodata.ProductView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Tacademy on 2016-08-05.
 */
public class ProductAdapter extends BaseAdapter {
    List<Product> items = new ArrayList<>();
    public void addAll(Product[] items) {
        this.items.addAll(Arrays.asList(items));
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ProductView view;
        if (convertView == null) {
            view = new ProductView(parent.getContext());
        } else {
            view = (ProductView)convertView;
        }
        view.setProduct(items.get(position));
        return view;
    }

    public void addAll(List<Product> items) {
        this.items.addAll(items);
        notifyDataSetChanged();
    }
}
