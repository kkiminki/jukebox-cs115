package com.sideproject.ryanbrounley.jukebox_android;

import android.content.Context;
import android.location.LocationManager;
import android.util.Log;

/**
 * Committed by Matthew Mayr on 11/3/2016.
 */

class RetrieveWifiLocation {

    private Context lContext;
    RetrieveWifiLocation(Context lContext) {
        this.lContext = lContext;
    }
    LocationManager lm = (LocationManager) lContext.getSystemService(Context.LOCATION_SERVICE);

    LocationManager getLocation() {
        if (lm != null) {
            boolean wifiEnabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            //gets rough location from wifi name every 10 seconds or 10 meters
            if (wifiEnabled) {
                lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000L, 10F, (android.location.LocationListener) this);
                return lm;
            } else {
                wifiError();
            }
        } else {
            System.out.println ("Something went wrong with location manager.");
        }
        return lm;
    }

    private void wifiError() { Log.d("RetrieveWifiLocation", "Wifi is not connected."); }
}
