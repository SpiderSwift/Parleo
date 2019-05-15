package com.leathersoft.parleo.network.users;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.leathersoft.parleo.MainApplication;
import com.leathersoft.parleo.network.SingletonRetrofitClient;
import com.leathersoft.parleo.network.model.AccountResponse;
import com.leathersoft.parleo.network.model.User;
import com.leathersoft.parleo.util.StorageUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserDataSource extends PageKeyedDataSource<Integer, User> {

    private int minAge;
    private int maxAge;
    private int maxDistance;
    private int languageLevel;
    private List<String> languageList;
    private boolean male;
    private boolean female;

    public static final int PAGE_SIZE = 5;
    private static final int FIRST_PAGE = 1;

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, User> callback) {
            getUserCall(FIRST_PAGE,PAGE_SIZE)
                .enqueue(new Callback<AccountResponse>() {
            @Override
            public void onResponse(Call<AccountResponse> call, Response<AccountResponse> response) {
                if(response.body() != null){
                    AccountResponse accountResponse = response.body();
                    callback.onResult(
                            accountResponse.getEntities(),
                            null,
                            FIRST_PAGE + 1);
                }
            }

            @Override
            public void onFailure(Call<AccountResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, User> callback) {

        getUserCall(params.key,PAGE_SIZE)
                .enqueue(new Callback<AccountResponse>() {
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

        getUserCall(params.key,PAGE_SIZE)
                .enqueue(new Callback<AccountResponse>() {
            @Override
            public void onResponse(Call<AccountResponse> call, Response<AccountResponse> response) {
                Integer pageNumber = response.body().getPageNumber();
                Integer pageSize = response.body().getPageSize();


                Integer totalAmount = response.body().getTotalAmount();

                Integer key;
                if(pageNumber * pageSize >= totalAmount){
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

    protected Call<AccountResponse> getUserCall(Integer page, Integer pageSize){

        minAge = StorageUtil.loadInt(MainApplication.getAppContext(), "minAge", 16);
        maxAge = StorageUtil.loadInt(MainApplication.getAppContext(), "maxAge", 25);
        maxDistance = StorageUtil.loadInt(MainApplication.getAppContext(), "maxDistanceUser", 200);
        languageList = StorageUtil.loadList(MainApplication.getAppContext(), "langListUser");
        languageLevel = StorageUtil.loadInt(MainApplication.getAppContext(), "langLevelUser", 2);
        male = StorageUtil.loadBooalen(MainApplication.getAppContext(), "male", true);
        female = StorageUtil.loadBooalen(MainApplication.getAppContext(), "female", true);

        Boolean gender = null;
        if (male || female) {
            gender = male;
        }

        if (languageList.isEmpty()) {
            languageList = null;
        }

        return SingletonRetrofitClient.getInsance()
                .getApi()
                .getUsers(
                        minAge,
                        maxAge,
                        gender,
                        maxDistance,
                        languageLevel + 1,
                        languageList,
                        page,
                        pageSize
                );
    }

}
