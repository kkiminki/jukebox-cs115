package com.sideproject.ryanbrounley.jukebox_android;

import android.support.v4.app.Fragment;

import com.sideproject.ryanbrounley.jukebox_android.ui.MainThreeTabActivity;

/**
 * Created by ryanbrounley on 10/22/15.
 */
public class Menu extends MainThreeTabActivity {

    @Override
    protected Fragment getInitialFragment() {
        return new FakeFragment();
    }
}
