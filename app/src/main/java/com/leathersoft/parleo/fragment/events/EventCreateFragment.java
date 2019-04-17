package com.leathersoft.parleo.fragment.events;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.leathersoft.parleo.R;
import com.leathersoft.parleo.fragment.BaseFragment;
import com.leathersoft.parleo.util.ActionBarUtil;
import com.leathersoft.parleo.util.TouchUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;

public class EventCreateFragment extends BaseFragment {

    @BindView(R.id.te_event_descriprion)
    EditText etDescription;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_event_create,container,false);
        ButterKnife.bind(this,v);
        TouchUtils.setEditTextMultilineScrolling(etDescription);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        ActionBarUtil.setFragmentTitle(getActivity(),R.string.event_create_title);
    }


    public static EventCreateFragment newInstance(){
        return new EventCreateFragment();
    }
}
