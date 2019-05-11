package com.leathersoft.parleo.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.leathersoft.parleo.R;
import com.leathersoft.parleo.messaging.DialogImpl;
import com.leathersoft.parleo.network.SingletonRetrofitClient;
import com.leathersoft.parleo.network.SingletonSignalrClient;
import com.leathersoft.parleo.network.model.ChatListModel;
import com.leathersoft.parleo.network.model.ChatModel;
import com.leathersoft.parleo.network.model.MessageListModel;
import com.leathersoft.parleo.network.model.MessageViewModel;
import com.leathersoft.parleo.util.ChatUtil;
import com.leathersoft.parleo.util.ImageUtil;
import com.microsoft.signalr.HubConnection;
import com.microsoft.signalr.Subscription;
import com.stfalcon.chatkit.commons.ImageLoader;
import com.stfalcon.chatkit.commons.models.IMessage;
import com.stfalcon.chatkit.commons.models.IUser;
import com.stfalcon.chatkit.messages.MessageInput;
import com.stfalcon.chatkit.messages.MessagesList;
import com.stfalcon.chatkit.messages.MessagesListAdapter;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Collections;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChatActivity extends AppCompatActivity {

    @BindView(R.id.input_message) MessageInput messageInput;
    @BindView(R.id.list_message) MessagesList messagesList;

    SingletonRetrofitClient retrofitClient = SingletonRetrofitClient.getInsance();
    MessagesListAdapter adapter;
    HubConnection connection = SingletonSignalrClient.getInstance().getConnection();
    String chatId;
    Subscription on;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
        chatId = getIntent().getStringExtra("chatId");
        connection.send(SingletonSignalrClient.CHAT_SUBSCRIBE_COMMAND, chatId);
        adapter = new MessagesListAdapter<>(SingletonSignalrClient.currentId, (imageView, url, payload) -> ImageUtil.setImage(url, imageView, R.drawable.billy));
        retrofitClient.getApi().getChat(getIntent().getStringExtra("chatId"))
                .enqueue(new Callback<ChatModel>() {
                    @Override
                    public void onResponse(Call<ChatModel> call, Response<ChatModel> response) {
                        if (response.isSuccessful()) {
                            ChatModel model = response.body();
                            ChatUtil.getInstance().clearMap();
                            ChatUtil.getInstance().fillMap(Collections.singletonList(model));
                            retrofitClient.getApi().getMessages(getIntent().getStringExtra("chatId"),1,100)
                                    .enqueue(new Callback<MessageListModel>() {
                                        @Override
                                        public void onResponse(Call<MessageListModel> call, Response<MessageListModel> response) {
                                            if (response.isSuccessful()) {
                                                MessageListModel messageListModel = response.body();
                                                for (MessageViewModel messageViewModel : messageListModel.getEntities()) {
                                                    adapter.addToEnd(Collections.singletonList(new IMessage() {
                                                        @Override
                                                        public String getId() {
                                                            return String.valueOf(messageViewModel.getId());
                                                        }

                                                        @Override
                                                        public String getText() {
                                                            return messageViewModel.getText();
                                                        }

                                                        @Override
                                                        public IUser getUser() {
                                                            return ChatUtil.getInstance().findByIds(messageViewModel.getChatId(), messageViewModel.getSenderId());
                                                        }

                                                        @Override
                                                        public Date getCreatedAt() {
                                                            return messageViewModel.getCreatedOn();
                                                        }
                                                    }),false);
                                                }
                                                messagesList.setAdapter(adapter);
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<MessageListModel> call, Throwable t) {

                                        }
                                    });
                        }
                    }

                    @Override
                    public void onFailure(Call<ChatModel> call, Throwable t) {

                    }
                });


        on = connection.on(SingletonSignalrClient.RECEIVE_SUBSCRIPTION, model -> {
            Log.d("TAAAAAA", model.toString());
            runOnUiThread(() -> {
                adapter.addToStart(new IMessage() {
                    @Override
                    public String getId() {
                        return String.valueOf(model.getId());
                    }

                    @Override
                    public String getText() {
                        return model.getText();
                    }

                    @Override
                    public IUser getUser() {
                        return ChatUtil.getInstance().findByIds(model.getChatId(), model.getSenderId());
                    }

                    @Override
                    public Date getCreatedAt() {
                        return model.getCreatedOn();
                    }
                }, true);
            });
        }, MessageViewModel.class);



        messageInput.setInputListener(input -> {
            MessageViewModel messageViewModel = new MessageViewModel();
            messageViewModel.setText(input.toString());
            messageViewModel.setSenderId(SingletonSignalrClient.currentId);
            messageViewModel.setChatId(chatId);
            messageViewModel.setCreatedOn(new Date());
            connection.send(SingletonSignalrClient.SEND_COMMAND, messageViewModel);
            return true;
        });

    }


    @Override
    protected void onDestroy() {
        on.unsubscribe();
        on = null;
        super.onDestroy();
    }
}
