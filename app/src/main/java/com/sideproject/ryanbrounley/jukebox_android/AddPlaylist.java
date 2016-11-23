package com.sideproject.ryanbrounley.jukebox_android;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.*;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sideproject.ryanbrounley.jukebox_android.Firebase.AddPlaylistQuery;
import com.sideproject.ryanbrounley.jukebox_android.Playlist.Playlist;
import com.sideproject.ryanbrounley.jukebox_android.ui.MainThreeTabActivity;
import com.sideproject.ryanbrounley.jukebox_android.ui.NoTabActivity;

/**
 * Created by ryanbrounley on 11/22/16.
 */

public class AddPlaylist extends NoTabActivity {

    @Override
    protected AddPlaylistFragment getInitialFragment() {
        Bundle args = getIntent().getExtras();

        getSupportFragmentManager().beginTransaction();
        return AddPlaylistFragment
                .newInstance();
    }


    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }


    @Override
    public boolean onSupportNavigateUp() {
        //This method is called when the up button is pressed. Just the pop back stack.
        this.finish();
        return true;
    }



    /**
     * A placeholder fragment containing a simple view.
     */

    //TODO refactor
    public static class AddPlaylistFragment extends Fragment {

        private LinearLayout listlay;
        private LinearLayout favrlay;
        boolean isPersonal;

        String id;
        String username;
        String fullname;
        int currentlyInflated;

        View v;
        View root;


        public static AddPlaylistFragment newInstance() {
            return new AddPlaylistFragment();
        }

        //Puts the intent string values into their specified spots on the
        //Profile XML page.
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            v = inflater.inflate(R.layout.create_playlist, container, false);

            final EditText name = (EditText) v.findViewById(R.id.playlist_name);
            final EditText ssid = (EditText) v.findViewById(R.id.wifi_name);
            Button submit = (Button) v.findViewById(R.id.submit_playlist);

            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                            String str = "Playlist needs a name!";
                            if(name.getText()==null || name.getText().equals(str)){
                                Log.i("PlayerFragment", "Playlist name not set");
                                name.setText(str);
                            }else{
                                Log.i("PlayerFragment", "Initializing new playlist");
                                Log.i("PlayerFragment", "Playlist name = "+name.getText().toString()
                                                         + "wifi = "+ssid);
                                Playlist p = new Playlist(ssid.getText().toString(), name.getText().toString());
                                AddPlaylistQuery apq = new AddPlaylistQuery(ssid.getText().toString(), name.getText().toString(), getActivity());
                                apq.executeAndUpdate(p);
                                getActivity().finish();
                            }
                }
            });

            return v;
        }

        @Override
        public void onResume(){
            super.onResume();
            setHasOptionsMenu(true);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle("Add a Playlist");
        }

        @Override
        public void onPause(){
            super.onPause();
            ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            setHasOptionsMenu(false);
        }

        @Override
        public void onActivityResult(int requestCode, int resultCode, Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
