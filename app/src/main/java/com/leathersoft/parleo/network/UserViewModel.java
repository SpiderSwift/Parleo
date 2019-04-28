package com.leathersoft.parleo.network;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.leathersoft.parleo.network.model.User;

public class UserViewModel extends ViewModel {

    private LiveData<PagedList<User>> userPagedList;
    private LiveData<PageKeyedDataSource<Integer,User>> liveDataSource;



    public UserViewModel() {
        UserDataSourceFactory  userDataSourceFactory = new UserDataSourceFactory();
        liveDataSource = userDataSourceFactory.getUserLiveDataSource();
        PagedList.Config config =
                (new PagedList.Config.Builder())
                .setEnablePlaceholders(false)
                .setPageSize(UserDataSource.PAGE_SIZE)
                .build();

        userPagedList = (new LivePagedListBuilder(userDataSourceFactory,config)).build();
    }

    public LiveData<PagedList<User>> getUserPagedList() {
        return userPagedList;
    }

    public LiveData<PageKeyedDataSource<Integer, User>> getLiveDataSource() {
        return liveDataSource;
    }
}
