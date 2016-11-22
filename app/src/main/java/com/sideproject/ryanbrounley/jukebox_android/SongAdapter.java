package com.sideproject.ryanbrounley.jukebox_android;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Button;
import android.util.Log;


import com.sideproject.ryanbrounley.jukebox_android.SearchResponse.Item;
import com.sideproject.ryanbrounley.jukebox_android.Playlist.*;

/**
 * Created by kylerkiminki on 11/20/16.
 */

public class SongAdapter extends ArrayAdapter<Song> {
    public SongAdapter(Context context, ArrayList<Song> songs){
        super(context, 0, songs);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Song song = getItem(position);

        if(convertView==null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.song_template, parent, false);
        TextView text = (TextView) convertView.findViewById(R.id.song_template_text);
        Button upvote = (Button) convertView.findViewById(R.id.upvote_button);
        Button downvote = (Button) convertView.findViewById(R.id.downvote_button);
        text.setText(song.getArtists()+": "+song.getName());
        final Menu menu = (Menu) getContext();
        final Song s = song;
        upvote.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                s.addUpvote();
                Log.i("PlayerFragment", "Adding upvotes");
                Log.i("PlayerFragment", "Upvote count: "+s.getUpvotes());
            }
        });

        downvote.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                s.addDownvote();
                if (s.getDownvotes() >= 3){
                    menu.playlist.remove(s);
                    menu.updatePlayer();
                }
                Log.i("PlayerFragment", "Adding downvote");
                Log.i("PlayerFragment", "Downvote count: "+s.getDownvotes());
            }
        });

        return convertView;
    }
}
