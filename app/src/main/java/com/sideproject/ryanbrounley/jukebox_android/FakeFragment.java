package com.sideproject.ryanbrounley.jukebox_android;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.spotify.sdk.android.player.Player;
import com.spotify.sdk.android.player.Spotify;

/**
 * Created by ryanbrounley on 10/23/15.
 */
public class FakeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.temp_frag, container, false);


        return v;
    }
}
