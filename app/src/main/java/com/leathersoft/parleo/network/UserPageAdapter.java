package com.leathersoft.parleo.network;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.leathersoft.parleo.R;
import com.leathersoft.parleo.fragment.users.UserViewHolder;
import com.leathersoft.parleo.network.model.User;

public class UserPageAdapter extends PagedListAdapter<User, UserViewHolder> {

    public UserPageAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.user_item_view,null);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        User user = getItem(position);

        if(user != null){
            holder.bind(user);
        }else {
        }
    }

    private static DiffUtil.ItemCallback<User> DIFF_CALLBACK  =
            new DiffUtil.ItemCallback<User>() {
                @Override
                public boolean areItemsTheSame(@NonNull User oldItem, @NonNull User newItem) {
//                    TODO
                    return oldItem.equals(newItem);
                }

                @Override
                public boolean areContentsTheSame(@NonNull User oldItem, @NonNull User newItem) {
                    return oldItem.equals(newItem);
                }
            };
}
