package com.leathersoft.parleo.network;

import com.leathersoft.parleo.network.model.AccountResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("Account")
    Call<AccountResponse> getUsers(
            @Query("MinAge") Integer minAge,
            @Query("MaxAge") Integer maxAge,
            @Query("Gender") Boolean gender,
            @Query("MaxDistance") Integer maxDistance,
            @Query("MinLevel") Integer minLevel,

            @Query("Page") Integer page,
            @Query("PageSize") Integer pageSize
    );
}
