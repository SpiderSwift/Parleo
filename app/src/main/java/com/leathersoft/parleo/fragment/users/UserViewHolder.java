package com.leathersoft.parleo.fragment.users;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.leathersoft.parleo.R;
import com.leathersoft.parleo.network.model.User;
import com.leathersoft.parleo.util.ImageUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.iv_user_avatar)
    ImageView mUserAvatar;

    @BindView(R.id.tv_user_name)
    TextView mUserName;

    @BindView(R.id.tv_user_description)
    TextView mUserDescription;

    @BindView(R.id.tv_user_distance)
    TextView mUserDistance;


    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }
    public void bind(User user){

        ImageUtil.setImage(user.getAccountImage(),mUserAvatar,R.drawable.bg_avatar_placeholder);
        mUserName.setText(user.getName());
        mUserDescription.setText(user.getAbout());
        mUserDistance.setText(""+ user.getDistanceFromCurrentUser());
    }
}
