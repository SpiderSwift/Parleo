package com.leathersoft.parleo.activity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.leathersoft.parleo.R;
import com.leathersoft.parleo.activity.events.EventScreenFragment;
import com.leathersoft.parleo.fragment.DialogFragment;
import com.leathersoft.parleo.fragment.MyProfilefragment;
import com.leathersoft.parleo.fragment.NotificationFragment;
import com.leathersoft.parleo.fragment.PushFragmentInterface;
import com.leathersoft.parleo.fragment.users.UserFragment;
import com.ncapdevi.fragnav.FragNavController;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import butterknife.BindView;
import butterknife.ButterKnife;

public class TabsActivity extends AppCompatActivity implements PushFragmentInterface {

    private static final int INDEX_EVENTS = FragNavController.TAB1;
    private static final int INDEX_USERS = FragNavController.TAB2;
    private static final int INDEX_CHATS = FragNavController.TAB3;
    private static final int INDEX_NOTIFICATIONS = FragNavController.TAB4;
    private static final int INDEX_MY_PROFILE = FragNavController.TAB5;

    Fragment mEventScreenFragment;
    Fragment mUserFragment;
    Fragment mDialogFragment;
    Fragment mNotificationFragment;
    Fragment mProfileFragment;

    FragmentManager mFragmentManager;
    Fragment active;


    private FragNavController mFragNavController;
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

        mNavigationView.setOnNavigationItemSelectedListener(this::onNavigationItemSelected);

        mFragmentManager = getSupportFragmentManager();
        mFragNavController = new FragNavController(getSupportFragmentManager(),R.id.fragment_container);
        mFragNavController.setRootFragmentListener(new FragNavController.RootFragmentListener() {
            @Override
            public int getNumberOfRootFragments() {
                return 5;
            }

            @NotNull
            @Override
            public Fragment getRootFragment(int i) {
                Fragment fragment;
                switch(i){
                    case INDEX_EVENTS:
                        fragment = EventScreenFragment.newInstance();
                        break;
                    case INDEX_USERS:
                        fragment = UserFragment.newInstance();
                        break;
                    case INDEX_CHATS:
                        fragment = DialogFragment.newInstance();
                        break;
                    case INDEX_NOTIFICATIONS:
                        fragment = NotificationFragment.newInstance();
                        break;
                    case INDEX_MY_PROFILE:
                        fragment = MyProfilefragment.newInstance();
                        break;
                    default:
                        throw new IllegalStateException("Need to send an index that we know");
                }
                return fragment;
            }
        });
        mFragNavController.initialize(INDEX_EVENTS,savedInstanceState);
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
                mFragNavController.switchTab(INDEX_EVENTS);
                return true;
            case R.id.bottom_nav_users:
                mFragNavController.switchTab(INDEX_USERS);
                return true;
            case R.id.bottom_nav_chats:
                mFragNavController.switchTab(INDEX_CHATS);
                return true;
            case R.id.bottom_nav_notifications:
                mFragNavController.switchTab(INDEX_NOTIFICATIONS);
                return true;
            case R.id.bottom_nav_profile:
                mFragNavController.switchTab(INDEX_MY_PROFILE);
                return true;
        }
        return false;
    }

    @Override
    public void push(Fragment fragment) {
        mFragNavController.pushFragment(fragment);
    }

    @Override
    public void onBackPressed() {

        if(mFragNavController.isRootFragment() || !mFragNavController.popFragment()){

            super.onBackPressed();
        }
    }
}
