package com.example.tacademy.samplenetwork;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tacademy.samplenetwork.autodata.Product;
import com.example.tacademy.samplenetwork.autodata.ProductModel;
import com.example.tacademy.samplenetwork.manager.NetworkManager;
import com.example.tacademy.samplenetwork.manager.NetworkRequest;
import com.example.tacademy.samplenetwork.manager.TStoreDetailRequest;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_PRODUCT_ID = "productId";

    @BindView(R.id.image_thumb)
    ImageView thumbnailView;

    @BindView(R.id.text_title)
    TextView titleView;

    @BindView(R.id.text_score)
    TextView scoreView;

    @BindView(R.id.text_download)
    TextView downloadView;

    @BindView(R.id.text_detailDescription)
    TextView detailView;

    @BindView(R.id.text_description)
    TextView descriptionView;

    @BindView(R.id.rv_preview)
    RecyclerView previewView;

    PreviewAdapter mAdapter;
    Product product;

    @BindView(R.id.spinner_model)
    Spinner modelView;

    ArrayAdapter<ProductModel> mModelAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ButterKnife.bind(this);

        mModelAdapter = new ArrayAdapter<ProductModel>(this, android.R.layout.simple_spinner_item);
        mModelAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        modelView.setAdapter(mModelAdapter);

        mAdapter = new PreviewAdapter();
        previewView.setAdapter(mAdapter);
        previewView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        final String productId = getIntent().getStringExtra(EXTRA_PRODUCT_ID);

        TStoreDetailRequest request = new TStoreDetailRequest(productId);
        NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<Product>() {
            @Override
            public void onSuccess(NetworkRequest<Product> request, Product result) {
                product = result;
                titleView.setText(result.getName());
                scoreView.setText(""+result.getScore());
                downloadView.setText("" + result.getDownloadCount());
                detailView.setText(result.getDetailDescription());
                descriptionView.setText(result.getDescription());
                mAdapter.setImages(result.getPreviewUrlList());
                mModelAdapter.addAll(result.getModels().getModelList());
            }

            @Override
            public void onFail(NetworkRequest<Product> request, int errorCode, String errorMessage) {
                Toast.makeText(DetailActivity.this, "fail", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.text_link)
    public void onLinkClick() {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(product.getTinyUrl()));
        startActivity(intent);
    }
}
