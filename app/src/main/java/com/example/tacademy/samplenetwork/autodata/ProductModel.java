package com.example.tacademy.samplenetwork.autodata;

public class ProductModel {
    private String modelName;
    private String modelCode;

    public String getModelName() {
        return this.modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getModelCode() {
        return this.modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    @Override
    public String toString() {
        return modelName;
    }
}
