package com.sideproject.ryanbrounley.jukebox_android.Firebase;

/**
 * Created by ryanbrounley on 11/21/16.
 */

import android.content.Context;
import android.util.Log;

import com.sideproject.ryanbrounley.jukebox_android.Playlist.Playlist;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

import com.sideproject.ryanbrounley.jukebox_android.Playlist.Song;

/**
 * Created by ryanbrounley on 11/21/16.
 */

public class AddPlaylistQuery {
    public String name;
    public String wifiName;
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

    public AddPlaylistQuery(String wifiName, String name, Context ctx) {
        this.ctx = ctx;
        this.wifiName = wifiName;
        this.name = name;
    }

    public void executeAndUpdate() {
        PlaylistAdd service = retrofit.create(PlaylistAdd.class);
        JSONObject playlist = new JSONObject();
        try {
            playlist.put("wifiName", wifiName);
            playlist.put("name", name);
            Call<ResponseBody> newList = service.addPlaylist(playlist);
                newList.enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Response<ResponseBody> response) {

                    }
                    @Override
                    public void onFailure(Throwable t) {
                        Log.e("Failure", "Failed to GET playlists");
                    }
                });

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public interface PlaylistAdd {
        @POST("playlists")
        Call<ResponseBody> addPlaylist(@Body JSONObject playlist);
    }
}

