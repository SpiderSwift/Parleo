package com.leathersoft.parleo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.leathersoft.parleo.R;
import com.leathersoft.parleo.network.model.User;
import com.leathersoft.parleo.util.ActionBarUtil;
import com.leathersoft.parleo.util.ImageUtil;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileFragment extends BaseFragment {

    private User mUser;

    @BindView(R.id.iv_profile_avatar)
    ImageView mUserAvatar;

    @BindView(R.id.tv_profile_page_name)
    TextView mUserName;

    @BindView(R.id.tv_profile_page_description)
    TextView mUserDescription;



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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile_screen,container,false);
        ButterKnife.bind(this,v);

        ImageUtil.setImage(mUser.getAccountImage(),mUserAvatar,R.color.placeholderGray);
        mUserName.setText(mUser.getName());
        mUserDescription.setText(mUser.getAbout());
//        mUserDistance.setText(""+ mUser.getDistanceFromCurrentUser());
        
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        ActionBarUtil.setFragmentTitle(getActivity(),R.string.profile);
    }


    public static ProfileFragment newInstance(User user){
        ProfileFragment fragment = new ProfileFragment();

        Bundle args = new Bundle();
        args.putSerializable("user",user);
        fragment.setArguments(args);

        return fragment;
    }
}
