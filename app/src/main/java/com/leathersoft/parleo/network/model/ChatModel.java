package com.leathersoft.parleo.network.model;

import com.stfalcon.chatkit.commons.models.IUser;

import java.util.List;

public class ChatModel {

    private String id;
    private String name;
    private String image;
    private List<ChatMember> members;
    private MessageViewModel lastMessage;
    private int unreadMessages;

    public ChatModel() {
    }


    public ChatModel(String id, String name, String image, List<ChatMember> members, MessageViewModel lastMessage, int unreadMessages) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.members = members;
        this.lastMessage = lastMessage;
        this.unreadMessages = unreadMessages;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<ChatMember> getMembers() {
        return members;
    }


    public List<? extends IUser> getUsers() {
        return members;
    }

    public void setMembers(List<ChatMember> members) {
        this.members = members;
    }

    public MessageViewModel getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(MessageViewModel lastMessage) {
        this.lastMessage = lastMessage;
    }

    public int getUnreadMessages() {
        return unreadMessages;
    }

    public void setUnreadMessages(int unreadMessages) {
        this.unreadMessages = unreadMessages;
    }

    //{
    //  "entities": [
    //    {
    //      "id": "58c60d21-6b31-4b98-8c4d-fb4bdcdce028",
    //      "name": "SUPPA PUPPER TUSA",
    //      "image": "https://awesomeparleobackend.azurewebsites.net/event-images/cd2da173-e30e-4986-b46d-57601a8466e2.jpg",
    //      "members": [
    //        {
    //          "id": "44acf3c2-ca59-4fef-bf41-14a6a740610a",
    //          "image": "https://awesomeparleobackend.azurewebsites.net/account-images/217ffdfd-e6d0-424d-a8d9-b8a1b78dface.jpg",
    //          "name": "grgf"
    //        },
    //        {
    //          "id": "5ec4ec5f-7b0c-4cd0-ad8f-4b826a201d43",
    //          "image": "https://awesomeparleobackend.azurewebsites.net/account-images/654aa2f4-1449-477e-937a-4bc274ea44e4.jpg",
    //          "name": "Kirill Varikov"
    //        }
    //      ],
    //      "event": {
    //        "id": "f65f7c2b-10ce-4b23-b21b-4cdcbb298948",
    //        "image": "https://awesomeparleobackend.azurewebsites.net/event-images/cd2da173-e30e-4986-b46d-57601a8466e2.jpg",
    //        "name": "SUPPA PUPPER TUSA"
    //      },
    //      "creatorId": "5ec4ec5f-7b0c-4cd0-ad8f-4b826a201d43",
    //      "lastMessage": {
    //        "id": 21,
    //        "text": "сучки",
    //        "status": "new",
    //        "createdOn": "2019-05-10T11:41:18.945+00:00",
    //        "viewedOn": null,
    //        "isDeleted": false,
    //        "senderId": "5ec4ec5f-7b0c-4cd0-ad8f-4b826a201d43",
    //        "chatId": "58c60d21-6b31-4b98-8c4d-fb4bdcdce028"
    //      },
    //      "unreadMessages": 0
    //    },
    //    {
    //      "id": "65140b5b-3b90-41d3-874a-e5c9daf38b1e",
    //      "name": "Kate",
    //      "image": "https://awesomeparleobackend.azurewebsites.net/account-images/4e7893da-4bd2-491e-af91-1e346dbcabd5.jpg",
    //      "members": [
    //        {
    //          "id": "44acf3c2-ca59-4fef-bf41-14a6a740610a",
    //          "image": "https://awesomeparleobackend.azurewebsites.net/account-images/217ffdfd-e6d0-424d-a8d9-b8a1b78dface.jpg",
    //          "name": "grgf"
    //        },
    //        {
    //          "id": "bdff5e1c-4008-4f76-a209-3ff765506f47",
    //          "image": "https://awesomeparleobackend.azurewebsites.net/account-images/4e7893da-4bd2-491e-af91-1e346dbcabd5.jpg",
    //          "name": "Kate"
    //        }
    //      ],
    //      "event": null,
    //      "creatorId": null,
    //      "lastMessage": null,
    //      "unreadMessages": 0
    //    }
    //  ],
    //  "pageNumber": 1,
    //  "pageSize": 100,
    //  "totalAmount": 2,
    //  "timeStamp": "2019-05-10T21:09:17.9381653+00:00"
    //}

}
