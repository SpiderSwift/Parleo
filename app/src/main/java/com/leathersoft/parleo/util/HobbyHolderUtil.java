package com.leathersoft.parleo.util;

import com.leathersoft.parleo.messaging.Interest;
import com.leathersoft.parleo.network.model.Hobby;

import java.util.ArrayList;
import java.util.List;

public class HobbyHolderUtil {


    public static List<Hobby> serverHobbies = new ArrayList<>();

    public static void setServerHobbies(List<Hobby> hobbies) {
        serverHobbies = hobbies;
    }

    public static List<Interest> getHobbyModels(List<Hobby> userHobbies) {
        List<Interest> interests = new ArrayList<>();
        for (Hobby serverHobby : serverHobbies) {
            Interest interest = new Interest(serverHobby.getName());
            if (userHobbies.contains(serverHobby)) {
                interest.setChosen(1);
            }
            interests.add(interest);
        }
        return interests;
    }
}
