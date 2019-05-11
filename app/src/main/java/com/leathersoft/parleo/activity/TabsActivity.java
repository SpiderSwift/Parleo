package com.leathersoft.parleo.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.leathersoft.parleo.R;
import com.leathersoft.parleo.fragment.ChatFragment;
import com.leathersoft.parleo.fragment.MyProfileFragment;
import com.leathersoft.parleo.fragment.NotificationFragment;
import com.leathersoft.parleo.fragment.PushFragmentInterface;
import com.leathersoft.parleo.fragment.events.EventScreenFragment;
import com.leathersoft.parleo.fragment.users.UserFragment;
import com.leathersoft.parleo.network.SingletonRetrofitClient;
import com.leathersoft.parleo.network.SingletonSignalrClient;
import com.leathersoft.parleo.network.model.MessageViewModel;
import com.leathersoft.parleo.network.model.User;
import com.leathersoft.parleo.util.StorageUtil;
import com.microsoft.signalr.HubConnection;
import com.microsoft.signalr.HubConnectionBuilder;
import com.ncapdevi.fragnav.FragNavController;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Completable;
import okhttp3.internal.platform.Platform;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

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
        SingletonSignalrClient.getInstance();
        SingletonRetrofitClient.getInsance()
                .getApi()
                .getMe()
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(response.isSuccessful()){
                            SingletonSignalrClient.setCurrentId(response.body() != null ? response.body().getId() : null);
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });



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
                        fragment = ChatFragment.newInstance();
                        break;
                    case INDEX_NOTIFICATIONS:
                        fragment = NotificationFragment.newInstance();
                        break;
                    case INDEX_MY_PROFILE:
                        fragment = MyProfileFragment.newInstance();
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
            StorageUtil.save(this, "password", "");
            StorageUtil.save(this, "login", "");
            super.onBackPressed();
        }
    }
}
