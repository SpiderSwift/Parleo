package com.leathersoft.parleo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.leathersoft.parleo.R;
import com.leathersoft.parleo.messaging.Interest;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class InterestsAdapter extends RecyclerView.Adapter<InterestsAdapter.InterestsViewHolder> implements Filterable {

    private List<Interest> interests;
    private List<Interest> interestsFiltered;

    public void setInterests(List<Interest> interests) {
        this.interests = interests;
        interestsFiltered = interests;
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
        Interest interest;
        try {
            interest = interestsFiltered.get(i);
        } catch (Exception e) {
            return;
        }

        notificationViewHolder.box.setOnCheckedChangeListener(null);

        notificationViewHolder.box.setChecked(interest.isChosen() == 1);

        notificationViewHolder.box.setOnCheckedChangeListener((buttonView, isChecked) -> {
            interest.setChosen(isChecked? 1 : 0);
            notifyItemChanged(i);
        });

        notificationViewHolder.bind(interest);
    }

    @Override
    public int getItemCount() {
        return interestsFiltered.size();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    interestsFiltered = interests;
                } else {
                    List<Interest> filteredList = new ArrayList<>();
                    for (Interest row : interests) {
                        if (row.getName().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }

                    interestsFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = interestsFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                interestsFiltered = (ArrayList<Interest>) filterResults.values;
                notifyDataSetChanged();
            }
        };
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
        }
    }

}
