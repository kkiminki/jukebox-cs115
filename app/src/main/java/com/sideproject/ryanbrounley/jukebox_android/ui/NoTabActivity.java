package com.sideproject.ryanbrounley.jukebox_android.ui;

/**
 * Created by ryanbrounley on 10/22/15.
 */

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.*;
import android.widget.TextView;

import android.app.Activity;
import android.app.Notification;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.sideproject.ryanbrounley.jukebox_android.AddPlaylist;
import com.sideproject.ryanbrounley.jukebox_android.R;

import org.w3c.dom.Text;


//A class that should be subclassed. It automatically includes the three
//buttons at the bottom and contains a content fragment
//Usually you will set 3 onClickListeners for each button and have them change
//the content by overriding the setUp function in a subclass
//and will return the initial fragment to display by overriding the getInitialFragment function
//in a subclass
public abstract class NoTabActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.notabs);

        //intended to predict when there is a change in the backstack and resets the name of the
        //toolbar title via setting within the fragment
        this.getSupportFragmentManager()
                .addOnBackStackChangedListener(
                        new FragmentManager.OnBackStackChangedListener() {
                            @Override
                            public void onBackStackChanged() {setToolbarText(title, toolbar.getTitle());
                            }
                        });

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        title = (TextView) findViewById(R.id.page_name);
        //searchView = (SearchView) findViewById(R.id.search_toolbar_threetab);

        //setSearchVisible(true);

        //nulls out title from laying on toolbar. .setDisplayTitleEnabled(false)
        //made the value of the title null as well which didn't allow for specific
        //page names drawn from resetting the title value in the fragment
        setToolbarText(title, "Add a Playlist");
        setSupportActionBar(toolbar);

        if(savedInstanceState == null) {
            //Initialize by adding the initial fragment as the content
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, getInitialFragment())
                    .commit();
        }
    }

    public void setToolbarText(TextView title, CharSequence text){
        title.setText(text);
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.playlist_list, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.add_playlist:
                Intent i = new Intent(this, AddPlaylist.class);
                startActivity(i);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        //This method is called when the up button is pressed. Just the pop back stack.
        getSupportFragmentManager().popBackStack();
        return true;
    }

    //The fragment returned by this function will be set as the initial fragment displayed
    abstract protected Fragment getInitialFragment();

}