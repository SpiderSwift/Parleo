package com.leathersoft.parleo.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.view.menu.MenuPopupHelper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.PopupWindow;

import com.leathersoft.parleo.R;
import com.leathersoft.parleo.adapter.InterestsAdapter;
import com.leathersoft.parleo.messaging.Interest;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistrationContinueActivity extends AppCompatActivity {

    @BindView(R.id.te_name) EditText editName;
    @BindView(R.id.te_city) EditText editCity;
    @BindView(R.id.layout) ConstraintLayout layout;
    PopupWindow window;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_continue);
        ButterKnife.bind(this);
        //MenuInflater inflater = getMenuInflater();


        View v = getLayoutInflater().inflate(R.layout.window_list, null);
        window = new PopupWindow(v, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        window.setElevation(1000);
        RecyclerView recyclerView = v.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        InterestsAdapter adapter = new InterestsAdapter();
        recyclerView.setAdapter(adapter);
        List<Interest> interests = new ArrayList<>();
        interests.add(new Interest("hello", getDrawable(R.drawable.billy)));
        interests.add(new Interest("hello2", getDrawable(R.drawable.billy)));
        interests.add(new Interest("hello3", getDrawable(R.drawable.billy)));
        adapter.setInterests(interests);
        Button b = v.findViewById(R.id.btn_done);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                window.dismiss();
            }
        });
        //v.setAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.popup_animation));
//        window.setWidth(ConstraintLayout.LayoutParams.MATCH_PARENT);
//        window.setHeight(ConstraintLayout.LayoutParams.WRAP_CONTENT);
//        window.setContentView(v);


    }


    @OnClick(R.id.btn_continue)
    public void onContinue() {
        startActivityForResult(new Intent(getApplicationContext(), RegistrationFinishActivity.class), 0);
    }

    @OnClick(R.id.btn_languages)
    public void showMenu() {
        //setForceShowIcon(menu);
        //menu.setGravity(Gravity.END);
        //menu.show();
        window.setAnimationStyle(R.anim.popup_animation);
        window.showAtLocation(layout, Gravity.BOTTOM, 0, 0);
    }

    @OnClick(R.id.layout)
    public void close() {
        window.setAnimationStyle(R.anim.popup_animation);
        window.dismiss();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        finish();
    }
}
