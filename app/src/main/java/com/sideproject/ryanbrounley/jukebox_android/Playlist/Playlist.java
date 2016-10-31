package com.sideproject.ryanbrounley.jukebox_android.Playlist;

import java.util.List;
import android.util.Log;

/**
 * Created by kylerkiminki on 10/30/16.
 */

public class Playlist {
    private List<Song> queue;

    public void addSong(Song s){
        queue.add(s);
    }

    public Song getSongAt(int i){
        return queue.get(i);
    }

    public void remove(Song s){
        for(int i=0; i<queue.size(); i++ ) {
            if(s.equals(queue.get(i))){
                queue.remove(i);
                Log.d("Playlist", "Removed "+s.getName()+" from the queue");
            }
        }
    }

    public int size(){
        return queue.size();
    }

}
