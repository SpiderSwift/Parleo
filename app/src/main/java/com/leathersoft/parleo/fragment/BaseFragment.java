package com.leathersoft.parleo.fragment;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment extends Fragment {

    protected PushFragmentInterface mPushFragmentInterface;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof PushFragmentInterface){
            mPushFragmentInterface = (PushFragmentInterface) context;
        }

    }
}
