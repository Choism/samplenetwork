package com.example.tacademy.samplenetwork.manager;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Administrator on 2016-08-08.
 */
public class ImageRequest extends NetworkRequest<Bitmap> {
    String url;
    public ImageRequest(String url) {
        this.url = url;
    }

    @Override
    public URL getURL() throws MalformedURLException {
        return new URL(url);
    }

    @Override
    protected Bitmap parse(InputStream is) {
//        BitmapFactory.Options opts = new BitmapFactory.Options();
//        opts.inJustDecodeBounds = true;
//        Rect outrect =new Rect();
//        BitmapFactory.decodeStream(is, outrect, opts);
//        opts.inSampleSize = 2;

//        Bitmap bm = BitmapFactory.decodeStream(is, null, opts);
        Bitmap bm = BitmapFactory.decodeStream(is);
        return bm;
    }
}
