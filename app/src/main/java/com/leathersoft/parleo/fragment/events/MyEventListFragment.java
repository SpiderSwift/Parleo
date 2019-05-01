package com.leathersoft.parleo.fragment.events;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.leathersoft.parleo.R;
import com.leathersoft.parleo.fragment.BaseFragment;
import com.leathersoft.parleo.network.SingletonRetrofitClient;
import com.leathersoft.parleo.network.model.Event;
import com.leathersoft.parleo.network.model.User;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyEventListFragment extends BaseFragment {

    @BindView(R.id.recycler_view_events)
    RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_events_list,container,false);
        ButterKnife.bind(this,v);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        final EventListAdapter eventPageAdapter =
                new EventListAdapter(mPushFragmentInterface);

        SingletonRetrofitClient.getInsance()
                .getApi()
                .getMe()
                .enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
                        if(response.isSuccessful()){
                            List<User.AttendingEvent> attendingEvents =
                                    response.body()
                                            .getAttendingEvents();

                            for(User.AttendingEvent event:attendingEvents){
                                Log.d("EVENT",event.toString());
                                getEvent(event.getId(),eventPageAdapter);
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });
        mRecyclerView.setAdapter(eventPageAdapter);

        return v;
    }

    private void getEvent(String id,EventListAdapter adapter){
        SingletonRetrofitClient.getInsance()
                .getApi()
                .getEvent(id)
                .enqueue(new Callback<Event>() {
                    @Override
                    public void onResponse(Call<Event> call, Response<Event> response) {
                        if(response.isSuccessful()){
                            Event e = response.body();
                            adapter.getEvents().add(e);
                            adapter.notifyItemInserted(
                                    adapter.getEvents().size()
                            );
                        }
                    }

                    @Override
                    public void onFailure(Call<Event> call, Throwable t) {
                        Log.d("EVENT",t.toString());

                    }
                });
    }

    public static MyEventListFragment newInstance(){
        MyEventListFragment fragment = new MyEventListFragment();
        return fragment;
    }
}
