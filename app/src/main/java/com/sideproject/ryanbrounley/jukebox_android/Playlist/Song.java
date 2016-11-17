package com.sideproject.ryanbrounley.jukebox_android.Playlist;

import java.net.URL;

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

    //Constructor for the song class
    public Song(String artist, String uri, String album, URL image, URL thumbnail, String name){
        this.artist = artist;
        this.uri = uri;
        this.album = album;
        this.image = image;
        this.thumbnail = thumbnail;
        this.name = name;
    }

    //Constructor for testing
    //TODO Delete this constructor
    public Song(String uri){
        this.uri=uri;
    }

    //TODO Delete setters: should be set by constructor
    //Artist setter
    public void setArtist(String artist){
        this.artist = artist;
    }

    //Artist getter
    public String getArtist(){
        return this.artist;
    }

    //URI setter
    public void setUri(String uri){
        this.uri = uri;
    }

    //URI getter
    public String getUri(){
        return this.uri;
    }

    //Album setter
    public void setAlbum(String album){
        this.album = album;
    }

    //Album getter
    public String getAlbum(){
        return this.album;
    }

    //Image setter
    public void setImage(URL image){
        this.image = image;
    }

    //Image getter
    public URL getImage(){
        return this.image;
    }

    //Thumbnail setter
    public void setThumbnail(URL thumbnail){
        this.thumbnail = thumbnail;
    }

    //Thumbnail getter
    public URL getThumbnail(){
        return this.thumbnail;
    }

    //Name setter
    public void setName(String name){
        this.name = name;
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

    //Upvotes decrementer
    public void addDownvote(){
        this.upvotes--;
    }
}
