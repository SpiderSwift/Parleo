package com.leathersoft.parleo.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.leathersoft.parleo.R;
import com.leathersoft.parleo.network.model.Event;
import com.leathersoft.parleo.util.ImageUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MemberIconViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.iv_member_icon)
    ImageView mMemberIcon;

    @BindView(R.id.tv_member_name)
    TextView mTextView;

    public MemberIconViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this,itemView);
    }

    public void bind(Event.Particiant particiant){
        ImageUtil.setImage(particiant.getImage(),mMemberIcon,R.color.placeholderGray);
        mTextView.setText(particiant.getName());
    }
}
