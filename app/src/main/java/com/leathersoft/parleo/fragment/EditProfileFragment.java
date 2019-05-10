package com.leathersoft.parleo.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.snackbar.Snackbar;
import com.leathersoft.parleo.R;
import com.leathersoft.parleo.network.SingletonRetrofitClient;
import com.leathersoft.parleo.network.model.AccountResponse;
import com.leathersoft.parleo.network.model.User;
import com.leathersoft.parleo.network.model.UserUpdateModel;
import com.leathersoft.parleo.util.ActionBarUtil;
import com.leathersoft.parleo.util.DateUtil;
import com.leathersoft.parleo.util.ImageUtil;
import com.leathersoft.parleo.util.LocaleUtil;

import java.util.Calendar;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileFragment extends BaseFragment {

    private static final String ARGS_INTERFACE_KEY = "reload_key";

    User mUser;

    ReloadDataInterface mReloadDataInterface;

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

    @OnClick(R.id.et_profile_age)
    public void getBirthDateDialog(){
        if(getContext() == null){
            return;
        }

        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        calendar.setTime(mUser.getBirthdate());

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                getContext(),
                this::onDateSet,
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
        );

        datePickerDialog.show();
    }

    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        mUser.setBirthdate(DateUtil.getDateFromDatePicker(datePicker));
        setInfoToViews();
    }

    @OnClick(R.id.btn_save)
    public void save(){
        if(mUser == null){
            return;
        }

        //TODO Maybe add ontext change listener instead of this
        mUser.setAbout(mDescription.getText().toString());
        mUser.setName(mEtProfileName.getText().toString());

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
        mReloadDataInterface = (ReloadDataInterface) getArguments().getSerializable(ARGS_INTERFACE_KEY);

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
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        ActionBarUtil.setFragmentTitle(getActivity(),R.string.profile_edit_profile);
    }

    private void setInfoToViews(){
        ImageUtil.setImage(mUser.getAccountImage(),mAvatar,R.drawable.avatar_placeholder);
        mEtProfileName.setText(mUser.getName());
        mEtProfileAge.setText(
                String.format(
                        LocaleUtil.getCurrentLocale(getContext()),
                        "%d",
                        DateUtil.calculateAge(mUser.getBirthdate(),Calendar.getInstance().getTime())
                )
        );
        mDescription.setText(mUser.getAbout());
    }


    public static EditProfileFragment newInstance(ReloadDataInterface reloadDataInterface){
        EditProfileFragment fragment =  new EditProfileFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARGS_INTERFACE_KEY ,reloadDataInterface);
        fragment.setArguments(args);
        return fragment;
    }
}
