package com.leathersoft.parleo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.leathersoft.parleo.R;
import com.leathersoft.parleo.messaging.LanguageModel;
import com.leathersoft.parleo.network.SingletonRetrofitClient;
import com.leathersoft.parleo.network.model.Hobby;
import com.leathersoft.parleo.network.model.Language;
import com.leathersoft.parleo.network.model.User;
import com.leathersoft.parleo.util.ActionBarUtil;
import com.leathersoft.parleo.util.ImageUtil;
import com.leathersoft.parleo.util.LanguageHolderUtil;

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


//    @BindView(R.id.iv_profile_avatar)
//    ImageView mUserAvatar;

//    View v;
//
//    @BindView(R.id.tv_profile_page_name)
//    TextView mUserName;
//
//    @BindView(R.id.tv_profile_page_description)
//    TextView mUserDescription;

    @OnClick(R.id.edit_profile_btn)
    public void openEdit(){
        mPushFragmentInterface.push(EditProfileFragment.newInstance(this));
    }


    @OnClick(R.id.ll_friends)
    public void openFriends() {
        mPushFragmentInterface.push(FriendListFragment.newInstance());
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
        if (mMe != null) {
            ImageView view = getActivity().findViewById(R.id.iv_profile_avatar);
            if (view != null) {
                ImageUtil.setImage(mMe.getAccountImage(),view,R.color.placeholderGray);
                TextView tvName = getActivity().findViewById(R.id.tv_profile_page_name);
                TextView tvDesc = getActivity().findViewById(R.id.tv_profile_page_description);
                TextView tvHobbiesAndLanguages =  getActivity().findViewById(R.id.tv_profile_page_hobbies);
                StringBuilder desc = new StringBuilder("Hobbies : ");
                for (Hobby hobby : mMe.getHobbies()) {
                    desc.append(hobby.getName() + " ");
                }
                desc.append("\n");
                desc.append("Languages : ");
                for (Language language : mMe.getLanguages()) {
                    String name = LanguageHolderUtil.getInstance().findNameById(language.getCode());
                    String level = LanguageModel.langLevelMap.get(language.getLevel() - 1);
                    String s = name + "(" + level + ") ";
                    desc.append(s);
                }
                tvHobbiesAndLanguages.setText(desc.toString());
                tvName.setText(mMe.getName());
                tvDesc.setText(mMe.getAbout());
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_my_profile_screen,container,false);
        ButterKnife.bind(this,v);

//        if(mMe != null){
//            setInfoToViews();
//        }

        setUser();

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
