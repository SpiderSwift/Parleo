package com.leathersoft.parleo.network.friends;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.leathersoft.parleo.network.model.User;
import com.leathersoft.parleo.network.users.UserDataSource;
import com.leathersoft.parleo.network.users.UserDataSourceFactory;

public class FriendViewModel extends ViewModel {

    private LiveData<PagedList<User>> mUserPagedList;
    private LiveData<PageKeyedDataSource<Integer,User>> mLiveDataSource;



    public FriendViewModel() {
        FriendDataSourceFactory userDataSourceFactory = new FriendDataSourceFactory();
        mLiveDataSource = userDataSourceFactory.getUserLiveDataSource();
        PagedList.Config config =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(UserDataSource.PAGE_SIZE)
                        .build();

        mUserPagedList = (new LivePagedListBuilder(userDataSourceFactory,config)).build();
    }

    public LiveData<PagedList<User>> getUserPagedList() {
        return mUserPagedList;
    }

    public LiveData<PageKeyedDataSource<Integer, User>> getLiveDataSource() {
        return mLiveDataSource;
    }
}
