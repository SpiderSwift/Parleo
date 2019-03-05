package com.leathersoft.parleo.activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.leathersoft.parleo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistrationContinueActivity extends AppCompatActivity {

    @BindView(R.id.te_name) EditText editName;
    @BindView(R.id.te_city) EditText editCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_continue);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.btn_continue)
    public void onContinue() {
        startActivityForResult(new Intent(getApplicationContext(), RegistrationFinishActivity.class), 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        finish();
    }
}
