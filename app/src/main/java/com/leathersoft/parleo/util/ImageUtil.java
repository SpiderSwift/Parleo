package com.leathersoft.parleo.util;

import android.widget.ImageView;

import com.leathersoft.parleo.R;
import com.squareup.picasso.Picasso;

public class ImageUtil {
    private ImageUtil() {
    }

    public static void setImage(String path, ImageView imageView, int placeholderId){
        Picasso.get()
                .load(path)
                .placeholder(placeholderId)
                .into(imageView);
    }
}
