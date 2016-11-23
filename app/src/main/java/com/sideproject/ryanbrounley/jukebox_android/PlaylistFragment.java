package com.sideproject.ryanbrounley.jukebox_android;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;


import com.sideproject.ryanbrounley.jukebox_android.Firebase.AddPlaylistQuery;
import com.sideproject.ryanbrounley.jukebox_android.Firebase.GetPlaylistsQuery;
import com.sideproject.ryanbrounley.jukebox_android.Playlist.Playlist;
import com.sideproject.ryanbrounley.jukebox_android.SearchResponse.Item;

/**
 * Created by kylerkiminki on 11/21/16.
 */

public class PlaylistFragment extends Fragment{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.playlist_layout, container, false);
        final Menu menu = (Menu) getActivity();
        menu.onPlayer=false;

        ListView playlist_list = (ListView) v.findViewById(R.id.playlist_list_view);

        GetPlaylistsQuery g = new GetPlaylistsQuery(getActivity());
        g.executeAndUpdate(playlist_list);

        return v;
    }
}
