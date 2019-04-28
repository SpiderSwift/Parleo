package com.leathersoft.parleo.network;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.leathersoft.parleo.network.model.User;

public class UserDataSourceFactory extends DataSource.Factory {


    private MutableLiveData<PageKeyedDataSource<Integer, User>> mUserLiveDataSource = new MutableLiveData<>();

    @Override
    public DataSource create() {
        UserDataSource userDataSource = new UserDataSource();
        mUserLiveDataSource.postValue(userDataSource);
        return userDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, User>> getUserLiveDataSource() {
        return mUserLiveDataSource;
    }
}
