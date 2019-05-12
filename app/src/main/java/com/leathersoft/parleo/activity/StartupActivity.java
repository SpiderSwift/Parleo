package com.leathersoft.parleo.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import com.leathersoft.parleo.network.SingletonSignalrClient;
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
                new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                201);

    }





    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 200: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {


                    SingletonSignalrClient.getInstance();
                    String email = StorageUtil.load(this, "email");
                    String password = StorageUtil.load(this, "password");

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

                } else {
                    finish();
                }
                return;
            }

            case 201: {
                if (!(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    finish();
                } else {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            200);
                }
            }

            // other 'case' lines to check for other
            // permissions this app might request.
        }
    }

}
