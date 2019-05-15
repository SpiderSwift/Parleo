package com.leathersoft.parleo.network.model;

import com.leathersoft.parleo.network.SingletonRetrofitClient;

import retrofit2.Call;

public class MyEventDataSource extends EventDataSource {

    @Override
    protected Call<EventResponse> getEventCall(Integer page, Integer pageSize){
        return SingletonRetrofitClient.getInsance()
                .getApi()
                .getMyEvents(
                        page,
                        pageSize
                );
    }
}
