package com.leathersoft.parleo.fragment.events;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class EventViewHolder extends RecyclerView.ViewHolder {

    View mView;
    public EventViewHolder(@NonNull View itemView) {
        super(itemView);
        mView = itemView;
    }

}
