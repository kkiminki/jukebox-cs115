package com.sideproject.ryanbrounley.jukebox_android;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.sideproject.ryanbrounley.jukebox_android.Playlist.Playlist;

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

        Button submitButton = (Button) v.findViewById(R.id.create_playlist_button);

        ListView playlist_list = (ListView) v.findViewById(R.id.playlist_list_view);
        //getPlaylist(playlist_list);

        RetrieveWifiLocation r = new RetrieveWifiLocation(getActivity());
        final String ssid = r.getWifiName();

        final EditText playlistName = (EditText) v.findViewById(R.id.new_playlist_name);

        submitButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.i("PlaylistFragment", "Creating a new playlist");
                String str = "Playlist needs a name!";
                if(playlistName.getText()==null || playlistName.getText().equals(str)){
                    Log.i("PlayerFragment", "Playlist name not set");
                    playlistName.setText(str);
                }else{
                    Log.i("PlayerFragment", "Initializing new playlist");
                    menu.playlist = new Playlist(ssid, playlistName.getText().toString());
                }

            }
        });

        return v;
    }
}
