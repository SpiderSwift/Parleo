package com.leathersoft.parleo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


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
        Log.d("TAG", "msg sign");
    }


    @OnClick(R.id.btn_log)
    public void onLogin() {
        Log.d("TAG", "msg log");
    }

}
