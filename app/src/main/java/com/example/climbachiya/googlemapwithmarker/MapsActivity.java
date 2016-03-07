package com.example.climbachiya.googlemapwithmarker;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);



        findViewById(R.id.button_normal).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MapsActivity.this, "Normal", Toast.LENGTH_SHORT).show();
                setMapType(1);
            }
        });

        findViewById(R.id.button_satellite).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MapsActivity.this, "Satellite", Toast.LENGTH_SHORT).show();
                setMapType(2);
            }
        });

        findViewById(R.id.button_tarrain).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MapsActivity.this, "Terrain", Toast.LENGTH_SHORT).show();
                setMapType(3);
            }
        });

        findViewById(R.id.button_hybrid).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(MapsActivity.this, "Hybrid", Toast.LENGTH_SHORT).show();
                setMapType(4);
            }
        });
    }

    private void setMapType(int TYPE) {

        if(TYPE == 1)
            mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        else if(TYPE == 2)
            mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
        else if(TYPE == 3)
            mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);
        else if(TYPE == 4)
            mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
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

        setMapType(1);

        // Add a marker in Sydney and move the camera
        LatLng location = new LatLng(22.2587, 71.1924);

        //1) ----Default marker -------
         mMap.addMarker(new MarkerOptions().position(location).title("Welcome! GUJARAT (INDIA)"));
        //----Default marker -------


        //2) ------Custom Marker -------
            // create marker
            MarkerOptions marker = new MarkerOptions().position(new LatLng(34.1491, 76.826)).title("Welcome! J&K (INDIA)");
            // Changing marker icon
            marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.pin_drop_black));
            // adding marker
            mMap.addMarker(marker);
        //------Custom Marker -------



        mMap.moveCamera(CameraUpdateFactory.newLatLng(location));
        // Zoom in, animating the camera.
        //mMap.animateCamera(CameraUpdateFactory.zoomIn());
        // Zoom out to zoom level 10, animating with a duration of 2 seconds.
        mMap.animateCamera(CameraUpdateFactory.zoomTo(5), 2000, null);

        drawRoute();
    }

    private void drawRoute() {
        //specify latitude and longitude of both source and destination
        Polyline line = mMap.addPolyline(new PolylineOptions()
                .add(new LatLng(22.2587, 71.1924), new LatLng(34.1491, 76.826))
                .width(5)
                .color(Color.RED));
    }


}
