package com.leathersoft.parleo.adapter;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.leathersoft.parleo.R;
import com.leathersoft.parleo.messaging.Interest;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class InterestsAdapter extends RecyclerView.Adapter<InterestsAdapter.InterestsViewHolder> {

    private List<Interest> interests;

    public void setInterests(List<Interest> interests) {
        this.interests = interests;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public InterestsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_interest, viewGroup, false);
        return new InterestsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull InterestsViewHolder notificationViewHolder, int i) {
        notificationViewHolder.bind(interests.get(i));
    }

    @Override
    public int getItemCount() {
        return interests.size();
    }

    class InterestsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_name) TextView name;
        @BindView(R.id.circleImageView) CircleImageView view;
        @BindView(R.id.checkBox) CheckBox box;

        public InterestsViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Interest interest) {
            name.setText(interest.getName());
            view.setImageDrawable(interest.getDrawable());
            //todo add box handling
        }
    }

}
