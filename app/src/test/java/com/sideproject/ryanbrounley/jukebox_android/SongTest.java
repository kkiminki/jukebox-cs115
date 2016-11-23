package com.sideproject.ryanbrounley.jukebox_android;

import com.sideproject.ryanbrounley.jukebox_android.Playlist.Song;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 */
public class SongTest {
    @Test
    public void TestConstructedSong() throws Exception {
        Song song = new Song("Linkin Park", "http://napster.com/supercoolz", "Hybrid Theory", "Ryan's Song");
        assertEquals(song.getArtists(), "Linkin Park");
        assertEquals(song.getUri(), "http://napster.com/supercoolz");
        assertEquals(song.getAlbum(), "Hybrid Theory");
        assertEquals(song.getName(), "Ryan's Song");
    }

    @Test
    public void TestVotes() throws Exception {
        Song song = new Song("Linkin Park", "http://napster.com/supercoolz", "Hybrid Theory", "Ryan's Song");
        assertEquals(song.getUpvotes(), 0);
        assertEquals(song.getDownvotes(), 0);
        song.addDownvote();
        song.addUpvote();
        song.addDownvote();
        song.addDownvote();
        song.addUpvote();
        song.addDownvote();
        assertEquals(song.getUpvotes(), 2);
        assertEquals(song.getDownvotes(), 4);
    }
}