package com.sideproject.ryanbrounley.jukebox_android;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.sideproject.ryanbrounley.jukebox_android.Playlist.*;


/**
 * Created by kylerkiminki on 10/21/16.
 * */

public class PlayerFragment extends Fragment  {

    private static final String TAG = "PlayerActivity";
    private static final String test_uri = "spotify:track:17xbKoCF5iDcSb9usFt2yO";
    private static final String test_2 = "spotify:track:6RNgdXbb4yfKeihXYilZJI";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.fragment_player, container, false);

        final Menu menuContext = (Menu) getActivity();

        final Button playbtn = (Button)v.findViewById(R.id.play_button);
        Button prevbtn = (Button)v.findViewById(R.id.prev_button);
        Button nextbtn = (Button)v.findViewById(R.id.next_button);

        Song song1 = new Song(test_uri);
        Song song2 = new Song(test_2);
        menuContext.PlayerEnqueue(song1);
        menuContext.PlayerEnqueue(song2);
        menuContext.PlaySong(song1.getUri());
        menuContext.PausePlayer();

        menuContext.PlayerRepeat(true);
        playbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(playbtn.getText().equals("play")) {
                    menuContext.ResumePlayer();
                    playbtn.setText("pause");
                }else {
                    menuContext.PausePlayer();
                    playbtn.setText("play");
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
