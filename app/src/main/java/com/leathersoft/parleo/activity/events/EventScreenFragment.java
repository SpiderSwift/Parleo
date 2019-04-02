package com.leathersoft.parleo.activity.events;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.Toast;

import com.leathersoft.parleo.R;
import com.leathersoft.parleo.fragment.FilterEventFragment;
import com.leathersoft.parleo.fragment.users.UserAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class EventScreenFragment extends Fragment {

    @BindView(R.id.events_tablayout) TabLayout mTabLayout;
    @BindView(R.id.events_viewpager) ViewPager mViewPager;

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

                getActivity().getSupportFragmentManager().beginTransaction()
                        .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                        .replace(R.id.fragment_container,fragment)
                        .addToBackStack(null)
                        .commit();
                return true;

            case R.id.menu_filter_event:
                fragment = FilterEventFragment.newInstance();

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
