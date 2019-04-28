package com.leathersoft.parleo.fragment.events;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.leathersoft.parleo.R;
import com.leathersoft.parleo.network.model.Event;
import com.leathersoft.parleo.util.ImageUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventViewHolder extends RecyclerView.ViewHolder {


    @BindView(R.id.iv_event_image)
    ImageView mEventImage;
    @BindView(R.id.tv_event_place_title)
    TextView mEventPlaceTitle;
    @BindView(R.id.tv_event_place_description)
    TextView mEventPlaceDescription;
    @BindView(R.id.iv_language_icon)
    ImageView mLanguageIcon;

    public EventViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void bind(Event event){

        ImageUtil.setImage(event.getImage(),mEventImage,R.drawable.cafe_placeholder);
        mEventPlaceTitle.setText(event.getName());
        mEventPlaceDescription.setText(event.getDescription());
    }

}
