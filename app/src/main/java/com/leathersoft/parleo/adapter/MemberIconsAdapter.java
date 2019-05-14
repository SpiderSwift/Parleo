package com.leathersoft.parleo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.leathersoft.parleo.R;
import com.leathersoft.parleo.network.model.Event;

import java.util.List;

public class MemberIconsAdapter extends RecyclerView.Adapter<MemberIconViewHolder> {

    private List<Event.Particiant> mParticiants;

    public MemberIconsAdapter(List<Event.Particiant> particiants) {
        mParticiants = particiants;
    }

    @NonNull
    @Override
    public MemberIconViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_user_icon, parent, false);
        return new MemberIconViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberIconViewHolder holder, int position) {
        holder.bind(mParticiants.get(position));
    }

    @Override
    public int getItemCount() {
        return mParticiants.size();
    }
}
