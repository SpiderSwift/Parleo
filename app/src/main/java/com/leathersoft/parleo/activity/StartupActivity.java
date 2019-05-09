package com.leathersoft.parleo.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;
import com.leathersoft.parleo.R;
import com.leathersoft.parleo.activity.auth.MainActivity;
import com.leathersoft.parleo.activity.auth.RegistrationContinueActivity;
import com.leathersoft.parleo.network.SingletonRetrofitClient;
import com.leathersoft.parleo.network.model.LoginResponse;
import com.leathersoft.parleo.network.model.LoginViewModel;
import com.leathersoft.parleo.util.StorageUtil;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StartupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);

        String email = StorageUtil.load(this, "email");
        String password = StorageUtil.load(this, "password");


        SingletonRetrofitClient.getInsance().getApi()
                .login(new LoginViewModel(email, password))
                .enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if (response.isSuccessful()) {
                            SingletonRetrofitClient.setToken(response.body().getToken());
                            startActivity(new Intent(getApplicationContext(), RegistrationContinueActivity.class));
                        } else {
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        }

                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {

                    }
                });


    }
}
