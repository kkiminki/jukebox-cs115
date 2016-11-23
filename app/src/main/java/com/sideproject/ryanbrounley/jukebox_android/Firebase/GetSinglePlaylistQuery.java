package com.sideproject.ryanbrounley.jukebox_android.Firebase;

import android.content.Context;
import android.util.Log;

import com.sideproject.ryanbrounley.jukebox_android.Playlist.Playlist;
import com.sideproject.ryanbrounley.jukebox_android.Playlist.PlaylistAdapter;
import com.sideproject.ryanbrounley.jukebox_android.Playlist.Song;
import com.sideproject.ryanbrounley.jukebox_android.RetrieveWifiLocation;

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
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by kylerkiminki on 11/22/16.
 */

public class GetSinglePlaylistQuery {
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

    public GetSinglePlaylistQuery(Context ctx) {
        this.ctx = ctx;
    }

    public void executeAndUpdate(String ID){
        GetSinglePlaylistQuery.getSinglePlaylist service = retrofit.create(GetSinglePlaylistQuery.getSinglePlaylist.class);
        Call<ResponseBody> playlists = service.getSinglePlaylist();

        playlists.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Response<ResponseBody> response) {
                try {
                    Playlist playlist;
                    String body = response.body().string();
                    try {
                        JSONObject jsonArray = new JSONObject(body);
                        Iterator keys = jsonArray.keys();
                        RetrieveWifiLocation r = new RetrieveWifiLocation(ctx);
                        while(keys.hasNext()) {
                            String key = (String) keys.next();
                            JSONObject obj1 = jsonArray.getJSONObject(key);
                            JSONObject obj2 = obj1.getJSONObject("nameValuePairs");

                            Playlist temp = new Playlist(key, (String) obj2.get("wifiName"), (String) obj2.get("name"), (List<Song>) obj2.get("queue"));
                            //if(temp.getWifi()==r.getWifiName())
                            //    play.add(temp);
                            //Log.d("init", play.toString());
                        }
                    }catch (JSONException exception){
                        Log.e("broken JSON", exception.toString());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public void onFailure(Throwable t) {
                Log.e("Failure", "Failed to GET playlists");
            }
        });
    }

    public interface getSinglePlaylist {
        @GET("playlists.json")
        Call<ResponseBody> getSinglePlaylist();
    }
}
