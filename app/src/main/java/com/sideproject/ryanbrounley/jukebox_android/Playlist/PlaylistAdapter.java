package com.sideproject.ryanbrounley.jukebox_android.Playlist;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Button;
import android.util.Log;

import com.sideproject.ryanbrounley.jukebox_android.Menu;
import com.sideproject.ryanbrounley.jukebox_android.R;

/**
 * Created by kylerkiminki on 11/20/16.
 */

//Adapter for song list view
public class PlaylistAdapter extends ArrayAdapter<Playlist> {
    public PlaylistAdapter(Context context, ArrayList<Playlist> playlists){
        super(context, 0, playlists);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        final Playlist playlist = getItem(position);
        Log.i("PlaylistAdapter", "Inside adapter");

        //If the view is not inflated then inflate a playlist template
        if(convertView==null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.playlist_template, parent, false);

        //Creating fields for the views of the template
        TextView text = (TextView) convertView.findViewById(R.id.playlist_template_text);

        text.setText(playlist.getName());

        final Menu menu = (Menu)getContext();

        convertView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.i("PlaylistAdapter", "Grabbing playlist "+playlist.getName());
                menu.playlist = playlist;
                if(!menu.playlist.isEmpty())
                    menu.PlaySong(menu.playlist.popSong());
            }
        });

        return convertView;
    }
}
