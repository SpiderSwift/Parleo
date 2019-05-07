package com.leathersoft.parleo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.leathersoft.parleo.R;
import com.leathersoft.parleo.network.SingletonRetrofitClient;
import com.leathersoft.parleo.network.model.User;
import com.leathersoft.parleo.util.ActionBarUtil;
import com.leathersoft.parleo.util.ImageUtil;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyProfileFragment extends BaseFragment implements ReloadDataInterface{

    private User mMe;


    @BindView(R.id.iv_profile_avatar)
    ImageView mUserAvatar;

    @BindView(R.id.tv_profile_page_name)
    TextView mUserName;

    @BindView(R.id.tv_profile_page_description)
    TextView mUserDescription;

    @OnClick(R.id.edit_profile_btn)
    public void openEdit(){
        mPushFragmentInterface.push(EditProfileFragment.newInstance(this));
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        reloadData();
    }

    private void setUser(){
        SingletonRetrofitClient.getInsance()
                .getApi()
                .getMe()
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(response.body() != null){
                            mMe = response.body();
                            setInfoToViews();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });
    }

    private void setInfoToViews(){
        ImageUtil.setImage(mMe.getAccountImage(),mUserAvatar,R.color.placeholderGray);
        mUserName.setText(mMe.getName());
        mUserDescription.setText(mMe.getAbout());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_my_profile_screen,container,false);
        ButterKnife.bind(this,v);

        if(mMe != null){
            setInfoToViews();
        }

        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        ActionBarUtil.setFragmentTitle(getActivity(),R.string.my_profile);
    }

    @Override
    public void reloadData() {
        setUser();
    }


    public static MyProfileFragment newInstance(){
        return new MyProfileFragment();
    }
}
