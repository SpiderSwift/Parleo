package com.leathersoft.parleo.fragment.users;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leathersoft.parleo.R;
import com.leathersoft.parleo.activity.events.EventsPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserFragment extends Fragment {

    @BindView(R.id.recycler_view_users)
    RecyclerView mRecyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_users,container,false);
        ButterKnife.bind(this,v);

        UserAdapter adapter = new UserAdapter();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(adapter);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        Activity activity = getActivity();
        if(activity != null){
            getActivity().setTitle(getResources().getString(R.string.users));
        }

    }


    public static UserFragment newInstance(){
        return new UserFragment();
    }

//    @OnClick(R.id.user_card_profile_btn)
//    public void openProfileFragment(){
//
//        Snackbar.make(mRecyclerView,
//                "Profile button pressed",
//                Snackbar.LENGTH_LONG).show();
//    }
}
