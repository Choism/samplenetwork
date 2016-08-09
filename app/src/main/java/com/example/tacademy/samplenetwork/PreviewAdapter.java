package com.example.tacademy.samplenetwork;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by Administrator on 2016-08-08.
 */
public class PreviewAdapter extends RecyclerView.Adapter<PreviewHolder> {
    String[] previewImages;

    public void setImages(String[] previewImages) {
        this.previewImages = previewImages;
    }

    @Override
    public PreviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ImageView imageView = new ImageView(parent.getContext());
        int width = parent.getContext().getResources().getDimensionPixelSize(R.dimen.preview_width);
        int height = parent.getContext().getResources().getDimensionPixelSize(R.dimen.preview_height);
        RecyclerView.LayoutParams params = new RecyclerView.LayoutParams(width, height);
        imageView.setLayoutParams(params);
        return new PreviewHolder(imageView);
    }

    @Override
    public void onBindViewHolder(PreviewHolder holder, int position) {
        holder.setImage(previewImages[position]);
    }

    @Override
    public int getItemCount() {
        return (previewImages == null)?0:previewImages.length;
    }
}
