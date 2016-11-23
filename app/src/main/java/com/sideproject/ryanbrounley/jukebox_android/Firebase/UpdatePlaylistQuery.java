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
import retrofit2.http.PUT;
import retrofit2.http.Path;

import com.sideproject.ryanbrounley.jukebox_android.Playlist.Song;

/**
 * Created by ryanbrounley on 11/21/16.
 */

public class UpdatePlaylistQuery {
    public String name;
    public String wifiName;
    public Context ctx;
    public List<Song> queue;
    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

    OkHttpClient httpClient = new OkHttpClient.Builder()
            .addInterceptor(logging)
            .build();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://jukebox-2e137.firebaseio.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build();

    public UpdatePlaylistQuery(String wifiName, String name, List<Song>queue, Context ctx) {
        this.ctx = ctx;
        this.wifiName = wifiName;
        this.name = name;
        this.queue=queue;
    }

    public void executeAndUpdate(final String ID) {
        PlaylistUpdate service = retrofit.create(PlaylistUpdate.class);
        JSONObject playlist = new JSONObject();
        try {
            playlist.put("wifiName", wifiName);
            playlist.put("name", name);
            playlist.put("queue", queue);
            Call<ResponseBody> newList = service.addPlaylist(ID, playlist);
            newList.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Response<ResponseBody> response) {
                    Log.d("made it: ", "" + response.toString());
                }
                @Override
                public void onFailure(Throwable t) {
                    Log.e("Failure", "Failed to UPDATE playlists");
                }
            });

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public interface PlaylistUpdate {
        @POST("playlists/{id}.json")
        Call<ResponseBody> addPlaylist(@Path("id") String ID, @Body JSONObject playlist);
    }
}

