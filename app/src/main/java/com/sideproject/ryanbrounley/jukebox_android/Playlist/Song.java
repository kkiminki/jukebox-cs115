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
    private int downvotes=0;

    public Song(){}

    public Song(String artist, String uri, String album, URL image, URL thumbnail, String name){
        this.artist = artist;
        this.uri = uri;
        this.album = album;
        this.image = image;
        this.thumbnail = thumbnail;
        this.name = name;
    }

    public void setArtist(String artist){
        this.artist = artist;
    }

    public String getArtist(){
        return this.artist;
    }

    public void setUri(String uri){
        this.uri = uri;
    }

    public String getUri(){
        return this.uri;
    }

    public void setAlbum(String album){
        this.album = album;
    }

    public String getAlbum(){
        return this.album;
    }

    public void setImage(URL image){
        this.image = image;
    }

    public URL getImage(){
        return this.image;
    }

    public void setThumbnail(URL thumbnail){
        this.thumbnail = thumbnail;
    }

    public URL getThumbnail(){
        return this.thumbnail;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public boolean equals(Song s){
        return this.uri == s.uri;
    }

    public void addUpvote(){
        this.upvotes++;
    }

    public int getUpvotes(){
        return this.upvotes;
    }

    public void addDownvote(){
        this.downvotes++;
    }

    public int getDownvotes(){
        return this.downvotes;
    }
}
