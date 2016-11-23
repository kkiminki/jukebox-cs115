package com.sideproject.ryanbrounley.jukebox_android.Firebase;

import android.content.Context;
import android.util.Log;
import android.widget.ListView;

import com.sideproject.ryanbrounley.jukebox_android.Playlist.Playlist;
import com.sideproject.ryanbrounley.jukebox_android.Playlist.PlaylistAdapter;
import com.sideproject.ryanbrounley.jukebox_android.RetrieveWifiLocation;
import com.sideproject.ryanbrounley.jukebox_android.Playlist.Song;

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

/**
 * Created by ryanbrounley on 11/21/16.
 */

public class GetPlaylistsQuery {
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

    public GetPlaylistsQuery(Context ctx) {
        this.ctx = ctx;
    }

    public void executeAndUpdate(final ListView v) {
        PlaylistGet service = retrofit.create(PlaylistGet.class);
        Call<ResponseBody> playlists = service.listPlaylists();

        playlists.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Response<ResponseBody> response) {
                try {
                    ArrayList<Playlist> play = new ArrayList<Playlist>();
                    String body = response.body().string();
                    try {
                        JSONObject jsonArray = new JSONObject(body);
                        Iterator keys = jsonArray.keys();
                        RetrieveWifiLocation r = new RetrieveWifiLocation(ctx);
                        while(keys.hasNext()) {
                            String key = (String) keys.next();
                            JSONObject obj1 = jsonArray.getJSONObject(key);
                            JSONObject obj2 = obj1.getJSONObject("nameValuePairs");
                            play.add(new Playlist(key, (String) obj2.get("wifiName"), (String) obj2.get("name")));
                            //if(temp.getWifi()==r.getWifiName())
                            //    play.add(temp);
                            Log.d("init", play.toString());
                        }
                        PlaylistAdapter p = new PlaylistAdapter(ctx, play);
                        v.setAdapter(p);
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

    public interface PlaylistGet {
        @GET("playlists.json")
        Call<ResponseBody> listPlaylists();
    }
}
