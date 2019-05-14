package com.leathersoft.parleo.network.users;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.leathersoft.parleo.network.model.EventDataSource;
import com.leathersoft.parleo.network.model.EventDataSourceFactory;
import com.leathersoft.parleo.network.model.User;

public class UserViewModel extends ViewModel {

    private LiveData<PagedList<User>> mUserPagedList;
    private LiveData<PageKeyedDataSource<Integer,User>> mLiveDataSource;



//    public UserViewModel() {
//        UserDataSourceFactory userDataSourceFactory = new UserDataSourceFactory();
//        mLiveDataSource = userDataSourceFactory.getUserLiveDataSource();
//        PagedList.Config config =
//                (new PagedList.Config.Builder())
//                .setEnablePlaceholders(false)
//                .setPageSize(UserDataSource.PAGE_SIZE)
//                .build();
//
//        mUserPagedList = (new LivePagedListBuilder(userDataSourceFactory,config)).build();
//    }


    private void initData(Class classId){
        UserDataSourceFactory userDataSourceFactory = new UserDataSourceFactory(classId);
        mLiveDataSource = userDataSourceFactory.getUserLiveDataSource();
        PagedList.Config config =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(UserDataSource.PAGE_SIZE)
                        .build();

        mUserPagedList = (new LivePagedListBuilder(userDataSourceFactory,config)).build();
    }

    public void initFactory(Class classId){
        initData(classId);
    }

    public LiveData<PagedList<User>> getUserPagedList() {
        return mUserPagedList;
    }

    public LiveData<PageKeyedDataSource<Integer, User>> getLiveDataSource() {
        return mLiveDataSource;
    }
}
