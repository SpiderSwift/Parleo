package com.leathersoft.parleo.fragment.events;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.leathersoft.parleo.R;
import com.leathersoft.parleo.fragment.BaseFragment;
import com.leathersoft.parleo.fragment.FilterEventFragment;
import com.leathersoft.parleo.util.ActionBarUtil;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

public class EventScreenFragment extends BaseFragment {

    @BindView(R.id.events_tablayout) TabLayout mTabLayout;
    @BindView(R.id.events_viewpager) ViewPager mViewPager;

//    @BindView(R.id.swipe_refresh_layout)
//    SwipeRefreshLayout mRefreshLayout;


    EventsPagerAdapter mEventsPagerAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.events_appbar_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_event_screen,container,false);
        ButterKnife.bind(this,v);

        mEventsPagerAdapter = new EventsPagerAdapter(getChildFragmentManager());

        mViewPager.setAdapter(mEventsPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);


//        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                mPushFragmentInterface.push(EventScreenFragment.newInstance());
//            }
//        });

        return v;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Fragment fragment;
        switch (item.getItemId()) {
            case R.id.menu_add_event:
                fragment = EventCreateFragment.newInstance();
                mPushFragmentInterface.push(fragment);
                return true;

            case R.id.menu_filter_event:
                fragment = FilterEventFragment.newInstance();
                mPushFragmentInterface.push(fragment);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        ActionBarUtil.setFragmentTitle(getActivity(),R.string.events);
    }

    public static EventScreenFragment newInstance(){
        return new EventScreenFragment();
    }
}
