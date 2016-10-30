package com.sideproject.ryanbrounley.jukebox_android;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.content.Context;
import com.sideproject.ryanbrounley.jukebox_android.ui.MainThreeTabActivity;
import com.spotify.sdk.android.player.Config;
import com.spotify.sdk.android.player.ConnectionStateCallback;
import com.spotify.sdk.android.player.Player;
import com.spotify.sdk.android.player.PlayerNotificationCallback;
import com.spotify.sdk.android.player.PlayerState;
import com.spotify.sdk.android.player.Spotify;

/**
 * Created by ryanbrounley on 10/22/15.
 */
public class Menu extends MainThreeTabActivity implements PlayerNotificationCallback, ConnectionStateCallback{
    Bundle args;
    private static final String CLIENT_ID = "b2e9ab519e00426cbc10567e290ea8fd";
    private Player mPlayer;

    @Override
    public void onCreate(Bundle savedInstanceState){
        args = getIntent().getExtras();
        Log.d("Menu", "AccessToken = "+args.getString("AccessToken"));
        Config playerConfig = new Config(this, args.getString("AccessToken"), CLIENT_ID);
        mPlayer = Spotify.getPlayer(playerConfig, this, new Player.InitializationObserver() {
            @Override
            public void onInitialized(Player player) {
                mPlayer.addConnectionStateCallback(Menu.this);
                mPlayer.addPlayerNotificationCallback(Menu.this);
            }

            @Override
            public void onError(Throwable throwable) {
                Log.e("Menu", "Could not initialize player: " + throwable.getMessage());
            }
        });
        super.onCreate(savedInstanceState);
    }

    public void PlaySong(String song){
        mPlayer.play(song);
        Log.d("Menu", "Calling PlaySong");
        Log.d("Menu", "URI = "+song);
    }

    public void PausePlayer(){
        mPlayer.pause();
        Log.d("Menu", "Calling Pause");
    }

    public void PlayNext(){
        mPlayer.skipToNext();
        Log.d("Menu", "Calling play next");
    }

    public void PlayPrev(){
        mPlayer.skipToPrevious();
        Log.d("Menu", "Calling play prev");
    }

    public void PlayerRepeat(boolean b){
        mPlayer.setRepeat(b);
    }

    @Override
    protected Fragment getInitialFragment() {
        Fragment playerFragment = new PlayerFragment();
        Context menuContext = this;
        playerFragment.setArguments(args);
        return playerFragment;
    }

    @Override
    public void onLoggedIn() {
        Log.d("MainActivity", "User logged in");
    }

    @Override
    public void onLoggedOut() {
        Log.d("MainActivity", "User logged out");
    }

    @Override
    public void onLoginFailed(Throwable error) {
        Log.d("MainActivity", "Login failed");
    }

    @Override
    public void onTemporaryError() {
        Log.d("MainActivity", "Temporary error occurred");
    }

    @Override
    public void onConnectionMessage(String message) {
        Log.d("MainActivity", "Received connection message: " + message);
    }

    @Override
    public void onPlaybackEvent(PlayerNotificationCallback.EventType eventType, PlayerState playerState) {
        Log.d("MainActivity", "Playback event received: " + eventType.name());
        switch (eventType) {
            // Handle event type as necessary
            default:
                break;
        }
    }

    @Override
    public void onPlaybackError(PlayerNotificationCallback.ErrorType errorType, String errorDetails) {
        Log.d("MainActivity", "Playback error received: " + errorType.name());
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
}
