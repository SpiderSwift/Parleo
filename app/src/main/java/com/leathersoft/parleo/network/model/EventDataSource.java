package com.leathersoft.parleo.network.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.leathersoft.parleo.MainApplication;
import com.leathersoft.parleo.network.SingletonRetrofitClient;
import com.leathersoft.parleo.util.StorageUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventDataSource extends PageKeyedDataSource<Integer, Event> {

    public static final int PAGE_SIZE = 5;
    private static final int FIRST_PAGE = 1;

    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull LoadInitialCallback<Integer, Event> callback) {

        getEventCall(FIRST_PAGE,PAGE_SIZE)
                .enqueue(new Callback<EventResponse>() {
                    @Override
                    public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
                        if(response.body() != null){
                            callback.onResult(
                                    response.body().getEntities(),
                                    null,
                                    FIRST_PAGE + 1
                            );
                        }
                    }

                    @Override
                    public void onFailure(Call<EventResponse> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadBefore(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Event> callback) {
        getEventCall(params.key,PAGE_SIZE)
                .enqueue(new Callback<EventResponse>() {
                    @Override
                    public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {
                        Integer key = (params.key > 1) ? params.key -1 : null;

                        if(response.body() != null){
                            callback.onResult(response.body().getEntities(),key);
                        }
                    }

                    @Override
                    public void onFailure(Call<EventResponse> call, Throwable t) {

                    }
                });
    }

    @Override
    public void loadAfter(@NonNull LoadParams<Integer> params, @NonNull LoadCallback<Integer, Event> callback) {

        getEventCall(params.key,PAGE_SIZE)
                .enqueue(new Callback<EventResponse>() {
                    @Override
                    public void onResponse(Call<EventResponse> call, Response<EventResponse> response) {

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
                    public void onFailure(Call<EventResponse> call, Throwable t) {

                    }
                });
    }

    protected Call<EventResponse> getEventCall(Integer page, Integer pageSize){


        int memberEvent = StorageUtil.loadInt(MainApplication.getAppContext(), "maxMemberEvent");
        int distanceEvent = StorageUtil.loadInt(MainApplication.getAppContext(), "maxDistanceEvent");
        List<String> stringList = StorageUtil.loadList(MainApplication.getAppContext(), "langListEvent");
        if (stringList.isEmpty()) {
            stringList = null;
        }
        return SingletonRetrofitClient.getInsance()
                .getApi()
                .getEvents(
                        memberEvent,
                        distanceEvent,
                        stringList,
                        page,
                        pageSize
                );
    }
}
