package com.leathersoft.parleo.util;

import com.leathersoft.parleo.messaging.DialogImpl;
import com.leathersoft.parleo.network.model.ChatModel;
import com.stfalcon.chatkit.commons.models.IUser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatUtil {

    private static final ChatUtil instance = new ChatUtil();

    private Map<String, ChatModel> dialogMap;

    private ChatUtil() {
        dialogMap = new HashMap<>();
    }

    public static ChatUtil getInstance() {
        return instance;
    }


    public void clearMap() {
        dialogMap = new HashMap<>();
    }

    public void fillMap(List<ChatModel> dialogList) {
        for (ChatModel dialog : dialogList) {
            dialogMap.put(dialog.getId(), dialog);
        }
    }

    public IUser findByIds(String dialogId, String userId) {
        ChatModel dialog = dialogMap.get(dialogId);
        if (dialog != null) {
            for (IUser user : dialog.getUsers()) {
                if (user.getId().equals(userId)) {
                    return user;
                }
            }
        }
        return null;
    }

}
