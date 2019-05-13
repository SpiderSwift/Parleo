package com.leathersoft.parleo.network.friends;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.leathersoft.parleo.network.model.User;
import com.leathersoft.parleo.network.users.UserDataSource;

public class FriendDataSourceFactory extends DataSource.Factory {


    private MutableLiveData<PageKeyedDataSource<Integer, User>> mUserLiveDataSource = new MutableLiveData<>();

    @Override
    public DataSource create() {
        FriendDataSource userDataSource = new FriendDataSource();
        mUserLiveDataSource.postValue(userDataSource);
        return userDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, User>> getUserLiveDataSource() {
        return mUserLiveDataSource;
    }
}
