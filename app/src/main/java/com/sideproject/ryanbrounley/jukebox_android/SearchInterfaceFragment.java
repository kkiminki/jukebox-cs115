package com.sideproject.ryanbrounley.jukebox_android;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.sideproject.ryanbrounley.jukebox_android.Playlist.Song;
import com.sideproject.ryanbrounley.jukebox_android.SearchResponse.Item;
import com.sideproject.ryanbrounley.jukebox_android.SearchResponse.SpotifySearchResponse;
import com.sideproject.ryanbrounley.jukebox_android.SearchResponse.Tracks;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


/**
 * Created by Anshika on 11/1/2016.
 */

public class SearchInterfaceFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        final View v = inflater.inflate(R.layout.searchlayout, container, false);
        final Menu menuContext = (Menu)getActivity();
        menuContext.onPlayer=false;

        final EditText search_bar = (EditText)v.findViewById(R.id.search_bar);

        search_bar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void afterTextChanged(Editable s) {
                HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
                logging.setLevel(HttpLoggingInterceptor.Level.BODY);
                OkHttpClient httpClient = new OkHttpClient.Builder()
                        .addInterceptor(logging)
                        .build();

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("https://api.spotify.com")
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(httpClient)
                        .build();
                SpotifyService service = retrofit.create(SpotifyService.class);
                String query = search_bar.getText().toString();
                Call<SpotifySearchResponse> queryResponseCall = service.getTracks(query, "track", "20");
                queryResponseCall.enqueue(new Callback<SpotifySearchResponse>() {
                    @Override
                    public void onResponse(Response<SpotifySearchResponse> response) {
                        List<String> songs = new ArrayList<>();
                        SearchItemAdapter listAdapter;
                        ListView results = (ListView)v.findViewById(R.id.search_results);

                        if (response.code() == 200){
                            List<Item> items = response.body().getTracks().getItems();
                            //for (Item item: items) {
                            //    songs.add(item.getName().toString() + " - " +
                            //            item.getArtists().get(0).getName().toString());
                            //}
                            //listAdapter = new ArrayAdapter<String>(getActivity(), R.layout.search_result, songs);
                            ArrayList<Item> arrayItems = new ArrayList<Item>(items);
                            listAdapter = new SearchItemAdapter(getActivity(), arrayItems);
                            results.setAdapter(listAdapter);
                        } else {

                        }
                        if (search_bar.getText().toString().isEmpty()){

                        }
                    }
                    @Override
                    public void onFailure(Throwable t) {
                    }
                });
            }
        });
        return v;
    }
    public interface SpotifyService {
        @GET("v1/search")
        Call<SpotifySearchResponse> getTracks(@Query("query") String query, @Query("type") String type, @Query("limit") String limit);
    }
}