package com.leathersoft.parleo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.leathersoft.parleo.R;
import com.leathersoft.parleo.activity.ChatActivity;
import com.leathersoft.parleo.activity.auth.RegistrationFinishActivity;
import com.leathersoft.parleo.messaging.DialogImpl;
import com.leathersoft.parleo.network.SingletonRetrofitClient;
import com.leathersoft.parleo.network.SingletonSignalrClient;
import com.leathersoft.parleo.network.model.ChatListModel;
import com.leathersoft.parleo.network.model.ChatMember;
import com.leathersoft.parleo.network.model.ChatModel;
import com.leathersoft.parleo.network.model.MessageViewModel;
import com.leathersoft.parleo.util.ActionBarUtil;
import com.leathersoft.parleo.util.ChatUtil;
import com.leathersoft.parleo.util.ImageUtil;
import com.microsoft.signalr.Action1;
import com.microsoft.signalr.HubConnection;
import com.microsoft.signalr.Subscription;
import com.squareup.picasso.Picasso;
import com.stfalcon.chatkit.commons.ImageLoader;
import com.stfalcon.chatkit.commons.models.IDialog;
import com.stfalcon.chatkit.commons.models.IMessage;
import com.stfalcon.chatkit.commons.models.IUser;
import com.stfalcon.chatkit.dialogs.DialogsList;
import com.stfalcon.chatkit.dialogs.DialogsListAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ChatFragment extends BaseFragment {

    @BindView(R.id.list_dialog)
    DialogsList dialogsList;

    private DialogsListAdapter<DialogImpl> adapter;

    //private Subscription on;

    private HubConnection hubConnection = SingletonSignalrClient.getInstance().getConnection();

    Action1<MessageViewModel> onMessage = (model) -> {
        Log.d("TAG", "UPDATE");
        adapter.updateDialogWithMessage(model.getChatId(), new IMessage() {
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
        });
    };

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_dialog, container, false);
        ButterKnife.bind(this, v);
        adapter = new DialogsListAdapter<>(R.layout.item_chat, (imageView, url, payload) -> ImageUtil.setImage(url,imageView,R.drawable.billy));
        dialogsList.setAdapter(adapter);

        adapter.setOnDialogClickListener(dialog -> startActivity(new Intent(getActivity(), ChatActivity.class).putExtra("chatId", dialog.getId())));
        return v;
    }

    @Override
    public void onResume() {

        Log.d("TAG", "onResume");
        //on = hubConnection.on(SingletonSignalrClient.RECEIVE_SUBSCRIPTION, onMessage, MessageViewModel.class);

        SingletonRetrofitClient.getInsance().getApi().getChats(1,100)
                .enqueue(new Callback<ChatListModel>() {
                    @Override
                    public void onResponse(Call<ChatListModel> call, Response<ChatListModel> response) {
                        if (response.isSuccessful()) {
                            ChatListModel listModel = response.body();
                            ChatUtil.getInstance().clearMap();
                            ChatUtil.getInstance().fillMap(listModel.getEntities());
                            List<DialogImpl> dialogs = new ArrayList<>();
                            for (ChatModel chatModel : listModel.getEntities()) {
                                List<IUser> users = new ArrayList<>(chatModel.getMembers());
                                hubConnection.send(SingletonSignalrClient.CHAT_SUBSCRIBE_COMMAND, chatModel.getId());
                                IMessage lastMessage;
                                if (chatModel.getLastMessage() != null) {
                                    lastMessage  = new IMessage() {
                                        @Override
                                        public String getId() {
                                            return String.valueOf(chatModel.getLastMessage().getId());
                                        }

                                        @Override
                                        public String getText() {
                                            return chatModel.getLastMessage().getText();
                                        }

                                        @Override
                                        public IUser getUser() {
                                            return ChatUtil.getInstance().findByIds(chatModel.getId(), chatModel.getLastMessage().getSenderId());
                                        }

                                        @Override
                                        public Date getCreatedAt() {
                                            return chatModel.getLastMessage().getCreatedOn();
                                        }
                                    };
                                } else {
                                    lastMessage = null;
                                }
                                dialogs.add(new DialogImpl(users, chatModel.getName(), chatModel.getImage(), chatModel.getId(), chatModel.getUnreadMessages(), lastMessage));
                            }

                            adapter.clear();
                            for (DialogImpl dialog : dialogs) {
                                adapter.addItem(dialog);
                            }
                        }

                    }

                    @Override
                    public void onFailure(Call<ChatListModel> call, Throwable t) {

                    }
                });
        ActionBarUtil.setFragmentTitle(getActivity(),R.string.chats);
        super.onResume();
    }

    @Override
    public void onStop() {
        //hubConnection.remove(SingletonSignalrClient.RECEIVE_SUBSCRIPTION);
        Log.d("TAG", "onStop");
//        on.unsubscribe();
//        on = null;
        super.onStop();
    }

    public static ChatFragment newInstance(){
        return new ChatFragment();
    }


}
