package com.sideproject.ryanbrounley.jukebox_android.Playlist;

import java.util.Iterator;
import java.util.List;
import android.util.Log;
import java.util.LinkedList;

/**
 * Created by kylerkiminki on 10/30/16.
 */

//Playlist class ~ queue to hold songs ~
public class Playlist implements Iterable<Song>{
    private List<Song> queue;
    private String name, wifi;
    private int ID;

    //Constructor for playlist
    public Playlist(){
        queue= new LinkedList<Song>();
    }

    //Constructor for playlist
    public Playlist(String name, String wifi){
        this.name = name;
        this.wifi = wifi;
        this.queue= new LinkedList<Song>();
    }

    //Method to add a song to the playlist
    public void addSong(Song s){
        for(Song song : this){
            if(song.equals(s))
                return;
        }
        Log.d("Playlist", "Song added "+s.getName());
        queue.add(s);
    }

    //Method that grabs song at index i
    public Song getSongAt(int i){
        return queue.get(i);
    }

    //Method that grabs a song where Song.getUri()==uri
    public Song getSongAt(String uri){
        Song result=null;
        for(Song s: this){
            if(s.getUri()==uri) {
                result = s;
                break;
            }
        }
        if(result==null)
            Log.d("Playlist", "getSongAt: couldn't find");
        return result;
    }

    //Method to remove a song in the playlist
    public void remove(Song s){
        for(int i=0; i<queue.size(); i++ ) {
            if(s.equals(queue.get(i))){
                queue.remove(i);
                Log.d("Playlist", "Removed "+s.getName()+" from the queue");
            }
        }
    }

    //Method that removes song at index i
    public void removeAt(int i){
        queue.remove(i);
    }

    //Method that pops a song off the top of the playlist
    public Song popSong(){
        Song s = queue.get(0);
        queue.remove(0);
        return s;
    }

    //Method that returns the size of the playlist
    public int size(){
        return queue.size();
    }

    //Method that returns if the playlist is empty
    public boolean isEmpty(){
        return queue.isEmpty();
    }

    //Method that clears the playlist
    public void clear(){
        while (!this.isEmpty())
            this.popSong();
    }

    //Method that sorts the songs by upvotes
    public void sort(){
        mergeSort(this);
    }

    //Recursive sorting helper function
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

    //Merging helper function
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

    //Removes songs with
    public void vetoScan(){
        for(Song s: this){
            if(s.getDownvotes() > 2)
                remove(s);
        }
    }

    //Song iterator for the playist
    public sItor iterator(){
        return new sItor();
    }

    class sItor implements Iterator<Song>{
        private int index = 0;

        public boolean hasNext(){
            return index < size();
        }

        public Song next(){
            return getSongAt(index);
        }

        public void remove(){
            removeAt(index);
        }
    }

}
