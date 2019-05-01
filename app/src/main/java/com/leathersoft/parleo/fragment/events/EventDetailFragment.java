package com.leathersoft.parleo.fragment.events;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.leathersoft.parleo.R;
import com.leathersoft.parleo.ScrollableMapView;
import com.leathersoft.parleo.fragment.BaseFragment;
import com.leathersoft.parleo.network.model.Event;
import com.leathersoft.parleo.network.model.User;
import com.leathersoft.parleo.util.ActionBarUtil;
import com.leathersoft.parleo.util.ImageUtil;
import com.leathersoft.parleo.util.LocaleUtil;
import com.leathersoft.parleo.util.TouchUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EventDetailFragment extends BaseFragment {

    private final static String START_DATE_FORMAT = "d MMM yyyy HH:mm";

    @BindView(R.id.map)
    ScrollableMapView mMapView;

    GoogleMap mGoogleMap;
    Event mEvent;

    @BindView(R.id.iv_event_image)
    ImageView mEventImage;
    @BindView(R.id.tv_event_place_title)
    TextView mEventPlaceTitle;
    @BindView(R.id.tv_event_place_description)
    TextView mEventPlaceDescription;
    @BindView(R.id.iv_language_icon)
    ImageView mLanguageIcon;

    @BindView(R.id.tv_date_and_time)
    TextView mTvEventDateTime;

    @BindView(R.id.tv_adress)
    TextView mTvAdress;

    
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

        initGoogleMap(savedInstanceState);

        ImageUtil.setImage(mEvent.getImage(),mEventImage,R.drawable.cafe_placeholder);
        mEventPlaceTitle.setText(mEvent.getName());
        mEventPlaceDescription.setText(mEvent.getDescription());

        SimpleDateFormat mDateFormat = new SimpleDateFormat(START_DATE_FORMAT, LocaleUtil.getCurrentLocale(getContext()));
        mTvEventDateTime.setText(
                mDateFormat.format(mEvent.getStartTime())
        );

        mLanguageIcon.setVisibility(View.GONE);
        return v;
    }

    private void initGoogleMap(Bundle savedInstanceState){
        mMapView.onCreate(savedInstanceState);

        // Gets to GoogleMap from the MapView and does initialization stuff
        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                mGoogleMap = googleMap;
                mGoogleMap.getUiSettings().setMyLocationButtonEnabled(false);

                LatLng latLng = new LatLng(mEvent.getLatitude(),mEvent.getLongitude());
                mGoogleMap.addMarker(new MarkerOptions().position(latLng));
                mGoogleMap.getUiSettings().setMapToolbarEnabled(true);

                CameraPosition cameraPosition = new CameraPosition.Builder()
                        .target(latLng) // Center Set
                        .zoom(11.0f)// Zoom
                        .build();                   // Creates a CameraPosition from the builder

//                mGoogleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

                mGoogleMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

                Geocoder geocoder = new Geocoder(getContext());
                List<Address> addresses;
                try {
                    addresses = geocoder.getFromLocation(latLng.latitude,latLng.longitude,1);
                    if(addresses.size() > 0) {

                        Address address = addresses.get(0);

                        mTvAdress.setText(getStringFromAddress(address));
                        Log.d("EVENT",address.toString());
                    }

                }catch (IOException e){

                }

            }
        });


//        mGoogleMap.setMyLocationEnabled(true);
    }

    private String getStringFromAddress(Address address){

        String res = "";
        for(int i = 0; i <= address.getMaxAddressLineIndex(); i++){
            res += address.getAddressLine(i);
        }
//        String result = address.getLocality()
//                + ", "
//                + address.getSubLocality()
//                + ", "
//                + address.getThoroughfare();

        return res;
    }

    @Override
    public void onResume() {
        mMapView.onResume();
        super.onResume();
        ActionBarUtil.setFragmentTitle(getActivity(),R.string.event_details);
    }

    @Override
    public void onDestroy() {
        mMapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        mMapView.onLowMemory();
        super.onLowMemory();
    }

    public static EventDetailFragment newInstance(Event event){

        EventDetailFragment fragment = new EventDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable("event",event);
        fragment.setArguments(args);
        return fragment;
    }
}
