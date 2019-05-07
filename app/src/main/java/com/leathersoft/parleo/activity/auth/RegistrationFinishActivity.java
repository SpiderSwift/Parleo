package com.leathersoft.parleo.activity.auth;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.leathersoft.parleo.R;
import com.leathersoft.parleo.activity.TabsActivity;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RegistrationFinishActivity extends AppCompatActivity {

    @BindView(R.id.iv_photo) ImageView imagePhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_finish);
        ButterKnife.bind(this);
    }


    @OnClick(R.id.btn_finish)
    public void onFinish() {
        startActivity(new Intent(getApplicationContext(), TabsActivity.class));
        //finish();
    }

    @OnClick(R.id.iv_photo)
    public void onPhotoChange() {

    }

}
