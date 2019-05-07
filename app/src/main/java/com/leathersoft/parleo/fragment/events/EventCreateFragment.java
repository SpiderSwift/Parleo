package com.leathersoft.parleo.fragment.events;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.leathersoft.parleo.R;
import com.leathersoft.parleo.fragment.BaseFragment;
import com.leathersoft.parleo.fragment.FilterEventFragment;
import com.leathersoft.parleo.network.SingletonRetrofitClient;
import com.leathersoft.parleo.network.model.CreateEventModel;
import com.leathersoft.parleo.network.model.Event;
import com.leathersoft.parleo.util.ActionBarUtil;
import com.leathersoft.parleo.util.ImageUtil;
import com.leathersoft.parleo.util.TouchUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.documentfile.provider.DocumentFile;
import androidx.fragment.app.Fragment;

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

public class EventCreateFragment extends BaseFragment {

    private static final int GET_PHOTO_REQUEST_CODE = 200;

    private Uri mImageUri;

    @BindView(R.id.iv_photo)
    ImageView mEventImage;

    @BindView(R.id.te_event_name)
    EditText mEventName;

    @BindView(R.id.te_event_descriprion)
    EditText mEventDescription;

    @OnClick(R.id.iv_photo)
    public void getImage(){

        Intent i = new Intent(Intent.ACTION_OPEN_DOCUMENT);
//                Intent i = new Intent(Intent.ACTION_GET_CONTENT,
//                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("image/*");
        startActivityForResult(i, GET_PHOTO_REQUEST_CODE);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.save_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Fragment fragment;
        switch (item.getItemId()) {
            case R.id.menu_save:
//                fragment = EventCreateFragment.newInstance();
//                mPushFragmentInterface.push(fragment);
                if(canCreateEvent()){
                    createEvent();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private boolean canCreateEvent(){
        if(mImageUri == null){
            return false;
        }

        if(mEventName.getText().toString().isEmpty()){
            return false;
        }

        if(mEventDescription.getText().toString().isEmpty()){
            return false;
        }

        return true;
    }

    private void createEvent(){
        String eventName = mEventName.getText().toString();
        String eventDescription = mEventDescription.getText().toString();

        CreateEventModel createEventModel = new CreateEventModel();

        createEventModel.setName(eventName);
        createEventModel.setDescription(eventDescription);

        SingletonRetrofitClient.getInsance()
                .getApi()
                .postEvent(createEventModel)
                .enqueue(new Callback<Event>() {
                    @Override
                    public void onResponse(Call<Event> call, Response<Event> response) {

                        if(response.isSuccessful()){
                            Snackbar.make(getView(),"OK",Snackbar.LENGTH_LONG).show();
                            sendImage(response.body().getId());
                        }else {
                            try {

                                JSONObject jObjError = new JSONObject(response.errorBody().string());
                                Log.d("JSON",jObjError.toString());
                                Toast.makeText(getContext(), jObjError.getString("error"), Toast.LENGTH_LONG).show();
                            } catch (Exception e) {
//                                Log.d("JSON",jObjError.toString());

                                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Event> call, Throwable t) {
                        Snackbar.make(getView(),"SOMETHING WENT WRONG",Snackbar.LENGTH_LONG).show();

                    }
                });
    }

    private void sendImage(String eventId){


//        String result;
//        Cursor cursor = getContext().getContentResolver().query(mImageUri, null, null, null, null);
//        if (cursor == null) { // Source is Dropbox or other similar local file path
//            result = mImageUri.getPath();
//        } else {
//            cursor.moveToFirst();
//            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
//            result = cursor.getString(idx);
//            cursor.close();
//        }

        File file = new File(mImageUri.toString());
        if(!file.exists()){
            return;
        }

        //pass it like this
        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), file);

        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body =
                MultipartBody.Part.createFormData("image", file.getName(), requestFile);

//        MultipartBody.Part filePart = MultipartBody.Part.createFormData("file", file.getName(), RequestBody.create(MediaType.parse("image/*"), file));

        SingletonRetrofitClient.getInsance()
                .getApi()
                .putImage(eventId,body)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        if(response.isSuccessful()){
                            Snackbar.make(getView(),"OK",Snackbar.LENGTH_LONG).show();
                        }else {
                            try {
                                JSONObject jObjError = new JSONObject(response.errorBody().string());
                                Log.d("JSON",jObjError.toString());
                                Toast.makeText(getContext(), jObjError.getString("error"), Toast.LENGTH_LONG).show();
                            } catch (Exception e) {
                                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                        Log.d("JSON","Someething bad happended");
                    }
                });
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_event_create,container,false);
        ButterKnife.bind(this,v);
        TouchUtils.setEditTextMultilineScrolling(mEventDescription);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        ActionBarUtil.setFragmentTitle(getActivity(),R.string.event_create_title);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(data==null)return;

        if(requestCode == GET_PHOTO_REQUEST_CODE && resultCode == Activity.RESULT_OK){
            mImageUri = data.getData();
            ImageUtil.setImage(mImageUri.toString(),mEventImage,R.color.placeholderGray);


            String path = mImageUri.getPath();
            String path2 = mImageUri.toString();


//            String[] filePathColumn = { MediaStore.Images.Media.DATA };
//            Cursor cursor = getActivity().getContentResolver().query(mImageUri, filePathColumn, null, null, null);
//            cursor.moveToFirst();
//            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
//            String filePath = cursor.getString(columnIndex);
//            cursor.close();

            try {


                ParcelFileDescriptor parcelFileDescriptor =
                        getActivity().getContentResolver().openFileDescriptor(mImageUri, "r");
                FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
                Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
                parcelFileDescriptor.close();
            }catch (Exception e){

            }

            File file = new File(path);
            if(!file.exists()){
                return;
            }


            try {

                InputStream stream = getActivity().getContentResolver().openInputStream(mImageUri);

//                pass it like this
//                RequestBody requestFile =
//                        RequestBody.create(MediaType.parse("multipart/form-data"), stream);
//
//                 MultipartBody.Part is used to send also the actual file name
//                MultipartBody.Part body =
//                        MultipartBody.Part.createFormData("image", file.getName(), requestFile);

            }catch (FileNotFoundException e){

            }


        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public static EventCreateFragment newInstance(){
        return new EventCreateFragment();
    }
}
