package com.pbazaar.pbazaarforagent.remote;

import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by supto on 4/2/17.
 */

public class PbazaarApi {

    private static final String TAG = PbazaarApi.class.getSimpleName();

    private static PbazaarApi instance;
    private Retrofit retrofit;
    private PbazaarApiServiceClient pbazaarApiServiceClient;


    private static OkHttpClient.Builder okHttpClient;


    private PbazaarApi() {
        okHttpClient = new OkHttpClient.Builder().connectTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES);

        retrofit = new Retrofit.Builder()
                .baseUrl(RemoteConstant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient.build())
                .build();

        pbazaarApiServiceClient = retrofit.create(PbazaarApiServiceClient.class);
    }

    public static PbazaarApi getInstance() {
        if (instance == null) {
            instance = new PbazaarApi();
        }

        return instance;
    }

    public PbazaarApiServiceClient getPbazaarApiServiceClient() {
        return pbazaarApiServiceClient;
    }

    public PbazaarApiServiceClient getPbazarApiServiceClientForMultipartUpload() {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder().connectTimeout(5, TimeUnit.MINUTES)
                .readTimeout(5, TimeUnit.MINUTES);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RemoteConstant.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().setLenient().create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient.build())
                .build();

        return retrofit.create(PbazaarApiServiceClient.class);
    }
}
