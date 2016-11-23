package com.sideproject.ryanbrounley.jukebox_android;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import com.sideproject.ryanbrounley.jukebox_android.ui.MainThreeTabActivity;
import com.sideproject.ryanbrounley.jukebox_android.Playlist.*;
import com.spotify.sdk.android.player.Config;
import com.spotify.sdk.android.player.ConnectionStateCallback;
import com.spotify.sdk.android.player.Player;
import com.spotify.sdk.android.player.PlayerNotificationCallback;
import com.spotify.sdk.android.player.PlayerState;
import com.spotify.sdk.android.player.Spotify;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by ryanbrounley on 10/22/15.
 *
 * Edited by Kyler Kiminki 10/22/16
 */

public class Menu extends MainThreeTabActivity implements PlayerNotificationCallback, ConnectionStateCallback{

    Bundle args;
    private static final String CLIENT_ID = "b2e9ab519e00426cbc10567e290ea8fd";
    //Spotify player
    public  Player mPlayer;
    //The running playlist
    public Playlist playlist;
    //boolean stating if the player is actively playing a song
    public boolean playing = false;
    //boolean stating if the fragment is playerFragment
    public boolean onPlayer = false;
    //String holding the currently playing song
    public String current="No song currently playing";
    //Fragment manager for the menu activity
    public FragmentManager fm =getSupportFragmentManager();
    //List of played songs
    public List<Song> songPlayed = new LinkedList<Song>();

    //Configures the player with the access token passed in
    //from MainActivity
    @Override
    public void onCreate(Bundle savedInstanceState){

        //Make a new player with the access
        //token from the login activity
        playlist = new Playlist();
        args = getIntent().getExtras();
        Log.d("Menu", "AccessToken = "+args.getString("AccessToken"));
        Config playerConfig = new Config(this, args.getString("AccessToken"), CLIENT_ID);
        mPlayer = Spotify.getPlayer(playerConfig, this, new Player.InitializationObserver() {

            //Adding callbacks for the connection status
            //and for player notifications
            @Override
            public void onInitialized(Player player) {
                mPlayer.addConnectionStateCallback(Menu.this);
                mPlayer.addPlayerNotificationCallback(Menu.this);
            }

            //Logs a fatal error if the player cannot be
            //initialized
            @Override
            public void onError(Throwable throwable) {
                Log.wtf("Menu", "Could not initialize player: " + throwable.getMessage());
            }
        });
        super.onCreate(savedInstanceState);
    }

    //Helper function to play a song
    public void PlaySong(Song song){
        playlist.addSong(song);
        current = song.getArtists()+": "
                +song.getName();
        mPlayer.play(song.getUri());
        Log.d("Menu", "Calling PlaySong");
        Log.d("Menu", "URI = "+song);
        songPlayed.add(song);
        if(onPlayer)
            updatePlayer();
    }

    public void ResumePlayer(){
        mPlayer.resume();
    }

    //Helper function to set the repeat value on the player
    public void PlayerRepeat(boolean b){
        mPlayer.setRepeat(b);
    }

    //Helper function to put a song in the queue
    public void PlayerEnqueue(Song song){
        Log.d("Menu", "song uri = "+song.getUri());
        playlist.addSong(song);
        if(onPlayer)
            updatePlayer();
    }

    public void PlayerRemoveSong(Song song){
        playlist.remove(song);
    }

    //Sets the initial fragment of the menu activity
    @Override
    protected Fragment getInitialFragment() {
        Fragment playerFragment = new PlaylistFragment();
        playerFragment.setArguments(args);
        return playerFragment;
    }

    @Override
    public void onLoggedIn() {
        Log.d("Menu", "User logged in");
    }

    @Override
    public void onLoggedOut() {
        Log.d("Menu", "User logged out");
    }

    @Override
    public void onLoginFailed(Throwable error) {
        Log.d("Menu", "Login failed");
    }

    @Override
    public void onTemporaryError() {
        Log.d("Menu", "Temporary error occurred");
    }

    @Override
    public void onConnectionMessage(String message) {
        Log.d("Menu", "Received connection message: " + message);
    }

    @Override
    public void onPlaybackEvent(PlayerNotificationCallback.EventType eventType, PlayerState playerState) {
        Log.d("Menu", "Playback event received: " + eventType.name());
        switch (eventType) {
            case PLAY:
                playing=true;
                break;
            case END_OF_CONTEXT:
                playing=false;
                break;
            case TRACK_END:
                if(!playlist.isEmpty()){
                    current =  playlist.getSongAt(0).getArtists()+": "
                            +playlist.getSongAt(0).getName();
                    Log.i("Menu", "In TRACK_CHANGED current = "+current);
                    songPlayed.add(playlist.getSongAt(0));
                    mPlayer.queue(playlist.popSong().getUri());
                    //firebase.getPlayist
                    //menu.playlist.hasPlayer(songsPlayed);
                    playlist.vetoScan();
                    playlist.sort();
                    if(onPlayer)
                        updatePlayer();
                }else{
                    current = "No song currently playing";
                    Log.i("Menu", "In else of TRACK_CHANGED current = "+current);
                    if(onPlayer)
                        updatePlayer();
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onPlaybackError(PlayerNotificationCallback.ErrorType errorType, String errorDetails) {
        Log.d("Menu", "Playback error received: " + errorType.name());
        switch (errorType) {

            // Handle error type as necessary
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        // VERY IMPORTANT! This must always be called or else you will leak resources
        Spotify.destroyPlayer(this);
        super.onDestroy();
    }

    public void updatePlayer(){
        fm.beginTransaction().replace(R.id.container, new PlayerFragment()).commit();
    }
}
