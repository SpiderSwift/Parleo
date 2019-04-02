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
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.leathersoft.parleo.R;
import com.leathersoft.parleo.activity.events.EventCreateFragment;
import com.leathersoft.parleo.activity.events.EventsPagerAdapter;
import com.leathersoft.parleo.fragment.FilterEventFragment;
import com.leathersoft.parleo.fragment.FilterUserFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class UserFragment extends Fragment {

    @BindView(R.id.recycler_view_users)
    RecyclerView mRecyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_user_screen,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Fragment fragment;
        switch (item.getItemId()) {
            case R.id.menu_filter_users:
                fragment = FilterUserFragment.newInstance();

                getActivity().getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                        .replace(R.id.fragment_container,fragment)
                        .addToBackStack(null)
                        .commit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_users,container,false);
        ButterKnife.bind(this,v);

        UserAdapter adapter = new UserAdapter(getActivity().getSupportFragmentManager());
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
