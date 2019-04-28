package com.leathersoft.parleo.network.model;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

public class EventDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, Event>> mEventLiveDataSource = new MutableLiveData<>();


    @Override
    public DataSource create() {
        EventDataSource eventDataSource = new EventDataSource();
        mEventLiveDataSource.postValue(eventDataSource);

        return eventDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Event>> getEventLiveDataSource() {
        return mEventLiveDataSource;
    }
}
