package com.leathersoft.parleo.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.TextView;

import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;
import com.google.android.material.snackbar.Snackbar;
import com.leathersoft.parleo.MainApplication;
import com.leathersoft.parleo.R;
import com.leathersoft.parleo.activity.LanguageChooseWindowActivity;
import com.leathersoft.parleo.adapter.InterestsAdapter;
import com.leathersoft.parleo.messaging.Interest;
import com.leathersoft.parleo.messaging.LanguageModel;
import com.leathersoft.parleo.network.SingletonSignalrClient;
import com.leathersoft.parleo.util.ActionBarUtil;
import com.leathersoft.parleo.util.LanguageHolderUtil;
import com.leathersoft.parleo.util.StorageUtil;
import com.microsoft.signalr.HubConnection;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class FilterUserFragment extends BaseFragment {


    @BindView(R.id.range_bar) CrystalRangeSeekbar barAge;
    @BindView(R.id.tv_min) TextView min;
    @BindView(R.id.tv_max) TextView max;

    @BindView(R.id.seekBarLevel) SeekBar barLevel;
    @BindView(R.id.seekBarDistance) SeekBar barDistance;
    @BindView(R.id.tv_max_dist) TextView tvMaxDistance;
    @BindView(R.id.cb_two) CheckBox cbMale;
    @BindView(R.id.cb_many) CheckBox cbFemale;
    @BindView(R.id.tv_min_level) TextView tvMinLevel;


    private int minAge;
    private int maxAge;
    private int maxDistance;
    private int languageLevel;
    private List<String> languageList;
    private boolean male;
    private boolean female;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.save_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_save:

                StorageUtil.save(MainApplication.getAppContext(), "minAge", minAge);
                StorageUtil.save(MainApplication.getAppContext(), "maxAge", maxAge);
                StorageUtil.save(MainApplication.getAppContext(), "maxDistanceUser", maxDistance);
                StorageUtil.save(MainApplication.getAppContext(), "langListUser", languageList);
                StorageUtil.save(MainApplication.getAppContext(), "langLevelUser", languageLevel);
                StorageUtil.save(MainApplication.getAppContext(), "male", male);
                StorageUtil.save(MainApplication.getAppContext(), "female", female);

                getActivity().onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @OnClick(R.id.tv_lang_set)
    public void setLanguages() {
        startActivityForResult(new Intent(getActivity().getApplicationContext(), LanguageChooseWindowActivity.class).putExtra("listLanguages", (Serializable) LanguageHolderUtil.getInstance().createModelList(languageList)), 300);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_filter_user, container, false);
        ButterKnife.bind(this, v);


        minAge = StorageUtil.loadInt(MainApplication.getAppContext(), "minAge", 16);
        maxAge = StorageUtil.loadInt(MainApplication.getAppContext(), "maxAge", 25);
        maxDistance = StorageUtil.loadInt(MainApplication.getAppContext(), "maxDistanceUser", 200);
        languageList = StorageUtil.loadList(MainApplication.getAppContext(), "langListUser");
        languageLevel = StorageUtil.loadInt(MainApplication.getAppContext(), "langLevelUser", 2);
        male = StorageUtil.loadBooalen(MainApplication.getAppContext(), "male", true);
        female = StorageUtil.loadBooalen(MainApplication.getAppContext(), "female", true);

        barAge.setMaxValue(100);
        barAge.setMinValue(16);
        barAge.setMaxStartValue(maxAge);
        barAge.setMinStartValue(minAge);

        cbFemale.setChecked(female);
        cbMale.setChecked(male);

        barLevel.setProgress(languageLevel);
        barDistance.setProgress(maxDistance);

        tvMaxDistance.setText(String.valueOf(200));

        tvMinLevel.setText(LanguageModel.langLevelMap.get(languageLevel));

        cbFemale.setOnCheckedChangeListener((buttonView, isChecked) -> female = isChecked);
        cbMale.setOnCheckedChangeListener((buttonView, isChecked) -> male = isChecked);


        barAge.apply();
        barAge.setOnRangeSeekbarChangeListener((minValue, maxValue) -> {
            max.setText("" + maxValue.intValue());
            minAge = minValue.intValue();
            min.setText("" + minValue.intValue());
            maxAge = maxValue.intValue();
        });


        barLevel.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                languageLevel = progress;
                tvMinLevel.setText(LanguageModel.langLevelMap.get(languageLevel));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        barDistance.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                maxDistance = progress + 1;
                tvMaxDistance.setText(String.valueOf(maxDistance));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        return v;

    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (data == null) {
            return;
        }

        List<LanguageModel> languageModels = (List<LanguageModel>) data.getSerializableExtra("listLanguages");
        if (languageModels != null) {
            languageList = LanguageHolderUtil.getInstance().createKeyList(languageModels);
        }


        //super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onResume() {
        super.onResume();
        ActionBarUtil.setFragmentTitle(getActivity(),R.string.filter);
    }


    @Override
    public void onStop() {
        super.onStop();
    }

    public static FilterUserFragment newInstance(){
        return new FilterUserFragment();
    }
}
