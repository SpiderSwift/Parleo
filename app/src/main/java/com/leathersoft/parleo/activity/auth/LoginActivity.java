package com.leathersoft.parleo.activity.auth;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.google.android.material.snackbar.Snackbar;
import com.leathersoft.parleo.R;
import com.leathersoft.parleo.activity.TabsActivity;
import com.leathersoft.parleo.network.SingletonRetrofitClient;
import com.leathersoft.parleo.network.model.LoginResponse;
import com.leathersoft.parleo.network.model.LoginViewModel;
import com.leathersoft.parleo.network.model.RegisterViewModel;
import com.leathersoft.parleo.util.StorageUtil;

import androidx.appcompat.app.AppCompatActivity;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.te_email) EditText editEmail;
    @BindView(R.id.te_password) EditText editPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_login)
    public void onLogin() {
        SingletonRetrofitClient.getInsance().getApi()
                .login(new LoginViewModel(editEmail.getText().toString(), editPassword.getText().toString()))
                .enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if (response.isSuccessful()) {
                            Snackbar.make(editEmail,"OK",Snackbar.LENGTH_LONG).show();
                            SingletonRetrofitClient.setToken(response.body().getToken());
                            StorageUtil.save(LoginActivity.this, "email", editEmail.getText().toString());
                            StorageUtil.save(LoginActivity.this, "password", editPassword.getText().toString());
                            startActivity(new Intent(getApplicationContext(), TabsActivity.class));

                        } else {
                            try {
                                JSONObject jObjError = new JSONObject(response.errorBody().string());
                                String error = (String) jObjError.get("error");
                                Snackbar.make(editEmail,error,Snackbar.LENGTH_LONG).show();
                            } catch (Exception e) {
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {

                    }
                });
        //finish();
    }

}
