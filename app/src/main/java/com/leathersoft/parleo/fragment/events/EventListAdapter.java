package com.leathersoft.parleo.fragment.events;

import android.view.View;
import android.view.ViewGroup;

import com.leathersoft.parleo.R;
import com.leathersoft.parleo.fragment.PushFragmentInterface;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

public class EventListAdapter extends RecyclerView.Adapter<EventViewHolder> {

    private PushFragmentInterface mPushFragmentInterface;

    public EventListAdapter(PushFragmentInterface pushFragmentInterface) {
        mPushFragmentInterface = pushFragmentInterface;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(viewGroup.getContext(),R.layout.include_event_item_view,null);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment fragment = new EventDetailFragment();
                mPushFragmentInterface.push(fragment);
            }
        });
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
