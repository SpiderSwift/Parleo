package com.leathersoft.parleo.fragment;

import android.app.Activity;
import android.os.Bundle;
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
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.leathersoft.parleo.R;
import com.leathersoft.parleo.network.model.User;
import com.leathersoft.parleo.network.users.UserPageAdapter;
import com.leathersoft.parleo.network.users.UserViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FriendListFragment extends BaseFragment {

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mRefreshLayout;

    @BindView(R.id.recycler_view_users)
    RecyclerView mRecyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_users,container,false);
        ButterKnife.bind(this,v);


        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setHasFixedSize(true);

        UserViewModel userViewModel = ViewModelProviders.of(this)
                .get(UserViewModel.class);

        userViewModel.initFactory(FriendListFragment.class);

        final UserPageAdapter userPageAdapter = new UserPageAdapter(mPushFragmentInterface);

        userViewModel.getUserPagedList().observe(this, new Observer<PagedList<User>>() {
            @Override
            public void onChanged(PagedList<User> users) {
                userPageAdapter.submitList(users);
            }
        });

        mRecyclerView.setAdapter(userPageAdapter);

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mPushFragmentInterface.replace(FriendListFragment.newInstance());
            }
        });
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        Activity activity = getActivity();
        if(activity != null){
            getActivity().setTitle(getResources().getString(R.string.friends));
        }

    }

    public static FriendListFragment newInstance(){
        return new FriendListFragment();
    }

}
