package com.leathersoft.parleo.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.leathersoft.parleo.R;
import com.leathersoft.parleo.activity.MessagesActivity;
import com.leathersoft.parleo.messaging.DialogImpl;
import com.stfalcon.chatkit.commons.ImageLoader;
import com.stfalcon.chatkit.commons.models.IDialog;
import com.stfalcon.chatkit.dialogs.DialogsList;
import com.stfalcon.chatkit.dialogs.DialogsListAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;


public class DialogFragment extends BaseFragment {

    @BindView(R.id.list_dialog)
    DialogsList dialogsList;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dialog, container, false);
        ButterKnife.bind(this, v);
        DialogsListAdapter adapter = new DialogsListAdapter<DialogImpl>(R.layout.item_dialog, new ImageLoader() {
            @Override
            public void loadImage(ImageView imageView, @Nullable String url, @Nullable Object payload) {
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.billy));
            }
        });
        adapter.addItem(new DialogImpl());
        adapter.addItem(new DialogImpl());
        adapter.addItem(new DialogImpl());
        adapter.addItem(new DialogImpl());
        adapter.addItem(new DialogImpl());
        adapter.addItem(new DialogImpl());
        adapter.addItem(new DialogImpl());
        adapter.addItem(new DialogImpl());
        adapter.addItem(new DialogImpl());
        adapter.addItem(new DialogImpl());
        adapter.addItem(new DialogImpl());
        adapter.addItem(new DialogImpl());
        adapter.addItem(new DialogImpl());
        adapter.addItem(new DialogImpl());
        adapter.addItem(new DialogImpl());
        dialogsList.setAdapter(adapter);
        adapter.setOnDialogClickListener(new DialogsListAdapter.OnDialogClickListener() {
            @Override
            public void onDialogClick(IDialog dialog) {
                startActivity(new Intent(getActivity().getApplicationContext(), MessagesActivity.class));
            }
        });
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        Activity activity = getActivity();
        if(activity != null){
            getActivity().setTitle(getResources().getString(R.string.chats));
        }

    }

    public static DialogFragment newInstance(){
        return new DialogFragment();
    }


}
