package com.leathersoft.parleo.network.users;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.leathersoft.parleo.R;
import com.leathersoft.parleo.fragment.ProfileFragment;
import com.leathersoft.parleo.fragment.PushFragmentInterface;
import com.leathersoft.parleo.fragment.users.UserViewHolder;
import com.leathersoft.parleo.network.model.User;

public class UserPageAdapter extends PagedListAdapter<User, UserViewHolder> {

    private PushFragmentInterface mPushFragmentInterface;


    public UserPageAdapter(PushFragmentInterface pushFragmentInterface) {
        super(DIFF_CALLBACK);
        mPushFragmentInterface = pushFragmentInterface;
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

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Fragment fragment = ProfileFragment.newInstance(user);
                    mPushFragmentInterface.push(fragment);
                }
            });

            holder.bind(user);
        }else {
        }
    }

    private static DiffUtil.ItemCallback<User> DIFF_CALLBACK  =
            new DiffUtil.ItemCallback<User>() {
                @Override
                public boolean areItemsTheSame(@NonNull User oldItem, @NonNull User newItem) {
//                    TODO
                    return oldItem == newItem;
                }

                @Override
                public boolean areContentsTheSame(@NonNull User oldItem, @NonNull User newItem) {
                    return oldItem.equals(newItem);
                }
            };
}
