package com.leathersoft.parleo.activity;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.leathersoft.parleo.R;
import com.leathersoft.parleo.activity.auth.RegistrationContinueActivity;
import com.leathersoft.parleo.network.SingletonRetrofitClient;
import com.leathersoft.parleo.network.model.ActivateResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeepLinkStartupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_startup);


        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                200);


        Uri data = getIntent().getData();

        if (data != null) {
            String deepLinkToken = data.getQueryParameter("token");

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
                                startActivity(new Intent(getApplicationContext(), StartupActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
                            }

                        }

                        @Override
                        public void onFailure(Call<ActivateResponse> call, Throwable t) {

                        }
                    });

        }

    }


}
