package com.leathersoft.parleo.fragment.events;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.leathersoft.parleo.R;
import com.leathersoft.parleo.fragment.BaseFragment;
import com.leathersoft.parleo.util.ActionBarUtil;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;

public class EventDetailFragment extends BaseFragment {

    @BindView(R.id.iv_language_icon)
    ImageView mIconLanguage;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_event_details,container,false);
        ButterKnife.bind(this,v);

        mIconLanguage.setVisibility(View.GONE);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        ActionBarUtil.setFragmentTitle(getActivity(),R.string.event_details);
    }


    public static EventDetailFragment newInstance(){
        return new EventDetailFragment();
    }
}
