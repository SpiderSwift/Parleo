package com.leathersoft.parleo.network;

import com.leathersoft.parleo.network.model.AccountResponse;
import com.leathersoft.parleo.network.model.CreateEventModel;
import com.leathersoft.parleo.network.model.Event;
import com.leathersoft.parleo.network.model.EventResponse;
import com.leathersoft.parleo.network.model.Lang;
import com.leathersoft.parleo.network.model.Language;
import com.leathersoft.parleo.network.model.LanguageResponse;
import com.leathersoft.parleo.network.model.LoginResponse;
import com.leathersoft.parleo.network.model.LoginViewModel;
import com.leathersoft.parleo.network.model.RegisterViewModel;
import com.leathersoft.parleo.network.model.User;
import com.leathersoft.parleo.network.model.UserUpdateModel;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface Api {

    @POST("Accounts/register")
    Call<ResponseBody> register(@Body RegisterViewModel registerViewModel);

    @POST("Accounts/login")
    Call<LoginResponse> login(@Body LoginViewModel loginViewModel);



    @GET("Utilities/languages")
    Call<List<Lang>> getLanguages();

    @GET("Users")
    Call<AccountResponse> getUsers(
            @Query("MinAge") Integer minAge,
            @Query("MaxAge") Integer maxAge,
            @Query("Gender") Boolean gender,
            @Query("MaxDistance") Integer maxDistance,
            @Query("MinLevel") Integer minLevel,

            @Query("PageNumber") Integer page,
            @Query("PageSize") Integer pageSize
    );

    @PUT("Users/{userId}")
    Call<AccountResponse> updateUser(
            @Path("userId") String userId,
            @Body UserUpdateModel userUpdateModel
            );



    @GET("Users/current")
    Call<User> getMe();



    @GET("Events")
    Call<EventResponse> getEvents(
            @Query("MinNumberOfParticipants") Integer minNumberOfParticipants,
            @Query("MaxNumberOfParticipants") Integer maxNumberOfParticipants,
            @Query("MaxDistance") Integer maxDistance,
            @Query("Languages") List<Language> languages,
//            @Query("MinStartDate") Data minNumberOfParticipants,
//            @Query("MaxStartDate") Integer maxStartDate,
            @Query("PageNumber") Integer page,
            @Query("PageSize") Integer pageSize
    );

    @GET("Events/{eventId}")
    Call<Event> getEvent(
            @Path("eventId") String eventId
    );

    @POST("Events/create")
    Call<Event> postEvent(@Body CreateEventModel createEventModel);

    @Multipart
    @PUT("Events/{eventId}/image")
    Call<ResponseBody> putImage(@Path("eventId") String eventId,
                                @Part MultipartBody.Part filePart);


//    {
//  "name": "Cool event",
//  "description": "10/10 you should come",
//  "maxParticipants": 10,
//  "latitude": 23,
//  "longitude": 11,
//  "isFinished": false,
//  "startTime": "2019-04-28T21:25:41.746Z",
//  "endDate": "2019-04-28T21:25:41.746Z",
//  "creatorId": "4377a0f9-f4d8-45b0-b263-0149dda2d634",
//  "languageCode": "aa"
//}
}
