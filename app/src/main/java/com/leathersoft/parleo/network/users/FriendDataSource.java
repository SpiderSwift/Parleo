package com.leathersoft.parleo.network.users;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.leathersoft.parleo.MainApplication;
import com.leathersoft.parleo.network.SingletonRetrofitClient;
import com.leathersoft.parleo.network.model.AccountResponse;
import com.leathersoft.parleo.network.model.User;
import com.leathersoft.parleo.network.users.UserDataSource;
import com.leathersoft.parleo.util.StorageUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FriendDataSource extends UserDataSource {

    @Override
    protected Call<AccountResponse> getUserCall(Integer page, Integer pageSize){

        return SingletonRetrofitClient.getInsance()
                .getApi()
                .getFriends(
                        page,
                        pageSize
                );
    }

}
