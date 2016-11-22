package com.sideproject.ryanbrounley.jukebox_android;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import com.sideproject.ryanbrounley.jukebox_android.Playlist.*;

import org.w3c.dom.Text;


/**
 * Created by kylerkiminki on 10/21/16.
 * */

public class PlayerFragment extends Fragment  {

    private static final String TAG = "PlayerActivity";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_player, container, false);

        final Menu menuContext = (Menu) getActivity();
        menuContext.onPlayer = true;

        Log.i("PlayerFragment", menuContext.playlist.logPlaylist());

        TextView currentlyPlaying = (TextView) v.findViewById(R.id.current_song);
        TextView upNext = (TextView) v.findViewById(R.id.song_up_next);

        currentlyPlaying.setText(menuContext.current);
        if(!menuContext.playlist.isEmpty()) {
            upNext.setText(menuContext.playlist.getSongAt(0).getArtists()+": "
                           +menuContext.playlist.getSongAt(0).getName());
        }else{
            String str ="No song is currently up next";
            upNext.setText(str);
        }

        ListView remaining = (ListView) v.findViewById(R.id.player_queue_list);
        SongAdapter songAdapter;
        ArrayList<Song> songs = new ArrayList<>(menuContext.playlist.getQueue());
        if(songs.size() > 2){
            songs.remove(0);
            songAdapter = new SongAdapter(getActivity(), songs);
            remaining.setAdapter(songAdapter);
        }

        return v;
    }

}
