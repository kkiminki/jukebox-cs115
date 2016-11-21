package com.sideproject.ryanbrounley.jukebox_android;

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.util.Log;

import com.sideproject.ryanbrounley.jukebox_android.SearchResponse.Item;
import com.sideproject.ryanbrounley.jukebox_android.Playlist.*;

/**
 * Created by kylerkiminki on 11/20/16.
 */

public class SearchItemAdapter extends ArrayAdapter<Item> {
    public SearchItemAdapter(Context context, ArrayList<Item> items){
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        Item item = getItem(position);

        if(convertView==null)
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.search_result, parent, false);
        TextView text = (TextView) convertView.findViewById(R.id.search_result_view);
        text.setText(item.getName().toString() + " - " +
                item.getArtists().get(0).getName().toString());
        final Menu menu = (Menu) getContext();
        final Song s = new Song(item.getArtists().get(0).getName(), item.getUri(), item.getAlbum().getName(), item.getName());
        convertView.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Log.d("SearchAdapter", "clicked");
                    //menu.PlayerEnqueue(s);
                    if(menu.playlist.isEmpty() && !menu.playing) {
                        menu.PlaySong(s);
                        Log.d("SearchAdapter", "Playing song "+s.getName());
                    }else{
                        Log.d("SearchAdapter", "enqueue");
                        menu.PlayerEnqueue(s);
                        Log.d("PlayerAdapter", "Enqueueing Song "+s.getName());
                    }
                }
        });
        return convertView;
    }
}
