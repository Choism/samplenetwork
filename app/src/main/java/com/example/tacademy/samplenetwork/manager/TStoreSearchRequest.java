package com.example.tacademy.samplenetwork.manager;

import com.example.tacademy.samplenetwork.autodata.TStore;
import com.example.tacademy.samplenetwork.autodata.TStoreResult;
import com.google.gson.Gson;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by Administrator on 2016-08-05.
 */
public class TStoreSearchRequest extends NetworkRequest<TStore> {
    private static final String TSTORE_SEARCH_URL = "http://apis.skplanetx.com/tstore/products?version=1&page=%d&count=%d&searchKeyword=%s&order=%s";
    public static final String SORT_ACCURACY = "R";
    public static final String SORT_LATEST = "L";
    public static final String SORT_DOWNLOAD = "D";

    String url;

    public TStoreSearchRequest(String keyword) {
        this(1, 10, keyword, SORT_ACCURACY);
    }

    public TStoreSearchRequest(int page, int count, String keyword) {
        this(page, count, keyword, SORT_ACCURACY);
    }

    public TStoreSearchRequest(int page, int count, String keyword, String sort) {
        try {
            url = String.format(TSTORE_SEARCH_URL, page, count, URLEncoder.encode(keyword, "utf-8"), sort);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void setRequestProperty(HttpURLConnection conn) {
        super.setRequestProperty(conn);
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("appKey","2bc7afe3-fc89-3125-b699-b9fb7cfe2fae");
    }

    @Override
    public URL getURL() throws MalformedURLException {
        return new URL(url);
    }

    @Override
    protected TStore parse(InputStream is) {
        Gson gson = new Gson();
        TStoreResult result = gson.fromJson(new InputStreamReader(is), TStoreResult.class);
        return result.getTstore();
    }
}
