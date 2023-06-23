package com.example.offersnear;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.offersnear.databinding.ActivityMapsBinding;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng Nirmal_Furniture = new LatLng(28.552500, 77.267420);
        LatLng Reliance = new LatLng(28.529997,77.251164);

        LatLng sports_ico = new LatLng(28.542051,77.252938);



        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Nirmal_Furniture, 15));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Reliance, 15));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sports_ico, 15));

// Zoom in, animating the camera.
        mMap.animateCamera(CameraUpdateFactory.zoomIn());

// Zoom out to zoom level 10, animating with a duration of 2 seconds.
        mMap.animateCamera(CameraUpdateFactory.zoomTo(10), 2000, null);

        mMap.addMarker(new MarkerOptions().position(Nirmal_Furniture).title("Nirmal Furniture"));
        mMap.addMarker(new MarkerOptions().position(Reliance).title("Reliance"));
        mMap.addMarker(new MarkerOptions().position(sports_ico).title("Icon Sports Wear"));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(Nirmal_Furniture));
    }
}