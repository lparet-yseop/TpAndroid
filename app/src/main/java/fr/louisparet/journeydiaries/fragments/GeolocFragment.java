package fr.louisparet.journeydiaries.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.location.Location;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import fr.louisparet.journeydiaries.MainActivity;
import fr.louisparet.journeydiaries.R;
import fr.louisparet.journeydiaries.databinding.GeolocBinding;
import fr.louisparet.journeydiaries.databinding.JourneyCreatorBinding;
import fr.louisparet.journeydiaries.permission.PermissionHelper;
import fr.louisparet.journeydiaries.service.GpsTracker;

/**
 * Created by hugo.blanc on 10/10/17.
 */

public class GeolocFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener {

    Context context;
    MainActivity mainActivity;

    GoogleMap mmap;
    GpsTracker gps;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        GeolocBinding binding = DataBindingUtil.inflate(inflater, R.layout.geoloc,container,false);

        MapFragment mapFragment = (MapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        binding.sendComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initButton();
            }
        });

        return binding.getRoot();
    }


    private void initButton(){

    }

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        this.mainActivity = (MainActivity)context;
    }

    // API VERSION AVANT 23
    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        this.mainActivity = (MainActivity)activity;
    }

    @Override
    public void onMapLongClick(LatLng latLng) {
        updateView(latLng);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        map.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
        this.mmap = map;
        this.mmap.setOnMapLongClickListener(this);
        try {
            this.mmap.setMyLocationEnabled(true);
        } catch (SecurityException ex) {
            System.out.println("This is not possible.");
        }
    }

    public void updateView(Location location){
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        updateView(latLng);
    }

    public void updateView(LatLng latLng) {
        if(mmap != null) {
            this.mmap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 5));
            this.mmap.addMarker(new MarkerOptions().position(latLng).title("Marker"));
        }
    }
}
