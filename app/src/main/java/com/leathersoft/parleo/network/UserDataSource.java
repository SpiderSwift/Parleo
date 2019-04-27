package com.leathersoft.parleo.network;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.leathersoft.parleo.network.model.AccountResponse;
import com.leathersoft.parleo.network.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDataSource extends PageKeyedDataSource<Integer, User> {
    public static final int SIZE = 10;
    private static final int FIRST_PAGE = 0;
    private static final String SITE_NAME = "";

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, User> callback) {

        SingletonRetrofitClient.getInsance()
                .getApi()
                .getUsers(
                        null,
                        null,
                        null,
                        null,
                        null,
                        FIRST_PAGE,
                        SIZE
                ).enqueue(new Callback<AccountResponse>() {
            @Override
            public void onResponse(Call<AccountResponse> call, Response<AccountResponse> response) {
                if(response.body() != null){
                    AccountResponse accountResponse = response.body();
                    List<User> users = accountResponse.getEntities();
                    callback.onResult(users,null, FIRST_PAGE + 1);
                }
            }

            @Override
            public void onFailure(Call<AccountResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, User> callback) {

        SingletonRetrofitClient.getInsance()
                .getApi()
                .getUsers(
                        null,
                        null,
                        null,
                        null,
                        null,
                        params.key,
                        SIZE
                ).enqueue(new Callback<AccountResponse>() {
            @Override
            public void onResponse(Call<AccountResponse> call, Response<AccountResponse> response) {

                Integer key = (params.key > 1) ? params.key -1 : null;

                if(response.body() != null){
                    callback.onResult(response.body().getEntities(),key);
                }
            }

            @Override
            public void onFailure(Call<AccountResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, User> callback) {

        SingletonRetrofitClient.getInsance()
                .getApi()
                .getUsers(
                        null,
                        null,
                        null,
                        null,
                        null,
                        params.key,
                        SIZE
                ).enqueue(new Callback<AccountResponse>() {
            @Override
            public void onResponse(Call<AccountResponse> call, Response<AccountResponse> response) {
                Integer pageNumber = response.body().getPageNumber() + 1;
                Integer pageSize = response.body().getPageSize();


                Integer totalAmount = response.body().getTotalAmount();

                Integer key;
                if( pageNumber*pageSize >= totalAmount){
                    key = null;
                }else {
                    key = params.key + 1;
                }

                if(response.body() != null){
                    callback.onResult(
                            response.body().getEntities(),key
                    );
                }
            }

            @Override
            public void onFailure(Call<AccountResponse> call, Throwable t) {

            }
        });
    }
}
