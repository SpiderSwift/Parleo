package com.leathersoft.parleo.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.leathersoft.parleo.R;
import com.leathersoft.parleo.activity.events.EventCreateFragment;
import com.leathersoft.parleo.activity.events.EventScreenFragment;
import com.leathersoft.parleo.fragment.DialogFragment;
import com.leathersoft.parleo.fragment.MyProfilefragment;
import com.leathersoft.parleo.fragment.NotificationFragment;
import com.leathersoft.parleo.fragment.ProfileFragment;
import com.leathersoft.parleo.fragment.users.UserFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Stack;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TabsActivity extends AppCompatActivity {

    public static final String TAB_EVENTS  = "tab_events";
    public static final String TAB_USERS  = "tab_users";
    public static final String TAB_CHATS  = "tab_chats";
    public static final String TAB_NOTIFICATIONS  = "tab_notifications";
    public static final String TAB_PROFILE = "tab_profile";


    private static final String BACK_STACK_ROOT_TAG = "root_fragment";

    private HashMap<String, Stack<Fragment>> mStacks;

    Fragment mEventScreenFragment;
    Fragment mUserFragment;
    Fragment mDialogFragment;
    Fragment mNotificationFragment;
    Fragment mProfileFragment;

    FragmentManager mFragmentManager;
    Fragment active;


    @BindView(R.id.bottom_navigation_view)
    BottomNavigationView mNavigationView;

    @BindView(R.id.fragment_container)
    FrameLayout mFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);
        ButterKnife.bind(this);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        mFragmentManager = getSupportFragmentManager();


        mEventScreenFragment = EventScreenFragment.newInstance();
        mUserFragment = UserFragment.newInstance();
        mDialogFragment = DialogFragment.newInstance();
        mNotificationFragment = NotificationFragment.newInstance();
        mProfileFragment = MyProfilefragment.newInstance();
        active = mEventScreenFragment;

        mStacks = new HashMap<>();
        mStacks.put(TAB_EVENTS, new Stack<Fragment>());
        mStacks.put(TAB_USERS, new Stack<Fragment>());
        mStacks.put(TAB_CHATS, new Stack<Fragment>());
        mStacks.put(TAB_NOTIFICATIONS, new Stack<Fragment>());
        mStacks.put(TAB_PROFILE, new Stack<Fragment>());

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
                //TODO do something with backstack
                mFragmentManager.beginTransaction()
                        .replace(mFrameLayout.getId(),active)
//                        .addToBackStack(null)
                        .commit();
                return true;

            case R.id.bottom_nav_users:
                mFragmentManager.beginTransaction()
                        .replace(mFrameLayout.getId(),mUserFragment)
                        .addToBackStack(BACK_STACK_ROOT_TAG)
                        .commit();
                active = mUserFragment;
                return true;

            case R.id.bottom_nav_chats:
                mFragmentManager.beginTransaction()
                        .replace(mFrameLayout.getId(),mDialogFragment)
                        .addToBackStack(BACK_STACK_ROOT_TAG)
                        .commit();
                active = mDialogFragment;
                return true;

            case R.id.bottom_nav_notifications:
                mFragmentManager.beginTransaction()
                        .replace(mFrameLayout.getId(),mNotificationFragment)
                        .addToBackStack(BACK_STACK_ROOT_TAG)
                        .commit();
                active = mNotificationFragment;
                return true;
            case R.id.bottom_nav_profile:
                mFragmentManager.beginTransaction()
                        .replace(mFrameLayout.getId(),mProfileFragment)
                        .addToBackStack(BACK_STACK_ROOT_TAG)
                        .commit();
                active = mProfileFragment;
                return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        FragmentManager manager = getSupportFragmentManager();
        if(manager.getBackStackEntryCount() > 0) {
            super.onBackPressed();
            Fragment currentFragment = manager.findFragmentById(R.id.fragment_container);
            if(currentFragment instanceof EventScreenFragment){
                mNavigationView.getMenu().getItem(0).setChecked(true);
            }
            else if(currentFragment instanceof EventCreateFragment){
                mNavigationView.getMenu().getItem(0).setChecked(true);
            }
            else if(currentFragment instanceof UserFragment){
                mNavigationView.getMenu().getItem(1).setChecked(true);
            }
            else if(currentFragment instanceof ProfileFragment){
                mNavigationView.getMenu().getItem(1).setChecked(true);
            }
            else if(currentFragment instanceof DialogFragment){
                mNavigationView.getMenu().getItem(2).setChecked(true);
            }
            else if(currentFragment instanceof NotificationFragment){
                mNavigationView.getMenu().getItem(3).setChecked(true);
            }
            else if(currentFragment instanceof MyProfilefragment){
                mNavigationView.getMenu().getItem(4).setChecked(true);
            }
        }else {

            super.onBackPressed();
        }
    }
}
