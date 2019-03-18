package com.leathersoft.parleo.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.leathersoft.parleo.R;
import com.leathersoft.parleo.adapter.MainFragmentsAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnTouch;

public class TabsActivity extends AppCompatActivity {

    @BindView(R.id.pager_main) ViewPager pager;
    @BindView(R.id.tab_main) TabLayout tabLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tabs);
        ButterKnife.bind(this);
        pager.setAdapter(new MainFragmentsAdapter(getSupportFragmentManager()));
        pager.setOffscreenPageLimit(5);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition(), false);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }


    @OnTouch(R.id.pager_main)
    public boolean onTouch() {
        return true;
    }

}
