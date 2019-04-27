package com.leathersoft.parleo.network;

import androidx.annotation.Nullable;

import java.util.List;

public class User {

    String id;
    String accountImage;
    String name;
    String about;
    //birthdate
    boolean gender;
    int distanceFromCurrentUser;
    String email;


    List<Language> languages;
    List<Hobby> hobbies;

//     "id": "a7727de6-8583-4479-99dc-00fa25972ac1",
//             "accountImage": null,
//             "name": null,
//             "about": null,
//             "birthdate": "0001-01-01T00:00:00",
//             "gender": false,
//             "distanceFromCurrentUser": 0,
//             "email": "npofa4ok@mail.ru",
//             "createdEvents": [],
//             "languages": [],
//             "friends": [],
//             "attendingEvents": [],
//             "hobbies": []


    @Override
    public boolean equals(@Nullable Object obj) {
        return super.equals(obj);
    }
}
