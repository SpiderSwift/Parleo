package com.leathersoft.parleo.activity.auth;

import android.content.Intent;
import android.os.Bundle;

import com.leathersoft.parleo.R;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_sign)
    public void onSign() {
        startActivityForResult(new Intent(getApplicationContext(), RegistrationStartActivity.class), 0);
    }


    @OnClick(R.id.btn_login)
    public void onLogin() {
        startActivityForResult(new Intent(getApplicationContext(), LoginActivity.class), 0);
    }

}
