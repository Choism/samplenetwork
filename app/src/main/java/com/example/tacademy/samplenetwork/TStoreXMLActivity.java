package com.example.tacademy.samplenetwork;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;


import com.begentgroup.xmlparser.XMLParser;
import com.example.tacademy.samplenetwork.autodata.Product;
import com.example.tacademy.samplenetwork.autodata.TStore;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnItemClick;

public class TStoreXMLActivity extends AppCompatActivity {

    @BindView(R.id.edit_input)
    EditText keywordView;

    @BindView(R.id.list_tstore)
    ListView listView;

    //    ArrayAdapter<Product> mAdapter;
    ProductAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tstore);

        ButterKnife.bind(this);

//        mAdapter = new ArrayAdapter<Product>(this, android.R.layout.simple_list_item_1);
        mAdapter = new ProductAdapter();

        listView.setAdapter(mAdapter);


    }

    @OnClick(R.id.btn_search)
    public void onSerach(View view) {
        String keyword = keywordView.getText().toString();
        if (!TextUtils.isEmpty(keyword)) {
            new TStoreSearchTask().execute(keyword);
        }
    }

    @OnItemClick(R.id.list_tstore)
    public void onProductItemClick(AdapterView<?> parent, View view, int position, long id) {
        Product p = (Product)listView.getItemAtPosition(position);
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(p.getTinyUrl()));
        startActivity(intent);
    }


    private static final String TSTORE_URL = "http://apis.skplanetx.com/tstore/products?version=1&page=1&count=10&searchKeyword=%s&order=L";
    private static final String SORT_ACCURACY = "R";
    private static final String SORT_LATEST = "L";
    private static final String SORT_DOWNLOAD = "D";

    class TStoreSearchTask extends AsyncTask<String,Integer,TStore> {

        @Override
        protected TStore doInBackground(String... strings) {
            String keyword = strings[0];
            try {
                String urlText = String.format(TSTORE_URL, URLEncoder.encode(keyword, "utf-8"));
                URL url = new URL(urlText);
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                conn.setRequestProperty("Accept", "application/xml");
                conn.setRequestProperty("appKey","2bc7afe3-fc89-3125-b699-b9fb7cfe2fae");
                int code = conn.getResponseCode();
                if (code >= 200 && code < 300) {
                    InputStream is = conn.getInputStream();
                    XMLParser parser = new XMLParser();
                    TStore result = parser.fromXml(is, "tstore", TStore.class);
                    return result;
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(TStore s) {
            super.onPostExecute(s);
            if (s != null) {
                mAdapter.addAll(s.getProducts().getProductList());
            } else {
                Toast.makeText(TStoreXMLActivity.this, "error", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
