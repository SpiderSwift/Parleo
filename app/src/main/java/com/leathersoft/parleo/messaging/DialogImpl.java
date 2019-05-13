package com.leathersoft.parleo.messaging;

import com.stfalcon.chatkit.commons.models.IDialog;
import com.stfalcon.chatkit.commons.models.IMessage;
import com.stfalcon.chatkit.commons.models.IUser;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class DialogImpl implements IDialog {

    private IMessage lastMessage;
    private List<IUser> users;
    private String dialogName;
    private String dialogPhoto;
    private String id;
    private int unreadCount;


    public DialogImpl(String id) {
        this.id = id;
    }

    public DialogImpl(List<IUser> users, String dialogName, String dialogPhoto, String id, int unreadCount, IMessage lastMessage) {
        this.users = users;
        this.dialogName = dialogName;
        this.dialogPhoto = dialogPhoto;
        this.id = id;
        this.unreadCount = unreadCount;
        this.lastMessage = lastMessage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DialogImpl dialog = (DialogImpl) o;
        return Objects.equals(id, dialog.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getDialogPhoto() {
        return dialogPhoto;
    }

    @Override
    public String getDialogName() {
        return dialogName;
    }

    @Override
    public List<? extends IUser> getUsers() {
        return users;
    }

    @Override
    public IMessage getLastMessage() {
        return lastMessage;
    }

    @Override
    public void setLastMessage(IMessage message) {
        this.lastMessage = message;
    }

    @Override
    public int getUnreadCount() {
        return unreadCount;
    }
}
