package com.leathersoft.parleo.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.leathersoft.parleo.R;

public class NotificationsAdapter extends RecyclerView.Adapter<NotificationsAdapter.NotificationViewHolder> {

    private List<String> notifications;

    public void setNotifications(List<String> notifications) {
        this.notifications = notifications;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_notification, viewGroup, false);
        return new NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder notificationViewHolder, int i) {
        notificationViewHolder.bind(notifications.get(i));
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    class NotificationViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_text) TextView text;

        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(String s) {
            text.setText(s);
        }
    }

}
