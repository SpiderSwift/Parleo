package com.leathersoft.parleo.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.leathersoft.parleo.R;
import com.leathersoft.parleo.activity.auth.MainActivity;
import com.leathersoft.parleo.activity.auth.RegistrationContinueActivity;
import com.leathersoft.parleo.activity.auth.RegistrationFinishActivity;
import com.leathersoft.parleo.network.SingletonRetrofitClient;
import com.leathersoft.parleo.network.model.ActivateResponse;
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


        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                200);


        Uri data = getIntent().getData();

        String email = StorageUtil.load(this, "email");
        String password = StorageUtil.load(this, "password");

        if (data != null) {
            String deepLinkToken = data.getQueryParameter("token");


            SingletonRetrofitClient.getInsance().getApi()
                    .login(new LoginViewModel(email, password))
                    .enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                            SingletonRetrofitClient.getInsance().getApi()
                                    .activate(deepLinkToken)
                                    .enqueue(new Callback<ActivateResponse>() {
                                        @Override
                                        public void onResponse(Call<ActivateResponse> call, Response<ActivateResponse> response) {
                                            if (response.isSuccessful()) {
                                                Log.d("TAG", "success");
                                                SingletonRetrofitClient.setToken(response.body().getToken());
                                                startActivity(new Intent(getApplicationContext(), RegistrationContinueActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                                            } else {
                                                try {
                                                    JSONObject jObjError = new JSONObject(response.errorBody().string());
                                                    Log.d("JSON",jObjError.toString());
                                                    Toast.makeText(getApplicationContext(), jObjError.getString("error"), Toast.LENGTH_LONG).show();
                                                } catch (Exception e) {

                                                }
                                            }

                                        }

                                        @Override
                                        public void onFailure(Call<ActivateResponse> call, Throwable t) {

                                        }
                                    });

                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {

                        }
                    });



        } else {

            SingletonRetrofitClient.getInsance().getApi()
                    .login(new LoginViewModel(email, password))
                    .enqueue(new Callback<LoginResponse>() {
                        @Override
                        public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                            if (response.isSuccessful()) {
                                SingletonRetrofitClient.setToken(response.body().getToken());
                                startActivity(new Intent(getApplicationContext(), TabsActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                            } else {
                                startActivity(new Intent(getApplicationContext(), MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                            }

                        }

                        @Override
                        public void onFailure(Call<LoginResponse> call, Throwable t) {

                        }
                    });
        }

    }
}
