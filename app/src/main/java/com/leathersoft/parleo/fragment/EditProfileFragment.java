package com.leathersoft.parleo.fragment;

import android.app.Activity;
import android.app.Application;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.snackbar.Snackbar;
import com.leathersoft.parleo.MainApplication;
import com.leathersoft.parleo.R;
import com.leathersoft.parleo.activity.InterestsWindowActivity;
import com.leathersoft.parleo.activity.LanguageWindowActivity;
import com.leathersoft.parleo.activity.TabsActivity;
import com.leathersoft.parleo.activity.auth.RegistrationFinishActivity;
import com.leathersoft.parleo.listener.DateButtonOnClickListener;
import com.leathersoft.parleo.messaging.Interest;
import com.leathersoft.parleo.messaging.LanguageModel;
import com.leathersoft.parleo.network.SingletonRetrofitClient;
import com.leathersoft.parleo.network.model.AccountResponse;
import com.leathersoft.parleo.network.model.Hobby;
import com.leathersoft.parleo.network.model.Language;
import com.leathersoft.parleo.network.model.User;
import com.leathersoft.parleo.network.model.UserUpdateModel;
import com.leathersoft.parleo.util.ActionBarUtil;
import com.leathersoft.parleo.util.DateUtil;
import com.leathersoft.parleo.util.HobbyHolderUtil;
import com.leathersoft.parleo.util.ImageUtil;
import com.leathersoft.parleo.util.LanguageHolderUtil;
import com.leathersoft.parleo.util.LocaleUtil;
import com.leathersoft.parleo.util.TouchUtils;
import com.leathersoft.parleo.util.UriUtil;

import org.json.JSONObject;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

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

public class EditProfileFragment extends BaseFragment {

    private static final String ARGS_INTERFACE_KEY = "reload_key";

    User mUser;

    //ReloadDataInterface mReloadDataInterface;

    @BindView(R.id.iv_avatar)
    ImageView mAvatar;

    @BindView(R.id.et_profile_name)
    EditText mEtProfileName;

    @BindView(R.id.et_profile_age)
    Button mEtProfileAge;

    @BindView(R.id.et_profile_description)
    EditText mDescription;

    @BindView(R.id.btn_your_languages)
    Button mBtnLanguages;

    @BindView(R.id.btn_your_interests)
    Button mBtnInterests;

    @BindView(R.id.btn_save)
    Button mBtnSave;

    @BindView(R.id.sw_gender)
    Switch genderSwitch;

    private List<LanguageModel> languageModels = new ArrayList<>();
    private List<Interest> interests = new ArrayList<>();

    Uri mImageUri;


    @OnClick(R.id.btn_your_interests)
    public void interests(){
        startActivityForResult(new Intent(MainApplication.getAppContext(), InterestsWindowActivity.class).putExtra("listInterests", (Serializable) interests), 201);
    }

    @OnClick(R.id.btn_your_languages)
    public void languages(){
        startActivityForResult(new Intent(MainApplication.getAppContext(), LanguageWindowActivity.class).putExtra("listLanguages", (Serializable) languageModels), 200);
    }

    @OnClick(R.id.btn_save)
    public void save(){
        if(mUser == null){
            return;
        }

        //TODO Maybe add ontext change listener instead of this
        mUser.setAbout(mDescription.getText().toString());
        mUser.setName(mEtProfileName.getText().toString());


        List<Language> languages = new ArrayList<>();
        for (LanguageModel languageModel : languageModels) {
            if (languageModel.isChosen() == 1) {
                Language language = new Language();
                language.setCode(languageModel.getCode());
                language.setLevel(languageModel.getLevel() + 1); // todo тут нужно подумать, можно ли сделать нормально?
                languages.add(language);
            }
        }

        List<String> hobbies = new ArrayList<>();

        for (Interest interest : interests) {
            if (interest.isChosen() == 1) {
                hobbies.add(interest.getName());
            }
        }



        UserUpdateModel model = new UserUpdateModel();
        model.setAbout(mDescription.getText().toString());
        model.setBirthdate(mUser.getBirthdate());
        model.setHobbies(hobbies);
        model.setLanguages(languages);
        model.setGender(mUser.isGender());
        model.setName(mEtProfileName.getText().toString());
        SingletonRetrofitClient.getInsance()
                .getApi()
                .updateUser(model)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });

        if (mImageUri != null) {
            Log.d("TAG", mImageUri.toString());
            File file = new File(UriUtil.getPath(MainApplication.getAppContext(), mImageUri));

            //TODO remove dublicate code MULTIPART DATA
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

        Snackbar.make(getView(),"Saved",Snackbar.LENGTH_LONG).show();


        //UserUpdateModel model = new UserUpdateModel(mUser);

//        SingletonRetrofitClient.getInsance()
//                .getApi()
//                .updateUser(mUser.getId(),model)
//                .enqueue(new Callback<AccountResponse>() {
//                    @Override
//                    public void onResponse(Call<AccountResponse> call, Response<AccountResponse> response) {
//                        if(response.isSuccessful()){
//                            Snackbar.make(getView(),getResources().getString(R.string.snack_bar_ok),Snackbar.LENGTH_LONG).show();
//                            mReloadDataInterface.reloadData();
//                            getActivity().onBackPressed();
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<AccountResponse> call, Throwable t) {
//                        Snackbar.make(getView(),getResources().getString(R.string.snack_bar_something_wrong),Snackbar.LENGTH_LONG).show();
//                    }
//                });
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //mReloadDataInterface = (ReloadDataInterface) getArguments().getSerializable(ARGS_INTERFACE_KEY);

        SingletonRetrofitClient.getInsance()
                .getApi()
                .getMe()
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(response.body() != null){
                            mUser = response.body();
                            setInfoToViews();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile_edit,container,false);
        ButterKnife.bind(this,v);
        TouchUtils.setEditTextMultilineScrolling(mDescription);

        mEtProfileAge.setOnClickListener(new DateButtonOnClickListener(
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        mUser.setBirthdate(DateUtil.getDateFromDatePicker(datePicker));
                        setInfoToViews();
                    }
                }
        ));
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        ActionBarUtil.setFragmentTitle(getActivity(),R.string.profile_edit_profile);
    }

    private void setInfoToViews(){
        languageModels = LanguageHolderUtil.getInstance().createModelLists(mUser.getLanguages());
        interests = HobbyHolderUtil.getHobbyModels(mUser.getHobbies());
        ImageUtil.setImage(mUser.getAccountImage(),mAvatar,R.color.placeholderGray);
        mEtProfileName.setText(mUser.getName());
        mEtProfileAge.setText(
                String.format(
                        LocaleUtil.getCurrentLocale(getContext()),
                        "%d",
                        DateUtil.calculateAge(mUser.getBirthdate(),Calendar.getInstance().getTime())
                )
        );
        mDescription.setText(mUser.getAbout());
        genderSwitch.setChecked(mUser.isGender());
        genderSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            mUser.setGender(isChecked);
        });
    }


    @OnClick(R.id.iv_avatar)
    public void onPhotoChange() {
        Intent i = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        i.addCategory(Intent.CATEGORY_OPENABLE);
        i.setType("image/*");
        startActivityForResult(i, 300);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(data==null)return;

        if(requestCode == 300 && resultCode == Activity.RESULT_OK){
            mImageUri = data.getData();
            ImageUtil.setImage(mImageUri.toString(),mAvatar,R.color.placeholderGray);
        }


        if (requestCode == 200) {
            languageModels = (List<LanguageModel>) data.getSerializableExtra("listLanguages");
            Log.d("TAG", languageModels.toString());
        }

        if (requestCode == 201) {
            interests = (List<Interest>) data.getSerializableExtra("listInterests");
            Log.d("TAG", interests.toString());
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    public static EditProfileFragment newInstance(ReloadDataInterface reloadDataInterface){
        EditProfileFragment fragment =  new EditProfileFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARGS_INTERFACE_KEY ,reloadDataInterface);
        fragment.setArguments(args);
        return fragment;
    }
}
