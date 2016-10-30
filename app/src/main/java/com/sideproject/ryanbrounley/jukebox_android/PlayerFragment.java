package com.sideproject.ryanbrounley.jukebox_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.content.Context;

import com.spotify.sdk.android.player.Config;
import com.spotify.sdk.android.player.ConnectionStateCallback;
import com.spotify.sdk.android.player.Player;
import com.spotify.sdk.android.player.Spotify;

/**
 * Created by kylerkiminki on 10/21/16.
 * */

public class PlayerFragment extends Fragment  {

    // TODO: Replace with your client ID
    private static final String CLIENT_ID = "yourclientid";
    // TODO: Replace with your redirect URI
    private static final String REDIRECT_URI = "testschema://callback";
    private static final String TAG = "PlayerActivity";
    private static final String test_uri = "spotify:track:17xbKoCF5iDcSb9usFt2yO";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_player, container, false);

        String accessToken = getArguments().getString("AccessToken");
        Log.d("PlayerFragment", "Access Token = "+accessToken);
        final Menu menuContext = (Menu) getActivity();

        final Button playbtn = (Button)v.findViewById(R.id.play_button);
        Button prevbtn = (Button)v.findViewById(R.id.prev_button);
        Button nextbtn = (Button)v.findViewById(R.id.next_button);

        menuContext.PlayerRepeat(true);
        playbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(playbtn.getText().equals("play")) {
                    menuContext.PlaySong(test_uri);
                    playbtn.setText("pause");
                }else {
                    menuContext.PausePlayer();
                    Log.d(TAG, "playbtn text = "+playbtn.getText());
                }
            } });

        prevbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuContext.PlayPrev();
            } });

        nextbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                menuContext.PlayNext();
            } });


        return v;
    }

}
