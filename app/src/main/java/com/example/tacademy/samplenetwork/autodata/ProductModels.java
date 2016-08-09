package com.example.tacademy.samplenetwork.autodata;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductModels {
    @SerializedName("model")
    private List<ProductModel> modelList;

    public List<ProductModel> getModelList() {
        return this.modelList;
    }

    public void setModelList(List<ProductModel> modelList) {
        this.modelList = modelList;
    }
}
