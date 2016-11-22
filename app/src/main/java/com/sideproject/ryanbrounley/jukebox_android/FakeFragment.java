package com.sideproject.ryanbrounley.jukebox_android;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sideproject.ryanbrounley.jukebox_android.Firebase.AddPlaylistQuery;
import com.sideproject.ryanbrounley.jukebox_android.Firebase.AddSongQuery;
import com.sideproject.ryanbrounley.jukebox_android.Firebase.GetPlaylistsQuery;

/**
 * Created by ryanbrounley on 10/23/15.
 */
public class FakeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View v = inflater.inflate(R.layout.temp_frag, container, false);
        final Menu menu = (Menu) getActivity();
        menu.onPlayer=false;

        /*GetPlaylistsQuery g = new GetPlaylistsQuery(getActivity());
        g.executeAndUpdate();

        AddPlaylistQuery f = new AddPlaylistQuery("test3", "test4", getActivity());
        f.executeAndUpdate();

        AddSongQuery e = new AddSongQuery("testSong", "test2", "testURI", "Ok Computer", getActivity());
        e.executeAndUpdate("-KX9iF5OplG9gzXuEwnS");
*/
        return v;
    }
}