package com.leathersoft.parleo.network.model;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

public class EventViewModel extends ViewModel {

    private LiveData<PagedList<Event>> mEventPagedList;
    private LiveData<PageKeyedDataSource<Integer,Event>> mLiveDataSource;

    private void initData(Class classId){

        EventDataSourceFactory eventDataSourceFactory = new EventDataSourceFactory(classId);
        mLiveDataSource = eventDataSourceFactory.getEventLiveDataSource();

        PagedList.Config config =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(EventDataSource.PAGE_SIZE)
                        .build();

        mEventPagedList = (new LivePagedListBuilder(eventDataSourceFactory,config)).build();

    }

    public void initFactory(Class classId){
        initData(classId);
    }

    public LiveData<PagedList<Event>> getEventPagedList() {
        return mEventPagedList;
    }

    public LiveData<PageKeyedDataSource<Integer, Event>> getLiveDataSource() {
        return mLiveDataSource;
    }
}
