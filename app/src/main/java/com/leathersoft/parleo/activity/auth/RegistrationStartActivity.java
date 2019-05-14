package com.leathersoft.parleo.activity.auth;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;
import com.leathersoft.parleo.R;
import com.leathersoft.parleo.network.SingletonRetrofitClient;
import com.leathersoft.parleo.network.model.RegisterViewModel;
import com.leathersoft.parleo.util.StorageUtil;

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

        if (!editPassword.getText().toString().equals(editConfirm.getText().toString())) {
            Snackbar.make(editEmail,"Passwords must match",Snackbar.LENGTH_LONG).show();
            return;
        }

        SingletonRetrofitClient.getInsance().getApi()
                .register(new RegisterViewModel(editEmail.getText().toString(), editPassword.getText().toString()))
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if (response.isSuccessful()) {
                            Snackbar.make(editEmail,"OK",Snackbar.LENGTH_LONG).show();
                            Log.d("TAG", response.toString());
                            StorageUtil.save(RegistrationStartActivity.this,"email", editEmail.getText().toString());
                            StorageUtil.save(RegistrationStartActivity.this,"password", editPassword.getText().toString());
                            startActivity(new Intent(getApplicationContext(), VerifyActivity.class).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK));
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


}
