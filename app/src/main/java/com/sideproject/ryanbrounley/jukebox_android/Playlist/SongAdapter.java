package com.sideproject.ryanbrounley.jukebox_android.Playlist;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Button;
import android.util.Log;

import com.sideproject.ryanbrounley.jukebox_android.Menu;
import com.sideproject.ryanbrounley.jukebox_android.Playlist.*;
import com.sideproject.ryanbrounley.jukebox_android.R;

/**
 * Created by kylerkiminki on 11/20/16.
 */

//Adapter for song list view
public class SongAdapter extends ArrayAdapter<Song> {
    public SongAdapter(Context context, ArrayList<Song> songs){
        super(context, 0, songs);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Song song = getItem(position);

        //If the view is not inflated then inflate a song template
        if(convertView==null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.song_template, parent, false);

        //Creating fields for the views of the template
        TextView artist = (TextView) convertView.findViewById(R.id.song_artist);
        TextView track = (TextView) convertView.findViewById(R.id.song_track);

        //Buttons for upvotes and downvotes
        ImageButton downvote = (ImageButton) convertView.findViewById(R.id.downvote_button);

        //Set the text to Artist: Song name
        artist.setText(song.getArtists());
        track.setText(song.getName());

        //Initialize a menu context and final reference
        //to the song
        final Menu menu = (Menu) getContext();
        final Song s = song;

        //Add onClickListener to increment downvotes
        downvote.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                s.addDownvote();

                //If downvotes >= 3 then remove the song
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
