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
import retrofit2.http.DELETE;

import com.sideproject.ryanbrounley.jukebox_android.Playlist.Song;

/**
 * Created by ryanbrounley on 11/21/16.
 */

public class DeleteQueueQuery {
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

    public DeleteQueueQuery(Context ctx) {
        this.ctx = ctx;
    }

    public void executeAndUpdate(final String ID) {
        PlaylistUpdate service = retrofit.create(PlaylistUpdate.class);
        JSONObject playlist = new JSONObject();
        try {
            Call<ResponseBody> newList = service.addPlaylist(ID);
            newList.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Response<ResponseBody> response) {
                    Log.d("DeleteQueueQuery", "code: " + response.code());
                }
                @Override
                public void onFailure(Throwable t) {
                    Log.e("Failure", "Failed to DELETE playlists");
                }
            });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public interface PlaylistUpdate {
        @DELETE("playlists/{id}/nameValuePairs/queue")
        Call<ResponseBody> addPlaylist(@Path("id") String ID);
    }
}

