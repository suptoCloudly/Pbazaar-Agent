package com.pbazaar.pbazaarforagent.remote;

import com.google.android.gms.common.api.Api;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by supto on 8/17/17.
 */

public class MapApi {
    private static final String TAG = Api.class.getSimpleName();


    private static MapApi instance;
    private MapApiServiceClient mapApiServiceClient;

    private MapApi() {


        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RemoteConstant.MAP_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient.build())
                .build();

        this.mapApiServiceClient = retrofit.create(MapApiServiceClient.class);
    }

    public static MapApi getInstance() {
        if (instance == null) {
            instance = new MapApi();
        }

        return instance;
    }

    public MapApiServiceClient getApiServiceClient() {
        return mapApiServiceClient;
    }
}
