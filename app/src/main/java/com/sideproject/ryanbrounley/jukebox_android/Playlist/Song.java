package com.sideproject.ryanbrounley.jukebox_android.Playlist;

import java.net.URL;
import java.util.List;

/**
 * Created by kylerkiminki on 10/30/16.
 */

public class Song {
    private String artist;
    private String uri;
    private String album;
    private URL image;
    private URL thumbnail;
    private String name;
    private int upvotes=0;
    private int downvotes=0;

    //Constructor for the song class
    public Song(String artist, String uri, String album, String name){
        this.artist = artist;
        this.uri = uri;
        this.album = album;
        this.name = name;
    }

    //Constructor for testing
    //TODO Delete this constructor
    //public Song(String uri){
    //    this.uri=uri;
    //}

    //Artists getter
    public String getArtists(){
        return this.artist;
    }

    //URI getter
    public String getUri(){
        return this.uri;
    }

    //Album getter
    public String getAlbum(){
        return this.album;
    }

    //Image getter
    public URL getImage(){
        return this.image;
    }

    //Thumbnail getter
    public URL getThumbnail(){
        return this.thumbnail;
    }

    //Name getter
    public String getName(){
        return this.name;
    }

    //Equals comparison method
    public boolean equals(Song s){
        return this.uri == s.uri;
    }

    //Upvote incrementer
    public void addUpvote(){
        this.upvotes++;
    }

    //Upvotes getter
    public int getUpvotes(){
        return this.upvotes;
    }

    //Downvotes incrementer
    public void addDownvote(){
        this.downvotes++;
    }

    //Downvote getter
    public int getDownvotes(){
        return this.downvotes;
    }
}
