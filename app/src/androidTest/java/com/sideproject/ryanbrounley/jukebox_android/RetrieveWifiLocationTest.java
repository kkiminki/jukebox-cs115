package com.sideproject.ryanbrounley.jukebox_android;

import android.content.Context;
import android.location.LocationManager;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Dell on 11/14/2016.
 */
public class RetrieveWifiLocationTest {
    Context tContext;
    private RetrieveWifiLocation rwl = new RetrieveWifiLocation(tContext);
    @Test
        public void testNullLm () {
            rwl.getLocation();
            assertNotNull(rwl.lm);
        }

}