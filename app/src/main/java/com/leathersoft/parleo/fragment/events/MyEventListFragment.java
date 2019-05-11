package com.leathersoft.parleo.fragment.events;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.leathersoft.parleo.R;
import com.leathersoft.parleo.fragment.BaseFragment;
import com.leathersoft.parleo.network.SingletonRetrofitClient;
import com.leathersoft.parleo.network.model.Event;
import com.leathersoft.parleo.network.model.EventPageAdapter;
import com.leathersoft.parleo.network.model.EventViewModel;
import com.leathersoft.parleo.network.model.User;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyEventListFragment extends BaseFragment {

    @BindView(R.id.recycler_view_events)
    RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_events_list,container,false);
        ButterKnife.bind(this,v);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setHasFixedSize(true);

        EventViewModel eventViewModel = ViewModelProviders.of(this)
                .get(EventViewModel.class);
        eventViewModel.initFactory(this.getClass());


        final EventPageAdapter eventPageAdapter =
                new EventPageAdapter(mPushFragmentInterface);

        eventViewModel.getEventPagedList().observe(this, new Observer<PagedList<Event>>() {
            @Override
            public void onChanged(PagedList<Event> events) {
                eventPageAdapter.submitList(events);
            }
        });

        mRecyclerView.setAdapter(eventPageAdapter);

        return v;
    }

    public static MyEventListFragment newInstance(){
        MyEventListFragment fragment = new MyEventListFragment();
        return fragment;
    }
}
