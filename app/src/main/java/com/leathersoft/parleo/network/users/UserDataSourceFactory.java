package com.leathersoft.parleo.network.users;

import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.leathersoft.parleo.fragment.users.UserFragment;
import com.leathersoft.parleo.network.model.User;

public class UserDataSourceFactory extends DataSource.Factory {


    private MutableLiveData<PageKeyedDataSource<Integer, User>> mUserLiveDataSource = new MutableLiveData<>();

    private Class mClass;

    public UserDataSourceFactory(Class aClass) {
        mClass = aClass;
    }

    @Override
    public DataSource create() {
        UserDataSource userDataSource;
        if(mClass == UserFragment.class){
            userDataSource = new UserDataSource();
        }else {
            userDataSource = new FriendDataSource();
        }

        mUserLiveDataSource.postValue(userDataSource);
        return userDataSource;
    }

    public MutableLiveData<PageKeyedDataSource<Integer, User>> getUserLiveDataSource() {
        return mUserLiveDataSource;
    }
}
