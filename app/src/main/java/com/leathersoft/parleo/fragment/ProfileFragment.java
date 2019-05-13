package com.leathersoft.parleo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.leathersoft.parleo.R;
import com.leathersoft.parleo.activity.ChatActivity;
import com.leathersoft.parleo.messaging.LanguageModel;
import com.leathersoft.parleo.network.SingletonRetrofitClient;
import com.leathersoft.parleo.network.model.ChatModel;
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
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileFragment extends BaseFragment {

    private User mUser;

    @BindView(R.id.iv_profile_avatar)
    ImageView mUserAvatar;

    @BindView(R.id.tv_profile_page_name)
    TextView mUserName;

    @BindView(R.id.tv_profile_page_description)
    TextView mUserDescription;

    @BindView(R.id.tv_profile_page_hobbies)
    TextView mUserLanguagesAndHobbies;

    //TODO add languages


    @BindView(R.id.btn_profile_add_friend)
    Button mBtnAddFriend;

    @BindView(R.id.btn_profile_send_message)
    Button mBtnSendMessage;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUser = (User) getArguments().getSerializable("user");
    }



    @OnClick(R.id.btn_profile_add_friend)
    public void interact() {
        if (mUser.isFriend()) {
            SingletonRetrofitClient.getInsance().getApi().removeFriend(mUser.getId())
                    .enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response.isSuccessful()) {
                                mUser.setFriend(false);
                                mBtnAddFriend.setText(mUser.isFriend() ? "Remove friend" : "Add friend");
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                        }
                    });
        } else {
            SingletonRetrofitClient.getInsance().getApi().addFriend(mUser.getId())
                    .enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if (response.isSuccessful()) {
                                mUser.setFriend(true);
                                mBtnAddFriend.setText(mUser.isFriend() ? "Remove friend" : "Add friend");
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                        }
                    });
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile_screen,container,false);
        ButterKnife.bind(this,v);

        ImageUtil.setImage(mUser.getAccountImage(),mUserAvatar,R.color.placeholderGray);
        mUserName.setText(mUser.getName());
        mUserDescription.setText(mUser.getAbout());

        mBtnAddFriend.setText(mUser.isFriend() ? "Remove friend" : "Add friend");

        StringBuilder desc = new StringBuilder("Hobbies : ");
        for (Hobby hobby : mUser.getHobbies()) {
            desc.append(hobby.getName() + " ");
        }
        desc.append("\n");
        desc.append("Languages : ");
        for (Language language : mUser.getLanguages()) {
            String name = LanguageHolderUtil.getInstance().findNameById(language.getCode());
            String level = LanguageModel.langLevelMap.get(language.getLevel() - 1);
            String s = name + "(" + level + ") ";
            desc.append(s);
        }
        mUserLanguagesAndHobbies.setText(desc.toString());

//        mUserDistance.setText(""+ mUser.getDistanceFromCurrentUser());
        
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        ActionBarUtil.setFragmentTitle(getActivity(),R.string.profile);
    }


    @OnClick(R.id.btn_profile_send_message)
    public void onSendMessage() {

        SingletonRetrofitClient.getInsance().getApi().getChatWithUser(mUser.getId())
                .enqueue(new Callback<ChatModel>() {
                    @Override
                    public void onResponse(Call<ChatModel> call, Response<ChatModel> response) {
                        if (response.isSuccessful()) {
                            startActivity(new Intent(getActivity(), ChatActivity.class).putExtra("chatId", response.body().getId()));
                        }
                    }

                    @Override
                    public void onFailure(Call<ChatModel> call, Throwable t) {

                    }
                });

    }



    public static ProfileFragment newInstance(User user){
        ProfileFragment fragment = new ProfileFragment();

        Bundle args = new Bundle();
        args.putSerializable("user",user);
        fragment.setArguments(args);

        return fragment;
    }
}
