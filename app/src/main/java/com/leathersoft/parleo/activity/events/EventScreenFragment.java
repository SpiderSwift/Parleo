package com.leathersoft.parleo.activity.events;

import android.app.Activity;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.google.android.material.tabs.TabLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.leathersoft.parleo.R;
import com.leathersoft.parleo.fragment.FilterEventFragment;
import com.ncapdevi.fragnav.FragNavController;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventScreenFragment extends Fragment {

    @BindView(R.id.events_tablayout) TabLayout mTabLayout;
    @BindView(R.id.events_viewpager) ViewPager mViewPager;

    private FragNavController mFragNavController;

    EventsPagerAdapter mEventsPagerAdapter;


//    public EventScreenFragment() {
//    }
//
//    public EventScreenFragment(FragNavController fragNavController) {
//        mFragNavController = fragNavController;
//    }

    //
//    public EventScreenFragment(FragNavController fragNavController) {
//        mFragNavController = fragNavController;
//    }
//
//    public EventScreenFragment(int contentLayoutId, FragNavController fragNavController) {
//        super(contentLayoutId);
//        mFragNavController = fragNavController;
//    }

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


        mEventsPagerAdapter = new EventsPagerAdapter(getFragmentManager());

        mViewPager.setAdapter(mEventsPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
        return v;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Fragment fragment;
        switch (item.getItemId()) {
            case R.id.menu_add_event:
                fragment = EventCreateFragment.newInstance();

                mFragNavController.replaceFragment(fragment);
//                getActivity().getSupportFragmentManager().beginTransaction()
//                        .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
//                        .replace(R.id.fragment_container,fragment)
//                        .addToBackStack(null)
//                        .commit();
                return true;

            case R.id.menu_filter_event:
                fragment = FilterEventFragment.newInstance();

                mFragNavController.replaceFragment(fragment);

//                getActivity().getSupportFragmentManager().beginTransaction()
//                        .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
//                        .replace(R.id.fragment_container,fragment)
//                        .addToBackStack(null)
//                        .commit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onResume() {
        super.onResume();
        Activity activity = getActivity();
        if(activity != null){
            getActivity().setTitle(getResources().getString(R.string.events));
        }
    }

    public static EventScreenFragment newInstance(){
        return new EventScreenFragment();
    }
}
