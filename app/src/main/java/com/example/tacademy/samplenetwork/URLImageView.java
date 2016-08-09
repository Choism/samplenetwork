package com.example.tacademy.samplenetwork;

import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.example.tacademy.samplenetwork.manager.ImageRequest;
import com.example.tacademy.samplenetwork.manager.NetworkManager;
import com.example.tacademy.samplenetwork.manager.NetworkRequest;


public class URLImageView extends ImageView {
    public URLImageView(Context context) {
        super(context);
    }

    public URLImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    ImageRequest request;
    public void setImageURL(String url) {
        if (request != null) {
            request.setCancel(true);
            request = null;
        }
        if (!TextUtils.isEmpty(url)) {
            request = new ImageRequest(url);
            NetworkManager.getInstance().getNetworkData(request, new NetworkManager.OnResultListener<Bitmap>() {
                @Override
                public void onSuccess(NetworkRequest<Bitmap> request, Bitmap result) {
                    URLImageView.this.request = null;
                    setImageBitmap(result);
                }

                @Override
                public void onFail(NetworkRequest<Bitmap> request, int errorCode, String errorMessage) {
                    URLImageView.this.request = null;
                    setImageResource(R.drawable.ic_error);
                }
            });
            setImageResource(R.drawable.ic_stub);
        } else {
            setImageResource(R.drawable.ic_empty);
        }
    }
}
