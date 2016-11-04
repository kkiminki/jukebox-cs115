package com.sideproject.ryanbrounley.jukebox_android;

import android.content.Context;
import android.location.LocationManager;
import android.util.Log;

/**
 * Committed by Matthew Mayr on 11/3/2016.
 */

public class RetrieveWifiLocation extends FakeFragment { //extends FakeFragment maybe?

    LocationManager lm=(LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
    boolean wifiEnabled = lm
            .isProviderEnabled(LocationManager.NETWORK_PROVIDER);

    public void getLocation() {
        //gets rough location from wifi name every 10 seconds or 10 meters
        if (wifiEnabled) {
            lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000L, 10F, (android.location.LocationListener) this);
        } else {
            wifiError();
        }
    }

    public void wifiError() { Log.d("RetrieveWifiLocation", "Wifi is not connected."); }
}
