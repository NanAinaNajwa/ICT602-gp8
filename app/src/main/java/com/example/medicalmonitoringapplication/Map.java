package com.example.medicalmonitoringapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.medicalmonitoringapplication.databinding.ActivityMapBinding;

import java.util.Vector;

public class Map extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapBinding binding;

    MarkerOptions marker;
    LatLng centerLocation;

    Vector<MarkerOptions> markerOptions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        centerLocation = new LatLng(3.0,101);

        markerOptions = new Vector<>();

        markerOptions.add(new MarkerOptions().title("Hospital Alor Setar")
                .position(new LatLng(6.12,100.37))
                .snippet("Open during MCO : 24 Hour")
        );

        markerOptions.add(new MarkerOptions().title("Hospital Bayan Lepas")
                .position(new LatLng(5.29,100.259))
                .snippet("Open during MCO : 24 Hour")
        );

        markerOptions.add(new MarkerOptions().title("Hospital Ipoh")
                .position(new LatLng(4.61,101.88))
                .snippet("Open during MCO : 24 Hour")
        );
        markerOptions.add(new MarkerOptions().title("Hospital Gua Musang")
                .position(new LatLng(4.8721,100.96))
                .snippet("Open during MCO : 24 Hour")
        );
        markerOptions.add(new MarkerOptions().title("Hospital Arau")
                .position(new LatLng(6.45,100.29))
                .snippet("Open during MCO : 24 Hour")
        );

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
        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));

        for (MarkerOptions mark : markerOptions) {
            mMap.addMarker(mark);
        }

        enableMyLocation();

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(centerLocation,8));
    }

    private void enableMyLocation(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            if (mMap != null){
                mMap.setMyLocationEnabled(true);
            }
        }else {
            String perms [] = {"android.permission.ACCESS_FINE_LOCATION"};
            ActivityCompat.requestPermissions(this,perms,200);
        }
    }
}