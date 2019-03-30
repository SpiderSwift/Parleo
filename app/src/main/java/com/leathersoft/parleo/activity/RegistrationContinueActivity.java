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
import android.view.Gravity;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.PopupWindow;

import com.leathersoft.parleo.R;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

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
        window = new PopupWindow(getApplicationContext());

        View v = getLayoutInflater().inflate(R.layout.activity_login, null);
        //v.setAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.popup_animation));
        window.setWidth(ConstraintLayout.LayoutParams.MATCH_PARENT);
        window.setHeight(ConstraintLayout.LayoutParams.WRAP_CONTENT);
        window.setContentView(v);


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
        window.dismiss();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        finish();
    }
}
