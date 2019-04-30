package com.leathersoft.parleo.fragment.events;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.leathersoft.parleo.R;
import com.leathersoft.parleo.fragment.BaseFragment;
import com.leathersoft.parleo.network.model.Event;
import com.leathersoft.parleo.network.model.User;
import com.leathersoft.parleo.util.ActionBarUtil;
import com.leathersoft.parleo.util.ImageUtil;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EventDetailFragment extends BaseFragment {

    Event mEvent;

    @BindView(R.id.iv_event_image)
    ImageView mEventImage;
    @BindView(R.id.tv_event_place_title)
    TextView mEventPlaceTitle;
    @BindView(R.id.tv_event_place_description)
    TextView mEventPlaceDescription;
    @BindView(R.id.iv_language_icon)
    ImageView mLanguageIcon;

    @OnClick(R.id.map)
    public void mapClick(){
        if(mEvent != null){

            String uri = String.format(Locale.ENGLISH, "geo:%f,%f", mEvent.getLatitude(),mEvent.getLongitude());
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
            getActivity().startActivity(intent);

        }
    }



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEvent = (Event) getArguments().getSerializable("event");
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_event_details,container,false);
        ButterKnife.bind(this,v);

        ImageUtil.setImage(mEvent.getImage(),mEventImage,R.drawable.cafe_placeholder);
        mEventPlaceTitle.setText(mEvent.getName());
        mEventPlaceDescription.setText(mEvent.getDescription());


        mLanguageIcon.setVisibility(View.GONE);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        ActionBarUtil.setFragmentTitle(getActivity(),R.string.event_details);
    }


    public static EventDetailFragment newInstance(Event event){

        EventDetailFragment fragment = new EventDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable("event",event);
        fragment.setArguments(args);
        return fragment;
    }
}
