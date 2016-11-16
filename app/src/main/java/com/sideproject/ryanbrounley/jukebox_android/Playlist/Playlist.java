package com.sideproject.ryanbrounley.jukebox_android.Playlist;

import java.util.List;
import android.util.Log;
import java.util.LinkedList;

/**
 * Created by kylerkiminki on 10/30/16.
 */

public class Playlist {
    private List<Song> queue;

    public Playlist(){
        queue= new LinkedList<Song>();
    }

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

    public Song popSong(){
        Song s = queue.get(0);
        queue.remove(0);
        return s;
    }

    public int size(){
        return queue.size();
    }

    public boolean isEmpty(){
        return queue.isEmpty();
    }

    public void sort(){
        mergeSort(this);
    }

    private Playlist mergeSort(Playlist list){
        if(list.size() <= 1){
            return list;
        }

        Playlist left = new Playlist();
        Playlist right = new Playlist();

        for (int i =0; i< list.size(); i++){
            if (i <= (list.size()/2)){
                left.addSong(list.getSongAt(i));
            }else{
                right.addSong(list.getSongAt(i));
            }
        }

        left = mergeSort(left);
        right = mergeSort(right);

        return merge(left, right);
    }

    private Playlist merge(Playlist left, Playlist right){
        Playlist result = new Playlist();
        while((!left.isEmpty()) && (!right.isEmpty())){
            if(left.getSongAt(0).getUpvotes() <= right.getSongAt(0).getUpvotes()){
                result.addSong(left.popSong());
            }
            else{
                result.addSong(right.popSong());
            }
        }
        while(!left.isEmpty())
            result.addSong(left.popSong());
        while(!right.isEmpty())
            result.addSong(right.popSong());
        return result;
    }

}
