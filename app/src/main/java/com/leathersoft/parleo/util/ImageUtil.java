package com.leathersoft.parleo.util;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class ImageUtil {
    private ImageUtil() {
    }

    public static void setImage(String path, ImageView imageView, int placeholderId){
        Picasso.get()
                .load(path)
                .fit()
                .centerCrop()
                .placeholder(placeholderId)
                .error(placeholderId)
                .into(imageView);


    }
}
