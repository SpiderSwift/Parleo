package com.leathersoft.parleo.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.leathersoft.parleo.R;
import com.leathersoft.parleo.util.ActionBarUtil;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ProfileFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_profile_screen,container,false);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        ActionBarUtil.setFragmentTitle(getActivity(),R.string.profile);
    }


    public static ProfileFragment newInstance(){
        return new ProfileFragment();
    }
}
