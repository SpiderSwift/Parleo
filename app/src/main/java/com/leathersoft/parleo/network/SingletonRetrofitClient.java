package com.leathersoft.parleo.network;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SingletonRetrofitClient {

    private static final String TOKEN = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJlbWFpbCI6InZsYWRwZXRydXNoa2V2aWNoMTk5OUBnbWFpbC5jb20iLCJqdGkiOiI0Mzc3YTBmOS1mNGQ4LTQ1YjAtYjI2My0wMTQ5ZGRhMmQ2MzQiLCJleHAiOjE1NTg5NjEzMDN9.jGMyVdzrDuAH8HAaktz7E1WIeiGy8OviJlvYIGDcr3g";
    private static final String BASE_URL = "https://awesomeparleobackend.azurewebsites.net/api/";
    private static SingletonRetrofitClient mInstance;
    private Retrofit retrofit;

    private SingletonRetrofitClient(){

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Interceptor.Chain chain) throws IOException {
                Request newRequest  = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer " + TOKEN)
                        .build();
                return chain.proceed(newRequest);
            }
        }).build();

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public static synchronized SingletonRetrofitClient getInsance(){
        if(mInstance == null){
            mInstance = new SingletonRetrofitClient();
        }
        return mInstance;
    }

    public Api getApi(){
        return retrofit.create(Api.class);
    }


}
