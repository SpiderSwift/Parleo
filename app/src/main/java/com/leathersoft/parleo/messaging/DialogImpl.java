package com.leathersoft.parleo.messaging;

import com.stfalcon.chatkit.commons.models.IDialog;
import com.stfalcon.chatkit.commons.models.IMessage;
import com.stfalcon.chatkit.commons.models.IUser;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class DialogImpl implements IDialog {

    public static IUser user = new IUser() {
        @Override
        public String getId() {
            return "userId";
        }

        @Override
        public String getName() {
            return "User Name";
        }

        @Override
        public String getAvatar() {
            return "user avatar";
        }
    };

    public static IUser user2 = new IUser() {
        @Override
        public String getId() {
            return "me";
        }

        @Override
        public String getName() {
            return "HEHE";
        }

        @Override
        public String getAvatar() {
            return "user avatar";
        }
    };

    public static IMessage message = new IMessage() {
        @Override
        public String getId() {
            return "gagga";
        }

        @Override
        public String getText() {
            return "asdjasdjnsdsdnvsjkvnjkds";
        }

        @Override
        public IUser getUser() {
            return user;
        }

        @Override
        public Date getCreatedAt() {
            return new Date();
        }
    };


    public static IMessage message2 = new IMessage() {
        @Override
        public String getId() {
            return "gagga";
        }

        @Override
        public String getText() {
            return "WAT";
        }

        @Override
        public IUser getUser() {
            return user2;
        }

        @Override
        public Date getCreatedAt() {
            return new Date();
        }
    };


    private static List<IUser> users = Collections.singletonList(user);



    @Override
    public String getId() {
        return "hi";
    }

    @Override
    public String getDialogPhoto() {
        return "hello there";
    }

    @Override
    public String getDialogName() {
        return "wow";
    }

    @Override
    public List<? extends IUser> getUsers() {
        return users;
    }

    @Override
    public IMessage getLastMessage() {
        return message;
    }

    @Override
    public void setLastMessage(IMessage message) {
        DialogImpl.message = message;
    }

    @Override
    public int getUnreadCount() {
        return 0;
    }
}
