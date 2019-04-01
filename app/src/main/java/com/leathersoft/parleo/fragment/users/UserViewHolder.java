package com.leathersoft.parleo.fragment.users;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.leathersoft.parleo.R;
import com.leathersoft.parleo.activity.ProfileActivity;

public class UserViewHolder extends RecyclerView.ViewHolder {
    TextView mTextView;

    public UserViewHolder(@NonNull View itemView) {
        super(itemView);
        mTextView = itemView.findViewById(R.id.user_card_profile_btn);
        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v,
                "Profile button pressed",
                Snackbar.LENGTH_LONG).show();
                v.getContext().startActivity(
                        new Intent(v.getContext(),ProfileActivity.class)
                );
            }
        });
    }
}
