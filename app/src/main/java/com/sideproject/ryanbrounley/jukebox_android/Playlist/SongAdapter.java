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

import com.sideproject.ryanbrounley.jukebox_android.Firebase.GetSinglePlaylistQuery;
import com.sideproject.ryanbrounley.jukebox_android.Firebase.UpdatePlaylistQuery;
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

/*<<<<<<< HEAD
        //Add onClickListener to increment upvotes
        upvote.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                GetSinglePlaylistQuery sq;
                UpdatePlaylistQuery uq;
                sq = new GetSinglePlaylistQuery(getContext());
                sq.executeAndUpdate(menu.playlist.getID());
                menu.playlist.getSongAt(s.getUri()).addUpvote();
                uq = new UpdatePlaylistQuery(menu.playlist.getQueue(), getContext());
                uq.executeAndUpdate(menu.playlist.getID());
                Log.i("PlayerFragment", "Adding upvotes");
                Log.i("PlayerFragment", "Upvote count: "+menu.playlist.getSongAt(s.getUri()).getUpvotes());
            }
        });

=======
>>>>>>> 2cf617fc80fcd92be29d0b7d017b70e24fd5dd78*/

        //Add onClickListener to increment downvotes
        downvote.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                menu.playlist.getSongAt(s.getUri()).addDownvote();
                //If downvotes >= 3 then remove the song
                if (menu.playlist.getSongAt(s.getUri()).getDownvotes() >= 3){
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
