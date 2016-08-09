package com.example.tacademy.samplenetwork.autodata;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class Products {
    @SerializedName("product")

    @com.begentgroup.xmlparser.SerializedName("product")
    private ArrayList<Product> productList;

    public ArrayList<Product> getProductList() {
        return this.productList;
    }

    public void setProductList(ArrayList<Product> productList) {
        this.productList = productList;
    }

}
