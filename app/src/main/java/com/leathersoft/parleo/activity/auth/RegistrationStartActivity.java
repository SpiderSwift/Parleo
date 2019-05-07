package com.leathersoft.parleo.activity.auth;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;
import com.leathersoft.parleo.R;
import com.leathersoft.parleo.network.SingletonRetrofitClient;
import com.leathersoft.parleo.network.model.RegisterViewModel;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        //startActivityForResult(new Intent(getApplicationContext(), RegistrationContinueActivity.class), 0);

        SingletonRetrofitClient.getInsance().getApi()
                .register(new RegisterViewModel(editEmail.getText().toString(), editPassword.getText().toString()))
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            Snackbar.make(editEmail,"OK",Snackbar.LENGTH_LONG).show();
                            Log.d("TAG", response.toString());
                            onBackPressed();
                        } else {
                            Snackbar.make(editEmail,"failed",Snackbar.LENGTH_LONG).show();
                            Log.d("TAG", response.toString());
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        finish();
    }
}
