package com.leathersoft.parleo.activity;

import android.content.Intent;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.leathersoft.parleo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistrationStartActivity extends AppCompatActivity {

    @BindView(R.id.te_email) EditText editEmail;
    @BindView(R.id.te_password) EditText editPassword;
    @BindView(R.id.te_confirm) EditText editConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_start);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.btn_continue)
    public void onContinue() {
        startActivityForResult(new Intent(getApplicationContext(), RegistrationContinueActivity.class), 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        finish();
    }
}
