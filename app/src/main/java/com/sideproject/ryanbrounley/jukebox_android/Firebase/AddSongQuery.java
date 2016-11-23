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

public class AddSongQuery {
    public String name, uri, artist, album;
    public int downvotes, upvotes;
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

    public AddSongQuery(String name, String artist, String uri, String album, Context ctx) {
        this.ctx = ctx;
        this.artist = artist;
        this.uri = uri;
        this.album = album;
        this.name = name;
        this.downvotes = 0;
        this.upvotes = 0;
    }

    public void executeAndUpdate(String id) {
        SongAdd service = retrofit.create(SongAdd.class);
        JSONObject song = new JSONObject();
        try {
            song.put("artist", artist);
            song.put("name", name);
            song.put("uri", uri);
            song.put("album", album);
            song.put("downvotes", downvotes);
            song.put("upvotes", upvotes);
            Call<ResponseBody> newList = service.addSong(id, song);
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

    public interface SongAdd {
        @POST("playlists/{id}/nameValuePairs/songs")
        Call<ResponseBody> addSong(@Path("id") String id, @Body JSONObject song);
    }
}

