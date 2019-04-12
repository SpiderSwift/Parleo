package com.leathersoft.parleo.activity.events;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.leathersoft.parleo.R;

public class EventListAdapter extends RecyclerView.Adapter<EventViewHolder> {

    private FragmentManager mFragmentManager;

    public EventListAdapter(FragmentManager fragmentManager) {
        mFragmentManager = fragmentManager;
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(viewGroup.getContext(),R.layout.event_item_view,null);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Fragment fragment = new EventDetailFragment();

                mFragmentManager.beginTransaction()
                        .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                        .replace(R.id.fragment_container,fragment)
                        .addToBackStack(null)
                        .commit();
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
