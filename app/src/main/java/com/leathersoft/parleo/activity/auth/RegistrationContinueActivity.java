package com.leathersoft.parleo.activity.auth;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;

import com.leathersoft.parleo.R;
import com.leathersoft.parleo.adapter.InterestsAdapter;
import com.leathersoft.parleo.messaging.Interest;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistrationContinueActivity extends AppCompatActivity {

    @BindView(R.id.te_name) EditText editName;
    @BindView(R.id.te_city) EditText editCity;
    @BindView(R.id.layout) ConstraintLayout layout;
    PopupWindow windowLanguages;
    PopupWindow windowInterests;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_continue);
        ButterKnife.bind(this);
        //MenuInflater inflater = getMenuInflater();


        View viewLanguages = getLayoutInflater().inflate(R.layout.window_list, null);
        windowLanguages = new PopupWindow(viewLanguages, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        //windowLanguages.setElevation(1000);
        RecyclerView recyclerView = viewLanguages.findViewById(R.id.recyclerView);
        View v = viewLanguages.findViewById(R.id.mainLayout);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                windowLanguages.dismiss();
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
        InterestsAdapter adapter = new InterestsAdapter();
        recyclerView.setAdapter(adapter);
        List<Interest> languages = new ArrayList<>();
        languages.add(new Interest("English", getDrawable(R.drawable.ic_english)));
        languages.add(new Interest("Russian", getDrawable(R.drawable.ic_russian)));
        languages.add(new Interest("Spanish", getDrawable(R.drawable.ic_spanish)));
        languages.add(new Interest("English", getDrawable(R.drawable.ic_english)));
        languages.add(new Interest("Russian", getDrawable(R.drawable.ic_russian)));
        languages.add(new Interest("Spanish", getDrawable(R.drawable.ic_spanish)));
        adapter.setInterests(languages);
        Button b = viewLanguages.findViewById(R.id.btn_done);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                windowLanguages.dismiss();
            }
        });


        View viewInterests = getLayoutInflater().inflate(R.layout.window_list, null);
        windowInterests = new PopupWindow(viewInterests, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        //windowLanguages.setElevation(1000);
        RecyclerView recyclerViewInterests = viewInterests.findViewById(R.id.recyclerView);
        View v2 = viewInterests.findViewById(R.id.mainLayout);
        v2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                windowInterests.dismiss();
            }
        });
        LinearLayoutManager layoutManagerInterests = new LinearLayoutManager(getApplicationContext());
        recyclerViewInterests.setLayoutManager(layoutManagerInterests);
        InterestsAdapter adapterInterests = new InterestsAdapter();
        recyclerViewInterests.setAdapter(adapterInterests);
        List<Interest> interests = new ArrayList<>();
        interests.add(new Interest("Writing poems", getDrawable(R.drawable.ic_writing)));
        interests.add(new Interest("Yoga", getDrawable(R.drawable.ic_yoga)));
        interests.add(new Interest("Riding horses", getDrawable(R.drawable.ic_riding)));
        interests.add(new Interest("Writing poems", getDrawable(R.drawable.ic_writing)));
        interests.add(new Interest("Yoga", getDrawable(R.drawable.ic_yoga)));
        interests.add(new Interest("Riding horses", getDrawable(R.drawable.ic_riding)));
        adapterInterests.setInterests(interests);
        Button button = viewInterests.findViewById(R.id.btn_done);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                windowInterests.dismiss();
            }
        });



        //v.setAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.popup_animation));
//        windowLanguages.setWidth(ConstraintLayout.LayoutParams.MATCH_PARENT);
//        windowLanguages.setHeight(ConstraintLayout.LayoutParams.WRAP_CONTENT);
//        windowLanguages.setContentView(v);


    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        windowLanguages = null;
    }

    @OnClick(R.id.btn_continue)
    public void onContinue() {
        startActivityForResult(new Intent(getApplicationContext(), RegistrationFinishActivity.class), 0);
    }

    @OnClick(R.id.btn_languages)
    public void showLanguages() {
        windowLanguages.setAnimationStyle(R.anim.popup_animation);
        windowLanguages.showAtLocation(layout, Gravity.BOTTOM, 0, 0);
    }

    @OnClick(R.id.btn_interests)
    public void showInterests() {
        windowInterests.setAnimationStyle(R.anim.popup_animation);
        windowInterests.showAtLocation(layout, Gravity.BOTTOM, 0, 0);
    }


    @OnClick(R.id.layout)
    public void close() {
        windowLanguages.setAnimationStyle(R.anim.popup_animation);
        windowLanguages.dismiss();
        windowInterests.dismiss();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        finish();
    }
}
