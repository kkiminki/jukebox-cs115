package com.sideproject.ryanbrounley.jukebox_android.Firebase;

import android.content.Context;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by kylerkiminki on 11/22/16.
 */

public class RemoveSongQuery {
    public Context ctx;
    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

    OkHttpClient httpClient = new OkHttpClient.Builder()
            .addInterceptor(logging)
            .build();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://jukebox-2e137.firebaseio.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build();

    public RemoveSongQuery(Context ctx) {
        this.ctx = ctx;
    }


}
