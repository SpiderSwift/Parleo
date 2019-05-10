package com.leathersoft.parleo.activity;

import android.os.Bundle;
import android.widget.ImageView;

import com.leathersoft.parleo.R;
import com.leathersoft.parleo.messaging.DialogImpl;
import com.stfalcon.chatkit.commons.ImageLoader;
import com.stfalcon.chatkit.messages.MessageInput;
import com.stfalcon.chatkit.messages.MessagesList;
import com.stfalcon.chatkit.messages.MessagesListAdapter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ChatActivity extends AppCompatActivity {

    @BindView(R.id.input_message) MessageInput messageInput;
    @BindView(R.id.list_message) MessagesList messagesList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
        MessagesListAdapter adapter = new MessagesListAdapter("me", new ImageLoader() {
            @Override
            public void loadImage(ImageView imageView, @Nullable String url, @Nullable Object payload) {
                imageView.setImageDrawable(getResources().getDrawable(R.drawable.billy));
            }
        });
        //adapter.addToStart(DialogImpl.message, false);
        //adapter.addToStart(DialogImpl.message2, false);
        //adapter.addToStart(DialogImpl.message2, false);
        //adapter.addToStart(DialogImpl.message2, false);
        //adapter.addToStart(DialogImpl.message, false);
        messagesList.setAdapter(adapter);
    }
}
