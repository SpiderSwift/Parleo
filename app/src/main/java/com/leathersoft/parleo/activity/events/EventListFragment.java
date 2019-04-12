package com.leathersoft.parleo.activity.events;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.leathersoft.parleo.R;
import com.leathersoft.parleo.fragment.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventListFragment extends BaseFragment {

    private static final String TAG = "EventListFragment";

    LinearLayoutManager mLayoutManager;
    EventListAdapter mEventListAdapter;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        Log.d(TAG,"onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG,"onCreate");
    }

    @BindView(R.id.recycler_view_events) RecyclerView mRecyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.d(TAG,"onCreateView");
        View v = inflater.inflate(R.layout.fragment_events_list,container,false);
        ButterKnife.bind(this,v);

        mLayoutManager = new LinearLayoutManager(getContext());

        mEventListAdapter = new EventListAdapter(mPushFragmentInterface);
        DividerItemDecoration decoration = new DividerItemDecoration(mRecyclerView.getContext(),mLayoutManager.getOrientation());

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mEventListAdapter);
        mRecyclerView.addItemDecoration(decoration);
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d(TAG,"onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG,"onResume");
    }

    public static EventListFragment newInstance(){
        EventListFragment fragment = new EventListFragment();
        return fragment;
    }
}
