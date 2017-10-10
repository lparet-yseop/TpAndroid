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

/**
 * Created by hugo.blanc on 10/10/17.
 */

public class PermissionHelper {


    public void askPermission(MainActivity activity) {

        System.out.println(ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED);

        ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION}, );


        LocationListener myLocationListener = new
                LocationListener() {
                    @Override
                    public void onLocationChanged(Location newLocation) {
                        System.out.println("Location Changed"+ newLocation);
                    }

                    @Override
                    public void onStatusChanged(String provider, int
                            status, Bundle extras) {
                        System.out.println("Status Changed : "+provider + " status :"+ status + " extras: "+extras.toString() );
                    }

                    @Override
                    public void onProviderEnabled(String provider) {
                        System.out.println("Provide enable: "+provider );
                    }

                    @Override
                    public void onProviderDisabled(String provider) {
                        System.out.println("Provide disable : "+provider );
                    }
                };


        LocationManager manager = (LocationManager)activity.getSystemService(Context.LOCATION_SERVICE);
        manager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 120, 100, myLocationListener);
        manager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 120, 100, myLocationListener);


        //ActivityCompat.requestPermissions(activity, permissions, 1);

//        if (ActivityCompat.checkSelfPermission(activity,
//                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION};
//            ActivityCompat.requestPermissions(activity, permissions, 1);
//        }
    }


//        @Override
//        public void onRequestPermissionsResult(int
//        requestCode, @NonNull final String[]
//        permissions, @NonNull int[] grantResults) {
//            super.onRequestPermissionsResult(requestCode,
//                    permissions, grantResults);
////do some work
//        }


//        public void dealWithValue(){
//        LocationManager manager = (LocationManager)
//                getSystemService(Context.LOCATION_SERVICE);
//        manager.requestLocationUpdates(
//                LocationManager.GPS_PROVIDER, 120, 100,
//                myLocationListener);
//        manager.requestLocationUpdates(
//                LocationManager.NETWORK_PROVIDER, 120, 100,
//                myLocationListener);

    // 120 actualisation

    // 100 distance (ou l'inverse)

}


//
//    LocationListener myLocationListener = new
//            LocationListener(){
//                @Override
//                public void onLocationChanged(Location newLocation){
//                }
//                @Override
//                public void onStatusChanged(String provider, int
//                        status, Bundle extras){
//                }
// Changement de status (reseau vs gps)
//                @Override
//                public void onProviderEnabled(String provider){
//                }
//                @Override
//                public void onProviderDisabled(String provider){
//                }
//            }
