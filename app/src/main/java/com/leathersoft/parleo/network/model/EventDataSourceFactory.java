package com.leathersoft.parleo.network.model;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.leathersoft.parleo.fragment.events.EventListFragment;

import retrofit2.Call;

public class EventDataSourceFactory extends DataSource.Factory {

    private MutableLiveData<PageKeyedDataSource<Integer, Event>> mEventLiveDataSource = new MutableLiveData<>();

    private Class mClass;

    public EventDataSourceFactory(Class aClass) {
        mClass = aClass;
    }

    @Override
    public DataSource create() {
        EventDataSource eventDataSource;
        if(mClass == EventListFragment.class){
            eventDataSource = new EventDataSource();
        }else {
            eventDataSource = new MyEventDataSource();
        }
        mEventLiveDataSource.postValue(eventDataSource);

        return eventDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, Event>> getEventLiveDataSource() {
        return mEventLiveDataSource;
    }
}
