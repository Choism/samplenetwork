package com.example.tacademy.samplenetwork.autodata;

import com.google.gson.annotations.SerializedName;

public class Product {
    private String detailDescription;
    private ProductModels models;
    private String charge;
    @SerializedName("previewUrl")
    private String previewUrls;
    private String[] previewUrlList;

    private String productId;
    private String categoryPath;
    private String description;
    private String tinyUrl;
    private String categoryCode;
    private String categoryName;
    private String platform;
    private String score;
    private String developerId;
    private String osVersion;
    private String webUrl;
    private String name;
    private String develperName;
    private String downloadCount;
    private String thumbnailUrl;

    public void makePreviewUrlList() {
        previewUrlList = previewUrls.split(";");
    }

    public String[] getPreviewUrlList() {
        return previewUrlList;
    }

    public String getDetailDescription() {
        return this.detailDescription;
    }

    public void setDetailDescription(String detailDescription) {
        this.detailDescription = detailDescription;
    }

    public ProductModels getModels() {
        return this.models;
    }

    public void setModels(ProductModels models) {
        this.models = models;
    }

    public String getCharge() {
        return this.charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getPreviewUrl() {
        return this.previewUrls;
    }

    public void setPreviewUrl(String previewUrl) {
        this.previewUrls = previewUrl;
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCategoryPath() {
        return this.categoryPath;
    }

    public void setCategoryPath(String categoryPath) {
        this.categoryPath = categoryPath;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTinyUrl() {
        return this.tinyUrl;
    }

    public void setTinyUrl(String tinyUrl) {
        this.tinyUrl = tinyUrl;
    }

    public String getCategoryCode() {
        return this.categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getPlatform() {
        return this.platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public String getScore() {
        return this.score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getDeveloperId() {
        return this.developerId;
    }

    public void setDeveloperId(String developerId) {
        this.developerId = developerId;
    }

    public String getOsVersion() {
        return this.osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getWebUrl() {
        return this.webUrl;
    }

    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDevelperName() {
        return this.develperName;
    }

    public void setDevelperName(String develperName) {
        this.develperName = develperName;
    }

    public String getDownloadCount() {
        return this.downloadCount;
    }

    public void setDownloadCount(String downloadCount) {
        this.downloadCount = downloadCount;
    }

    public String getThumbnailUrl() {
        return this.thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }
}
