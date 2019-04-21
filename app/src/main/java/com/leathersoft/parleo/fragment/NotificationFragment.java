package com.leathersoft.parleo.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leathersoft.parleo.R;
import com.leathersoft.parleo.adapter.NotificationsAdapter;
import com.leathersoft.parleo.util.ActionBarUtil;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class NotificationFragment extends BaseFragment {

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

    @Override
    public void onResume() {
        super.onResume();
        ActionBarUtil.setFragmentTitle(getActivity(),R.string.notifiactions);
    }

    public static NotificationFragment newInstance(){
        return new NotificationFragment();
    }


}
