package com.leathersoft.parleo.fragment.events;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.leathersoft.parleo.R;
import com.leathersoft.parleo.fragment.BaseFragment;
import com.leathersoft.parleo.listener.ButtonOnDateSetListener;
import com.leathersoft.parleo.listener.ButtonOnTimeSetListener;
import com.leathersoft.parleo.listener.DateButtonOnClickListener;
import com.leathersoft.parleo.listener.TimeButtonOnClickListener;
import com.leathersoft.parleo.network.SingletonRetrofitClient;
import com.leathersoft.parleo.network.model.CreateEventModel;
import com.leathersoft.parleo.network.model.Event;
import com.leathersoft.parleo.util.ActionBarUtil;
import com.leathersoft.parleo.util.DateUtil;
import com.leathersoft.parleo.util.ImageUtil;
import com.leathersoft.parleo.util.LocaleUtil;
import com.leathersoft.parleo.util.TouchUtils;
import com.schibstedspain.leku.LocationPickerActivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.json.JSONObject;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

import static com.schibstedspain.leku.LocationPickerActivityKt.ADDRESS;
import static com.schibstedspain.leku.LocationPickerActivityKt.LATITUDE;
import static com.schibstedspain.leku.LocationPickerActivityKt.LOCATION_ADDRESS;
import static com.schibstedspain.leku.LocationPickerActivityKt.LONGITUDE;
import static com.schibstedspain.leku.LocationPickerActivityKt.TIME_ZONE_DISPLAY_NAME;
import static com.schibstedspain.leku.LocationPickerActivityKt.TIME_ZONE_ID;
import static com.schibstedspain.leku.LocationPickerActivityKt.TRANSITION_BUNDLE;
import static com.schibstedspain.leku.LocationPickerActivityKt.ZIPCODE;

public class EventCreateFragment extends BaseFragment {

    private final static String DATE_FORMAT = "EEE, d MMM yyyy";
    private final static String TIME_FORMAT = "HH:mm";

    private CreateEventModel mCreateEventModel;

    private SimpleDateFormat mDateFormat;
    private SimpleDateFormat mTimeFormat;


    private static final int GET_PHOTO_REQUEST_CODE = 200;
    private static final int GET_LOCATION_REQUEST_CODE = 300;

    private Uri mImageUri;

    @BindView(R.id.iv_photo)
    ImageView mEventImage;

    @BindView(R.id.te_event_name)
    EditText mEventName;

    @BindView(R.id.te_event_descriprion)
    EditText mEventDescription;

    @BindView(R.id.btn_start_date_picker)
    Button mBtnStartDatePicker;

    @BindView(R.id.btn_start_time_picker)
    Button mBtnStartTimePicker;

    @BindView(R.id.btn_end_date_picker)
    Button mBtnEndDatePicker;

    @BindView(R.id.btn_end_time_picker)
    Button mBtnEndTimePicker;

    @OnClick(R.id.iv_photo)
    public void getImage(){

        Intent i = new Intent(Intent.ACTION_OPEN_DOCUMENT);
//                Intent i = new Intent(Intent.ACTION_GET_CONTENT,
//                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("image/*");
        startActivityForResult(i, GET_PHOTO_REQUEST_CODE);
    }

    @BindView(R.id.add_location_button)
    TextView mTvLocation;

    @OnClick(R.id.add_location_button)
    public void getLocation(){
        Intent intent = new LocationPickerActivity.Builder()
                .build(getContext());

        startActivityForResult(intent, GET_LOCATION_REQUEST_CODE);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mCreateEventModel = new CreateEventModel();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.save_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_save:
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

        if(mEventName.getText().toString().trim().isEmpty()){
            return false;
        }

        if(mEventDescription.getText().toString().trim().isEmpty()){
            return false;
        }

        return true;
    }

    private void createEvent(){
        String eventName = mEventName.getText().toString();
        String eventDescription = mEventDescription.getText().toString();

        mCreateEventModel.setName(eventName);
        mCreateEventModel.setDescription(eventDescription);

        Date startDate = null;
        Date endDate = null;
        try {
            startDate = DateUtil.getDateFromStringDateAndStringTime(
                    mBtnStartDatePicker.getText().toString(),
                    mBtnStartTimePicker.getText().toString(),
                    mDateFormat,
                    mTimeFormat
            );

            endDate = DateUtil.getDateFromStringDateAndStringTime(
                    mBtnEndDatePicker.getText().toString(),
                    mBtnEndTimePicker.getText().toString(),
                    mDateFormat,
                    mTimeFormat
            );
        }catch (ParseException e){
            Snackbar.make(getView(),getResources().getString(R.string.snack_bar_something_wrong),Snackbar.LENGTH_LONG);
            return;
        }finally {
            mCreateEventModel.setStartTime(startDate);
            mCreateEventModel.setEndDate(endDate);
        }



//        createEventModel.setName(eventName);
//        createEventModel.setDescription(eventDescription);

        SingletonRetrofitClient.getInsance()
                .getApi()
                .postEvent(mCreateEventModel)
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

        mDateFormat = new SimpleDateFormat(DATE_FORMAT, LocaleUtil.getCurrentLocale(getContext()));
        mTimeFormat = new SimpleDateFormat(TIME_FORMAT, LocaleUtil.getCurrentLocale(getContext()));
        initDataButtons(mDateFormat,mTimeFormat,mCreateEventModel.getStartTime());

        return v;
    }

    private void initDataButtons(SimpleDateFormat dateFormat, SimpleDateFormat timeFormat, Date date){
        mBtnStartDatePicker.setText(dateFormat.format(date));
        mBtnEndDatePicker.setText((dateFormat.format(date)));

        mBtnStartTimePicker.setText(timeFormat.format(date));
        mBtnEndTimePicker.setText(timeFormat.format(date));

        mBtnStartDatePicker.setOnClickListener(
                new DateButtonOnClickListener(
                        new ButtonOnDateSetListener(mBtnStartDatePicker,dateFormat)
                )
        );

        mBtnEndDatePicker.setOnClickListener(
                new DateButtonOnClickListener(
                        new ButtonOnDateSetListener(mBtnEndDatePicker,dateFormat)
                )
        );

        mBtnStartTimePicker.setOnClickListener(
                new TimeButtonOnClickListener(
                        new ButtonOnTimeSetListener(mBtnStartTimePicker,timeFormat)
                )
        );

        mBtnEndTimePicker.setOnClickListener(
                new TimeButtonOnClickListener(
                        new ButtonOnTimeSetListener(mBtnEndTimePicker,timeFormat)
                )
        );
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
//            String path = mImageUri.getPath();
//
//            try {
//                ParcelFileDescriptor parcelFileDescriptor =
//                        getActivity().getContentResolver().openFileDescriptor(mImageUri, "r");
//                FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();
//                Bitmap image = BitmapFactory.decodeFileDescriptor(fileDescriptor);
//                parcelFileDescriptor.close();
//            }catch (Exception e){
//
//            }
//
//            File file = new File(path);
//            if(!file.exists()){
//                return;
//            }
//
        }

        if(requestCode == GET_LOCATION_REQUEST_CODE && resultCode == Activity.RESULT_OK){

            final String ADDRESS = "ADDRESS";
            Double latitude = data.getDoubleExtra(LATITUDE, 0.0);
            Log.d(ADDRESS, latitude.toString());
            Double longitude = data.getDoubleExtra(LONGITUDE, 0.0);
            Log.d(ADDRESS, longitude.toString());
            String address = data.getStringExtra(LOCATION_ADDRESS);
            Log.d(ADDRESS, address);
            String postalcode = data.getStringExtra(ZIPCODE);
            Log.d(ADDRESS, postalcode);

            mTvLocation.setText(address);

        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public static EventCreateFragment newInstance(){
        return new EventCreateFragment();
    }
}
