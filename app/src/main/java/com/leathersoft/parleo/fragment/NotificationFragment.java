package com.leathersoft.parleo.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leathersoft.parleo.R;
import com.leathersoft.parleo.adapter.NotificationsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationFragment extends Fragment {

    @BindView(R.id.list_notifications) RecyclerView view;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_notification, container, false);
        ButterKnife.bind(this, v);
        NotificationsAdapter adapter = new NotificationsAdapter();
        List<String> not = new ArrayList<>();
        not.add("Billy Herrington wants to go workout in gym with you");
        not.add("Billy Herrington wants to go workout in gym with you");
        not.add("Billy Herrington wants to go workout in gym with you");
        not.add("Billy Herrington wants to go workout in gym with you");
        not.add("Billy Herrington wants to go workout in gym with you");
        not.add("Billy Herrington wants to go workout in gym with you");
        not.add("Billy Herrington wants to go workout in gym with you");
        not.add("Billy Herrington wants to go workout in gym with you");
        not.add("Billy Herrington added you as friend, you are now the boss of this gym");
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        view.setLayoutManager(layoutManager);
        adapter.setNotifications(not);
        view.setAdapter(adapter);
        return v;
    }

    public static NotificationFragment newInstance(){
        return new NotificationFragment();
    }


}
