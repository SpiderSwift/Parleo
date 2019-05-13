package com.leathersoft.parleo.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.leathersoft.parleo.R;
import com.leathersoft.parleo.fragment.users.UserFragment;
import com.leathersoft.parleo.network.friends.FriendPageAdapter;
import com.leathersoft.parleo.network.friends.FriendViewModel;
import com.leathersoft.parleo.network.model.User;
import com.leathersoft.parleo.network.users.UserPageAdapter;
import com.leathersoft.parleo.network.users.UserViewModel;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FriendListFragment extends BaseFragment {

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

        FriendViewModel userViewModel = ViewModelProviders.of(this)
                .get(FriendViewModel.class);

        final FriendPageAdapter userPageAdapter = new FriendPageAdapter(mPushFragmentInterface);

        userViewModel.getUserPagedList().observe(this, new Observer<PagedList<User>>() {
            @Override
            public void onChanged(PagedList<User> users) {
                userPageAdapter.submitList(users);
            }
        });

        mRecyclerView.setAdapter(userPageAdapter);
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
