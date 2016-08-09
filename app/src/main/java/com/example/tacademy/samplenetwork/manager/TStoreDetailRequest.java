package com.example.tacademy.samplenetwork.manager;


import com.example.tacademy.samplenetwork.autodata.Product;
import com.example.tacademy.samplenetwork.autodata.TStoreProductDetail;
import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2016-08-08.
 */
public class TStoreDetailRequest extends NetworkRequest<Product> {
    private static final String TSTORE_DETAIL_URL = "http://apis.skplanetx.com/tstore/products/%s?version=1";
    String url;
    public TStoreDetailRequest(String productId) {
        url = String.format(TSTORE_DETAIL_URL, productId);
    }

    @Override
    public URL getURL() throws MalformedURLException {
        return new URL(url);
    }

    @Override
    protected void setRequestProperty(HttpURLConnection conn) {
        super.setRequestProperty(conn);
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("appKey","2bc7afe3-fc89-3125-b699-b9fb7cfe2fae");
    }

    @Override
    protected Product parse(InputStream is) {
        InputStreamReader reader = new InputStreamReader(is);
        Gson gson = new Gson();
        TStoreProductDetail result = gson.fromJson(reader, TStoreProductDetail.class);
        result.getTstore().getProduct().makePreviewUrlList();
        return result.getTstore().getProduct();
    }
}
