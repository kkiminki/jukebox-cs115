package com.sideproject.ryanbrounley.jukebox_android;

import com.sideproject.ryanbrounley.jukebox_android.Playlist.Playlist;
import com.sideproject.ryanbrounley.jukebox_android.Playlist.Song;

import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 */
public class PlaylistTest {
    @Test
    public void TestConstructedPlaylist() throws Exception {
        Playlist playlist = new Playlist("abc", "xfinitywifi", "the list");
        assertEquals(playlist.getID(), "abc");
        assertEquals(playlist.getWifi(), "xfinitywifi");
        assertEquals(playlist.getName(), "the list");
    }

    @Test
    public void TestSetID() throws Exception {
        Playlist playlist = new Playlist("abc", "xfinitywifi", "the list");
        assertEquals(playlist.getID(), "abc");
        playlist.setID("DEF");
        assertEquals(playlist.getID(), "DEF");
    }

    @Test
    public void TestAddSongIsInPlaylist() throws Exception {
        Playlist playlist = new Playlist("abc", "xfinitywifi", "the list");
        Song song1 = new Song("a", "b", "c", "d");
        Song song2 = new Song("e", "f", "g", "h");
        assertFalse(playlist.isInPlaylist(song1));
        assertFalse(playlist.isInPlaylist(song2));
        playlist.addSong(song1);
        assertTrue(playlist.isInPlaylist(song1));
        assertFalse(playlist.isInPlaylist(song2));
        playlist.addSong(song2);
        assertTrue(playlist.isInPlaylist(song1));
        assertTrue(playlist.isInPlaylist(song2));
    }

    @Test
    public void TestGetSong() throws Exception {
        Playlist playlist = new Playlist("abc", "xfinitywifi", "the list");
        Song[] songs = {
                new Song("a", "b", "c", "d"),
                new Song("e", "f", "g", "h"),
                new Song("i", "j", "k", "l"),
        };
        for(int i = 0; i < songs.length; i++) {
            playlist.addSong(songs[i]);
        }
        for(int i = 0; i < songs.length; i++) {
            assertEquals(playlist.getSongAt(i), songs[i]);
        }
        for(int i = 0; i < songs.length; i++) {
            assertEquals(playlist.getSongAt(songs[i].getUri()), songs[i]);
        }
    }

    @Test
    public void TestRemove() throws Exception {
        Playlist playlist = new Playlist("abc", "xfinitywifi", "the list");
        Song[] songs = {
                new Song("a", "b", "c", "d"),
                new Song("e", "f", "g", "h"),
                new Song("i", "j", "k", "l"),
                new Song("m", "n", "o", "p"),
        };
        Song[] expected = {
                new Song("a", "b", "c", "d"),
                new Song("i", "j", "k", "l"),

        };
        for(int i = 0; i < songs.length; i++) {
            playlist.addSong(songs[i]);
        }
        playlist.remove(new Song("e", "f", "g", "h"));
        playlist.removeAt(2);
        List<Song> queue = playlist.getQueue();
        for(int i = 0; i < queue.size(); i++) {
            assertEquals(queue.get(i).getName(), expected[i].getName());
        }
    }

    @Test
    public void TestVetoScan() throws Exception {
        Playlist playlist = new Playlist("abc", "xfinitywifi", "the list");
        Song[] songs = {
                new Song("a", "b", "c", "d"),
                new Song("e", "f", "g", "h"),
                new Song("i", "j", "k", "l"),
                new Song("m", "n", "o", "p"),
        };
        Song[] expected = {
                new Song("a", "b", "c", "d"),
                new Song("e", "f", "g", "h"),
                new Song("i", "j", "k", "l"),

        };
        for(int i = 0; i < songs.length; i++) {
            playlist.addSong(songs[i]);
        }
        songs[3].addUpvote();
        songs[3].addDownvote();
        songs[3].addUpvote();
        songs[3].addDownvote();
        songs[3].addUpvote();
        songs[3].addDownvote();
        playlist.vetoScan();
        List<Song> queue = playlist.getQueue();
        for(int i = 0; i < queue.size(); i++) {
            assertEquals(queue.get(i).getName(), expected[i].getName());
        }
    }

    @Test
    public void TestSizeClear() throws Exception {
        Playlist playlist = new Playlist("abc", "xfinitywifi", "the list");
        Song[] songs = {
                new Song("a", "b", "c", "d"),
                new Song("e", "f", "g", "h"),
                new Song("i", "j", "k", "l"),
                new Song("m", "n", "o", "p"),
        };
        assertEquals(playlist.size(), 0);
        assertTrue(playlist.isEmpty());
        for(int i = 0; i < songs.length; i++) {
            playlist.addSong(songs[i]);
        }
        assertEquals(playlist.size(), 4);
        assertFalse(playlist.isEmpty());

        playlist.clear();
        assertEquals(playlist.size(), 0);
        assertTrue(playlist.isEmpty());
    }

    @Test
    public void TestSortPop() throws Exception {
        Playlist playlist = new Playlist("abc", "xfinitywifi", "the list");
        Song[] songs = {
                new Song("a", "b", "c", "d"),
                new Song("e", "f", "g", "h"),
                new Song("i", "j", "k", "l"),
                new Song("m", "n", "o", "p"),
        };
        Song[] expectedOrder = {
                new Song("m", "n", "o", "p"),
                new Song("i", "j", "k", "l"),
                new Song("a", "b", "c", "d"),
                new Song("e", "f", "g", "h"),
        };
        for(int i = 0; i < songs.length; i++) {
            playlist.addSong(songs[i]);
        }
        songs[0].addDownvote();
        songs[0].addUpvote();
        songs[0].addUpvote();
        songs[1].addUpvote();
        songs[3].addUpvote();
        songs[3].addUpvote();
        songs[3].addUpvote();
        songs[3].addUpvote();
        songs[2].addUpvote();
        songs[2].addUpvote();
        songs[2].addUpvote();
        playlist.sort();
        for(int i = 0; i < expectedOrder.length; i++) {
            assertEquals(playlist.popSong().getName(), expectedOrder[i].getName());
            assertEquals(playlist.size(), expectedOrder.length - (1 + i));
        }
        assertTrue(playlist.isEmpty());
    }
}