package com.leathersoft.parleo.network.model;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.leathersoft.parleo.R;
import com.leathersoft.parleo.fragment.ProfileFragment;
import com.leathersoft.parleo.fragment.PushFragmentInterface;
import com.leathersoft.parleo.fragment.events.EventDetailFragment;
import com.leathersoft.parleo.fragment.events.EventViewHolder;

public class EventPageAdapter extends PagedListAdapter<Event, EventViewHolder> {

    private PushFragmentInterface mPushFragmentInterface;

    public EventPageAdapter(PushFragmentInterface pushFragmentInterface) {
        super(DIFF_CALLBACK);
        mPushFragmentInterface = pushFragmentInterface;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(),R.layout.include_event_item_view,null);

        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {

        Event event = getItem(position);

        if(event != null){
            holder.bind(event);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fragment fragment = EventDetailFragment.newInstance(event);
                    mPushFragmentInterface.push(fragment);
                }
            });
        }
    }


    private static DiffUtil.ItemCallback<Event> DIFF_CALLBACK =
        new DiffUtil.ItemCallback<Event>() {
            @Override
            public boolean areItemsTheSame(@NonNull Event oldItem, @NonNull Event newItem) {
                return oldItem == newItem;
            }

            @Override
            public boolean areContentsTheSame(@NonNull Event oldItem, @NonNull Event newItem) {
                return oldItem.equals(newItem);
            }
        };
}
