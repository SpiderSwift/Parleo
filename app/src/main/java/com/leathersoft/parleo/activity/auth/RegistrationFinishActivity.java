package com.leathersoft.parleo.activity.auth;

import android.Manifest;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.ParcelFileDescriptor;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.leathersoft.parleo.R;
import com.leathersoft.parleo.activity.TabsActivity;
import com.leathersoft.parleo.network.SingletonRetrofitClient;
import com.leathersoft.parleo.util.ImageUtil;
import com.leathersoft.parleo.util.UriUtil;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import org.json.JSONObject;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.InputStream;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationFinishActivity extends AppCompatActivity {


    private static final int GET_PHOTO_REQUEST_CODE = 200;

    private Uri mImageUri;

    @BindView(R.id.iv_photo) ImageView imagePhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration_finish);
        ButterKnife.bind(this);



    }


    @OnClick(R.id.btn_finish)
    public void onFinish() {

        if (mImageUri != null) {
            Log.d("TAG", mImageUri.toString());
            File file = new File(UriUtil.getPath(this, mImageUri));
            if(!file.exists()){
                startActivity(new Intent(getApplicationContext(), TabsActivity.class));
            }

            RequestBody requestFile =
                    RequestBody.create(MediaType.parse("multipart/form-data"), file);

            MultipartBody.Part body =
                    MultipartBody.Part.createFormData("image", file.getName(), requestFile);


            SingletonRetrofitClient.getInsance()
                    .getApi()
                    .putUserImage(body)
                    .enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) { }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) { }
                    });
        }

        startActivity(new Intent(getApplicationContext(), TabsActivity.class));

        //finish();
    }

    @OnClick(R.id.iv_photo)
    public void onPhotoChange() {
        Intent i = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("image/*");
        startActivityForResult(i, GET_PHOTO_REQUEST_CODE);
    }










    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(data==null)return;

        if(requestCode == GET_PHOTO_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            mImageUri = data.getData();
            ImageUtil.setImage(mImageUri.toString(),imagePhoto,R.color.placeholderGray);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }






}
