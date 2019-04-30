package com.leathersoft.parleo.fragment.events;

import android.view.View;
import android.view.ViewGroup;

import com.leathersoft.parleo.R;
import com.leathersoft.parleo.fragment.PushFragmentInterface;
import com.leathersoft.parleo.network.model.Event;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class EventListAdapter extends RecyclerView.Adapter<EventViewHolder> {

    private List<Event> mEvents;
    private PushFragmentInterface mPushFragmentInterface;

    public EventListAdapter(PushFragmentInterface pushFragmentInterface) {
        mPushFragmentInterface = pushFragmentInterface;
        mEvents = new ArrayList<>();
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(viewGroup.getContext(),R.layout.include_event_item_view,null);
        return new EventViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder eventViewHolder, int i) {

        Event event = mEvents.get(i);

        if(event != null){
            eventViewHolder.bind(event);

            eventViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fragment fragment = EventDetailFragment.newInstance(event);
                    mPushFragmentInterface.push(fragment);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mEvents.size();
    }

    public List<Event> getEvents() {
        return mEvents;
    }
}
