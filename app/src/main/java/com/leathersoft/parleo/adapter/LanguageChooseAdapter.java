package com.leathersoft.parleo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.leathersoft.parleo.R;
import com.leathersoft.parleo.messaging.LanguageModel;
import com.leathersoft.parleo.util.LanguageHolderUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class LanguageChooseAdapter extends RecyclerView.Adapter<LanguageChooseAdapter.InterestsViewHolder>
        implements Filterable {


    private List<LanguageModel> interests;
    private List<LanguageModel> interestsFiltered;

    public void setInterests(List<LanguageModel> interests) {
        this.interests = interests;
        interestsFiltered = interests;
        notifyDataSetChanged();
    }

    public List<LanguageModel> getInterests() {
        return interests;
    }

    @NonNull
    @Override
    public LanguageChooseAdapter.InterestsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_language_choose, viewGroup, false);
        return new LanguageChooseAdapter.InterestsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LanguageChooseAdapter.InterestsViewHolder notificationViewHolder, int i) {
        LanguageModel model;
        try {
            model = interestsFiltered.get(i);
        } catch (Exception e) {
            return;
        }

        notificationViewHolder.bind(model);

        notificationViewHolder.box.setOnCheckedChangeListener(null);

        notificationViewHolder.box.setChecked(model.isChosen() == 1);

        notificationViewHolder.box.setOnCheckedChangeListener((buttonView, isChecked) -> {
            model.setChosen(isChecked? 1 : 0);
            notifyItemChanged(i);
        });

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
                    List<LanguageModel> filteredList = new ArrayList<>();
                    for (LanguageModel row : interests) {
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
                interestsFiltered = (ArrayList<LanguageModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    class InterestsViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_name)
        TextView name;
        @BindView(R.id.circleImageView)
        CircleImageView view;
        @BindView(R.id.checkBox)
        CheckBox box;

        public InterestsViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(LanguageModel interest) {
            name.setText(interest.getName());
            view.setImageDrawable(LanguageHolderUtil.getInstance().findById(interest.getCode()));

        }
    }

}
