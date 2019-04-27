package com.leathersoft.parleo.fragment.users;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.leathersoft.parleo.R;
import com.leathersoft.parleo.network.model.User;

public class UserViewHolder extends RecyclerView.ViewHolder {

    ImageView mUserAvatar;
    TextView mUserName;
    TextView mUserDescription;
    TextView mUserDistance;


    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
        mUserAvatar = itemView.findViewById(R.id.iv_user_avatar);
        mUserName = itemView.findViewById(R.id.tv_user_name);
        mUserDescription = itemView.findViewById(R.id.tv_user_description);
        mUserDistance = itemView.findViewById(R.id.tv_user_distance);

    }
    public void bind(User user){

//        mUserAvatar = itemView.findViewById(R.id.iv_user_avatar);
        mUserName.setText(user.getName());
        mUserDescription.setText(user.getAbout());
        mUserDistance.setText(user.getDistanceFromCurrentUser());
    }
}
