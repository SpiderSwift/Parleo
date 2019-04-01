package com.leathersoft.parleo.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.leathersoft.parleo.R;
import com.leathersoft.parleo.activity.events.EventScreenFragment;
import com.leathersoft.parleo.fragment.DialogFragment;
import com.leathersoft.parleo.fragment.NotificationFragment;
import com.leathersoft.parleo.fragment.ProfileFragment;
import com.leathersoft.parleo.fragment.users.UserFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TabsActivity extends AppCompatActivity {


    Fragment mEventScreenFragment;
    Fragment mUserFragment;
    Fragment mDialogFragment;
    Fragment mNotificationFragment;
    Fragment mProfileFragment;

    FragmentManager mFragmentManager;
    Fragment active;


    @BindView(R.id.bottom_navigation_view)
    BottomNavigationView mNavigationView;
//
    @BindView(R.id.fragment_container)
    FrameLayout mFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);
        ButterKnife.bind(this);
        mFragmentManager = getSupportFragmentManager();


        mEventScreenFragment = EventScreenFragment.newInstance();
        mUserFragment = UserFragment.newInstance();
        mDialogFragment = DialogFragment.newInstance();
        mNotificationFragment = NotificationFragment.newInstance();
        mProfileFragment = ProfileFragment.newInstance();
        active = mEventScreenFragment;

        mNavigationView.setOnNavigationItemSelectedListener(this::onNavigationItemSelected);

        mFragmentManager.beginTransaction().
                replace(mFrameLayout.getId(),active,active.toString()).commit();
    }

    private List<Fragment> getFragmentList(){
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(mEventScreenFragment);
        fragments.add(mUserFragment);
        fragments.add(mDialogFragment);
        fragments.add(mNotificationFragment);
        fragments.add(mProfileFragment);
        return fragments;
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.bottom_nav_events:
                active = EventScreenFragment.newInstance();
                mFragmentManager.beginTransaction()
                        .replace(mFrameLayout.getId(),active)
                        .commit();
                return true;

            case R.id.bottom_nav_users:
                mFragmentManager.beginTransaction()
                        .replace(mFrameLayout.getId(),mUserFragment)
                        .commit();
                active = mUserFragment;
                return true;

            case R.id.bottom_nav_chats:
                mFragmentManager.beginTransaction()
                        .replace(mFrameLayout.getId(),mDialogFragment)
                        .commit();
                active = mDialogFragment;
                return true;

            case R.id.bottom_nav_notifications:
                mFragmentManager.beginTransaction()
                        .replace(mFrameLayout.getId(),mNotificationFragment)
                        .commit();
                active = mNotificationFragment;
                return true;
            case R.id.bottom_nav_profile:
                mFragmentManager.beginTransaction()
                        .replace(mFrameLayout.getId(),mProfileFragment)
                        .commit();
                active = mProfileFragment;
                return true;
        }
        return false;
    }
}
