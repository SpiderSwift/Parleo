package com.leathersoft.parleo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import com.leathersoft.parleo.util.ImageUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfileFragment extends BaseFragment {

    User mUser;

    ReloadDataInterface mReloadDataInterface;

    @BindView(R.id.iv_avatar)
    ImageView mAvatar;

    @BindView(R.id.et_profile_name)
    EditText mEtProfileName;

    @BindView(R.id.et_profile_age)
    EditText mEtProfileAge;

    @BindView(R.id.et_profile_description)
    EditText mDescription;

    @BindView(R.id.btn_your_languages)
    Button mBtnLanguages;

    @BindView(R.id.btn_your_interests)
    Button mBtnInterests;

    @BindView(R.id.btn_save)
    Button mBtnSave;

    @OnClick(R.id.btn_save)
    public void save(){

        if(mUser == null){
            return;
        }

        mUser.setAbout(mDescription.getText().toString());
        mUser.setName(mEtProfileName.getText().toString());

        UserUpdateModel model = new UserUpdateModel(mUser);

        SingletonRetrofitClient.getInsance()
                .getApi()
                .updateUser(mUser.getId(),model)
                .enqueue(new Callback<AccountResponse>() {
                    @Override
                    public void onResponse(Call<AccountResponse> call, Response<AccountResponse> response) {
                        Snackbar.make(getView(),"OK",Snackbar.LENGTH_LONG).show();
                    }

                    @Override
                    public void onFailure(Call<AccountResponse> call, Throwable t) {
                        Snackbar.make(getView(),"Something went wrong",Snackbar.LENGTH_LONG).show();

                    }
                });

    }




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mReloadDataInterface = (ReloadDataInterface) getArguments().getSerializable("reload");

        SingletonRetrofitClient.getInsance()
                .getApi()
                .getMe()
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(response.body() != null){
                            mUser = response.body();
                            setInfoToViews();
                            mReloadDataInterface.reloadData();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });

    }

    private void setInfoToViews(){
        ImageUtil.setImage(mUser.getAccountImage(),mAvatar,R.drawable.avatar_placeholder);
        mEtProfileName.setText(mUser.getName());
//        mEtProfileAge.setText(mUser.get);
        mDescription.setText(mUser.getAbout());

//        ImageUtil.setImage(mUser.getAccountImage(),mUserAvatar,R.color.placeholderGray);
    }





    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile_edit,container,false);
        ButterKnife.bind(this,v);

        return v;
    }

    public static EditProfileFragment newInstance(ReloadDataInterface reloadDataInterface){
        EditProfileFragment fragment =  new EditProfileFragment();
        Bundle args = new Bundle();
        args.putSerializable("reload",reloadDataInterface);
        fragment.setArguments(args);
        return fragment;
    }
}
