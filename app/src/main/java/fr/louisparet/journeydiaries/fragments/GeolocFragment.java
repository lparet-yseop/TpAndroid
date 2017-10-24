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
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.LocationSource;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import fr.louisparet.journeydiaries.MainActivity;
import fr.louisparet.journeydiaries.R;
import fr.louisparet.journeydiaries.databinding.GeolocBinding;
import fr.louisparet.journeydiaries.databinding.JourneyCreatorBinding;
import fr.louisparet.journeydiaries.interaction.MainActivityContract;
import fr.louisparet.journeydiaries.models.Marker;
import fr.louisparet.journeydiaries.permission.PermissionHelper;
import fr.louisparet.journeydiaries.service.GpsTracker;
import fr.louisparet.journeydiaries.service.MarkerService;

/**
 * Created by hugo.blanc on 10/10/17.
 */

public class GeolocFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMapLongClickListener, OnMarkerClickListener {

    Context context;
    MainActivity mainActivity;

    MarkerService markerService;
    com.google.android.gms.maps.model.Marker gmapSelectedMarker;
    GeolocBinding binding;

    GoogleMap mmap;
    GpsTracker gps;

    Marker selectedMarker;
    List<Marker> markers;

    View view;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.geoloc,container,false);

        MapFragment mapFragment = (MapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        binding.sendComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initButton();
            }
        });


        markerService = new MarkerService(binding.getRoot().getContext());
        markers = markerService.findAll();

        return binding.getRoot();
    }

    private void initButton(){
        String newValue = binding.editText2.getText().toString();
        this.selectedMarker.setName(newValue);
        markerService.update(this.selectedMarker);
        this.gmapSelectedMarker.setTitle(newValue);
        this.gmapSelectedMarker.showInfoWindow();
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
        String text = "Nouveau marqueur";
        updateView(latLng);
        this.selectedMarker = new Marker(latLng.longitude, latLng.latitude, text);
        markerService.save(this.selectedMarker);
        markers.add(this.selectedMarker);
        MarkerOptions markerOptions = new MarkerOptions().position(latLng).title(text);
        this.gmapSelectedMarker = this.mmap.addMarker(markerOptions);
        binding.editText2.setText(text);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        this.mmap = map;
        map.setOnMapLongClickListener(this);
        map.setOnMarkerClickListener(this);
        try {
            this.mmap.setMyLocationEnabled(true);
        } catch (SecurityException ex) {
            System.out.println("This is not possible.");
        }
        if(markers.size() > 0){
            addMarkers(markers);
        }
    }


    private void addMarkers(List<Marker> markers){
        for(int i = 0 ; i < markers.size() ; i++){
            addMarker(markers.get(i));
        }
        if(markers.size() > 0 ){
            this.mmap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(markers.get(0).getLatitude(),markers.get(0).getLongitude()), 5));
        }

    }

    private void addMarker(Marker marker){
        MarkerOptions markerOptions = new MarkerOptions()
                .position(new LatLng(marker.getLatitude(), marker.getLongitude()))
                .title(marker.getName());
        this.mmap.addMarker(markerOptions);
    }



    public void updateView(Location location){
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
        updateView(latLng);
    }

    public void updateView(LatLng latLng ){
        if(mmap != null) {
            this.mmap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 5));
        }
    }


    @Override
    public boolean onMarkerClick(com.google.android.gms.maps.model.Marker marker) {
        System.out.println(" Id: " + marker.getId() + " Title: " + marker.getTitle() + " position: " + marker.getPosition() +" TAG : " + marker.getTag());
        String tag =  marker.getId().toString().substring(1);
        int id = Integer.parseInt(tag);
        if(id >= 0 && id < markers.size()){
            this.selectedMarker = markers.get(id);
        }
        this.gmapSelectedMarker = marker;
        binding.editText2.setText(selectedMarker.getName());
        return false;
    }
}
