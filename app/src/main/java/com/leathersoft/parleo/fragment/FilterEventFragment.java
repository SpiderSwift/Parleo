package com.leathersoft.parleo.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.crystal.crystalrangeseekbar.widgets.CrystalRangeSeekbar;
import com.google.android.material.snackbar.Snackbar;
import com.leathersoft.parleo.R;
import com.leathersoft.parleo.activity.LanguageChooseWindowActivity;
import com.leathersoft.parleo.activity.LanguageWindowActivity;
import com.leathersoft.parleo.adapter.InterestsAdapter;
import com.leathersoft.parleo.messaging.Interest;
import com.leathersoft.parleo.messaging.LanguageModel;
import com.leathersoft.parleo.util.ActionBarUtil;
import com.leathersoft.parleo.util.LanguageHolderUtil;
import com.leathersoft.parleo.util.StorageUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FilterEventFragment extends BaseFragment {


    @BindView(R.id.seekBarMember) SeekBar barMember;
    @BindView(R.id.seekBarDistance) SeekBar barDistance;
    @BindView(R.id.tv_max_dist) TextView tvMaxDistance;
    @BindView(R.id.tv_max_member) TextView tvMaxMember;


    private int maxMember;
    private int maxDistance;
    private List<String> languageList;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.event_filter_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.event_filter_save:
                //Snackbar.make(getView(),"Saved",Snackbar.LENGTH_SHORT).show();

                StorageUtil.save(getContext(), "maxMemberEvent", maxMember);
                StorageUtil.save(getContext(), "maxDistanceEvent", maxDistance);
                StorageUtil.save(getContext(), "langListEvent", languageList);
                getActivity().onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
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


    @OnClick(R.id.tv_lang_set)
    public void setLanguages() {
        startActivityForResult(new Intent(getActivity().getApplicationContext(), LanguageChooseWindowActivity.class).putExtra("listLanguages", (Serializable) LanguageHolderUtil.getInstance().createModelList(languageList)), 300);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_filter_event, container, false);
        ButterKnife.bind(this, v);


        maxMember = StorageUtil.loadInt(getContext(), "maxMemberEvent");
        maxDistance = StorageUtil.loadInt(getContext(), "maxDistanceEvent");
        languageList = StorageUtil.loadList(getContext(), "langListEvent");

        tvMaxDistance.setText(String.valueOf(maxDistance));
        tvMaxMember.setText(String.valueOf(maxMember));

        barDistance.setProgress(maxDistance);
        barMember.setProgress(maxMember);




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


        barMember.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                maxMember = progress + 2;
                tvMaxMember.setText(String.valueOf(maxMember));
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
    public void onResume() {
        super.onResume();
        ActionBarUtil.setFragmentTitle(getActivity(),R.string.filter);
    }

    public static FilterEventFragment newInstance(){
        return new FilterEventFragment();
    }
}
