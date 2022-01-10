package com.example.raund;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class ResheduleJadwalActivity extends AppCompatActivity implements OnMapReadyCallback {

    private Button reschedule_btn;
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reshedule_jadwal);

        //Button
        reschedule_btn = findViewById(R.id.nextreceiver_btn);
        reschedule_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DetailReceiverActivity();
            }
        });

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }
    public void DetailReceiverActivity(){
        Intent intent = new Intent(this, DetailReceiverActivity.class);
        startActivity(intent);
    }
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-0.9152784, 100.455757);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Unand"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney,15));
    }
}