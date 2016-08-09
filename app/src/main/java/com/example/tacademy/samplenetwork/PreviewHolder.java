package com.example.tacademy.samplenetwork;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Administrator on 2016-08-08.
 */
public class PreviewHolder extends RecyclerView.ViewHolder {
    ImageView imageView;
    public PreviewHolder(View itemView) {
        super(itemView);
        imageView = (ImageView)itemView;
    }

    public void setImage(String url) {
        imageView.setImageResource(R.mipmap.ic_launcher);
    }

}
