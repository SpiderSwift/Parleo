package com.leathersoft.parleo.fragment.users;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.leathersoft.parleo.R;
import com.leathersoft.parleo.activity.events.EventViewHolder;

public class UserAdapter extends RecyclerView.Adapter<UserViewHolder> {

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = View.inflate(viewGroup.getContext(),R.layout.user_item_view,null);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder userViewHolder, int i) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
