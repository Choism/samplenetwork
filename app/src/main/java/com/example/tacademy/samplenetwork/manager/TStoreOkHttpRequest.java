package com.example.tacademy.samplenetwork.manager;


import com.example.tacademy.samplenetwork.autodata.TStore;
import com.example.tacademy.samplenetwork.autodata.TStoreResult;
import com.google.gson.Gson;

import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.ResponseBody;

/**
 * Created by Administrator on 2016-08-09.
 */
public class TStoreOkHttpRequest extends OkHttpRequest<TStore> {
    Request request;

    public static final String SORT_ACCURACY = "R";
    public static final String SORT_LATEST = "L";
    public static final String SORT_DOWNLOAD = "D";

    public TStoreOkHttpRequest(String keyword) {
        this(keyword, 1, 10, SORT_LATEST);
    }

    public TStoreOkHttpRequest(String keyword, int page, int count, String sort) {
        HttpUrl url = new HttpUrl.Builder()
                .scheme("http")
                .host("apis.skplanetx.com")
                .addPathSegment("tstore")
                .addPathSegment("products")
                .addQueryParameter("version","1")
                .addQueryParameter("page","" + page)
                .addQueryParameter("count","" + count)
                .addQueryParameter("searchKeyword", keyword)
                .addQueryParameter("order",sort)
                .build();

        request = new Request.Builder()
                .url(url)
                .header("Accept", "application/json")
                .header("appKey","2bc7afe3-fc89-3125-b699-b9fb7cfe2fae")
                .build();
    }

    @Override
    public Request getRequest() {
        return request;
    }


    @Override
    protected TStore parse(ResponseBody body) {
        Gson gson = new Gson();
        TStoreResult result = gson.fromJson(body.charStream(), TStoreResult.class);
        return result.getTstore();
    }
}
