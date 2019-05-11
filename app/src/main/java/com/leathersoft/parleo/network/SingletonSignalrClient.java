package com.leathersoft.parleo.network;

import android.util.Log;

import com.leathersoft.parleo.network.model.MessageViewModel;
import com.microsoft.signalr.HubConnection;
import com.microsoft.signalr.HubConnectionBuilder;

public class SingletonSignalrClient {

    private static SingletonSignalrClient instance;

    private static final String HUB_URL = "https://awesomeparleobackend.azurewebsites.net/chathub";
    public static final String SEND_COMMAND = "SendMessage";
    public static final String RECEIVE_SUBSCRIPTION = "receiveMessage";
    public static final String CHAT_SUBSCRIBE_COMMAND = "SubscribeToChat";
    public static final String CHATS_SUBSCRIBE_COMMAND = "SubscribeToChats";

    public static String currentId = "";


    public static void setCurrentId(String currentId) {
        SingletonSignalrClient.currentId = currentId;
    }

    private HubConnection connection;

    private SingletonSignalrClient() {
        connection = HubConnectionBuilder
                .create(HUB_URL)
                .build();
        connection.start();
    }


    public static SingletonSignalrClient getInstance() {
        if (instance == null) {
            instance = new SingletonSignalrClient();
        }
        return instance;
    }

    public HubConnection getConnection() {
        return connection;
    }

//    Send message example
//    MessageViewModel messageViewModel = new MessageViewModel();
//    messageViewModel.setText("ALLO 2");
//    messageViewModel.setSenderId("53c812a4-bf18-4b98-881c-7abfe78df260");
//    messageViewModel.setChatId("a4882691-b8ae-440a-8dc8-056a58e67bbe");
//    messageViewModel.setCreatedOn(new Date());
//    connection.send("SendMessage", messageViewModel);


//    send subscribe to chat example
//    connection.send("SubscribeToChat", "a4882691-b8ae-440a-8dc8-056a58e67bbe");


//    subscription to receive messages example
//    connection.on("receiveMessage", model -> {
//        Log.d("TAG", model.toString());
//    }, MessageViewModel.class);
}
