package com.leathersoft.parleo.activity.events;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.leathersoft.parleo.R;

public class EventListAdapter extends RecyclerView.Adapter<EventViewHolder> {


    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(viewGroup.getContext(),R.layout.event_item_view,null);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder eventViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
