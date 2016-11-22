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

import com.sideproject.ryanbrounley.jukebox_android.R;

import org.w3c.dom.Text;


//A class that should be subclassed. It automatically includes the three
//buttons at the bottom and contains a content fragment
//Usually you will set 3 onClickListeners for each button and have them change
//the content by overriding the setUp function in a subclass
//and will return the initial fragment to display by overriding the getInitialFragment function
//in a subclass
public abstract class ThreeTabActivity extends AppCompatActivity {
    Toolbar toolbar;
    TextView title;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.widget_three_tab_bottom);

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
        setToolbarText(title, "Now Playing");
        setSupportActionBar(toolbar);

        //sets the up arrow to white -- not the default
//        final Drawable upArrow = ContextCompat.getDrawable(this, R.drawable.abc_ic_ab_back_mtrl_am_alpha);
//        getSupportActionBar().setHomeAsUpIndicator(upArrow);

        if(savedInstanceState == null) {
            //Initialize by adding the initial fragment as the content
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, getInitialFragment())
                    .commit();
        }
        setUp();
    }

    @Override
    public boolean onSearchRequested() {

        return super.onSearchRequested();
    }

    public void setToolbarText(TextView title, CharSequence text){
        title.setText(text);
    }

    public void setSearchVisible(boolean visible){
        if(visible) {
            title.setVisibility(View.INVISIBLE);
            searchView.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        //inflater.inflate(R.menu.menu_favr, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onSupportNavigateUp() {
        //This method is called when the up button is pressed. Just the pop back stack.
        getSupportFragmentManager().popBackStack();
        return true;
    }



    //The fragment returned by this function will be set as the initial fragment displayed
    abstract protected Fragment getInitialFragment();

    //This function should be overridden to set up the listeners to the three tabs
    abstract protected void setUp();

    //Ease of use functions that allow you to set onClickListeners to the
    //left right and middle tabs.
    //TODO change form using onClickListener to using RadioButton type listeners
    protected void setLeftTabListener(View.OnClickListener listener) {
        findViewById(R.id.tab3_left_btn).setOnClickListener(listener);
    }

    protected void setMiddleTabListener(View.OnClickListener listener) {
        findViewById(R.id.tab3_middle_btn).setOnClickListener(listener);
    }

    protected void setRightTabListener(View.OnClickListener listener) {
        findViewById(R.id.tab3_right_btn).setOnClickListener(listener);
    }

    protected void setRightClicked(boolean isClicked){
        findViewById(R.id.tab3_right_btn).setEnabled(isClicked);
    }

    protected void setMiddleClicked(boolean isClicked){
        findViewById(R.id.tab3_middle_btn).setEnabled(isClicked);
    }

    protected void setLeftClicked(boolean isClicked){
        findViewById(R.id.tab3_left_btn).setPressed(isClicked);
    }
    //Ease of use function that allows subclasses to easily
    //change the current fragment (the content on top of the 3 buttons)
    protected void setContent(Fragment frag) {
        String name = getClass().getName();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, frag)
                .commit();
    }

    //Utility class that can be set as an onClickListener for a button
    //When the button is clicked the current fragment is changed to
    //mFrag
    protected class OnClickChangeContent implements View.OnClickListener {
        private Fragment mFrag;
        private CharSequence titleText;
        public OnClickChangeContent(Fragment frag, CharSequence text) {
            mFrag = frag;
            titleText = text;
        }
        @Override
        public void onClick(View v) {
            //   clearBackStack();
            setContent(mFrag);
            setToolbarText(title, titleText);
        }

//        public void clearBackStack() {
//            for (int i = 0; i < getSupportFragmentManager().getBackStackEntryCount(); ++i) {
//                getSupportFragmentManager().popBackStack();
//            }
//        }
    }

}
