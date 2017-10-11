package fr.louisparet.journeydiaries.permission;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import fr.louisparet.journeydiaries.MainActivity;
import fr.louisparet.journeydiaries.fragments.GeolocFragment;

/**
 * Created by hugo.blanc on 10/10/17.
 */

public class PermissionHelper implements LocationListener {

    MainActivity activity;
    GeolocFragment geolocFragment;

    public void askPermission(GeolocFragment fragment, MainActivity activity) {
        this.geolocFragment = fragment;
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION};
            ActivityCompat.requestPermissions(activity, permissions, 1);
        }

        LocationManager manager = (LocationManager) activity.getSystemService(Context.LOCATION_SERVICE);
        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 100, this);
        manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 100, 100, this);
    }

    @Override
    public void onLocationChanged(Location newLocation) {
        System.out.println("Location Changed" + newLocation);
        this.geolocFragment.updateView(newLocation);
    }

    @Override
    public void onStatusChanged(String provider, int
            status, Bundle extras) {
        System.out.println("Status Changed : " + provider + " status :" + status + " extras: " + extras.toString());
    }

    @Override
    public void onProviderEnabled(String provider) {
        System.out.println("Provide enable: " + provider);
    }

    @Override
    public void onProviderDisabled(String provider) {
        System.out.println("Provide disable : " + provider);
    }
}