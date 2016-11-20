package com.sideproject.ryanbrounley.jukebox_android;

import android.content.Context;
import android.location.LocationManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.util.Log;

/**
 * Committed by Matthew Mayr on 11/3/2016.
 */

public class RetrieveWifiLocation {

    private Context lContext;
    private LocationManager lm;
    private WifiManager wm;
    public RetrieveWifiLocation(Context lContext) {
        this.lContext = lContext;
        lm = (LocationManager) lContext.getSystemService(Context.LOCATION_SERVICE);
        wm = (WifiManager)lContext.getSystemService(Context.WIFI_SERVICE);
    }

    public boolean wifiEnabled(){
        return lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    public void getLocation() {
        //gets rough location from wifi name every 10 seconds or 10 meters
        if (wifiEnabled()) {
            lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 10000L, 10F, (android.location.LocationListener) this);
        } else {
            wifiError();
        }
    }

    public void wifiError() { Log.d("RetrieveWifiLocation", "Wifi is not connected."); }

    public String getWifiName(){
        WifiInfo wifiInfo = wm.getConnectionInfo();
        return wifiInfo.getSSID();
    }
}