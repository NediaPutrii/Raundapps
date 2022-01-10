package com.example.raund;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class DetailSenderActivity extends AppCompatActivity implements OnMapReadyCallback {

    private Button nextsender_btn;
    Button btnLocSender;
    EditText editTxtLocSender;
    EditText editTxtLongSender;
    EditText editTxtLatSender;
    EditText editTxtNamaSender;
    EditText editTxtNoSender;
    CardView TravelChooseCv;
    private GoogleMap mMap;
    FusedLocationProviderClient fusedLocationProviderClient;
//    public double longitude=100.455757;
//    public double latitude=-0.9152784;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_sender);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        nextsender_btn = findViewById(R.id.nextsender_btn);
        nextsender_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DetailReceiverActivity();
            }
        });

        editTxtNamaSender = findViewById(R.id.nama_sender);
        editTxtNoSender = findViewById(R.id.no_sender);
        btnLocSender = findViewById(R.id.btnLocSender);
        editTxtLocSender = findViewById(R.id.editTxtLocSender);
        editTxtLatSender = findViewById(R.id.editTxtLatSender);
        editTxtLongSender = findViewById(R.id.editTxtLongSender);
        //Initialize fusedLocationProviderClient
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        btnLocSender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Check permission
                if (ActivityCompat.checkSelfPermission(DetailSenderActivity.this
                        , Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    //When Permission Granted
                    getLocation();
                } else {
                    //When Permission Denied
                    ActivityCompat.requestPermissions(DetailSenderActivity.this
                            , new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);

                }
            }
        });


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    public void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                //Initialize Loc
                Location location = task.getResult();
                gotoLocation(location.getLatitude(), location.getLongitude());
                if (location != null) {

                    try {
                        Geocoder geocoder = new Geocoder(DetailSenderActivity.this,
                                Locale.getDefault());
                        List<Address> addresses = geocoder.getFromLocation(
                                location.getLatitude(), location.getLongitude(), 1
                        );
                        //Set Address
                        editTxtLocSender.setText(Html.fromHtml("<font></font>"
                                + addresses.get(0).getSubAdminArea()));
                        editTxtLongSender.setText(Html.fromHtml("<font></font>"
                                + addresses.get(0).getLongitude()));
                        editTxtLatSender.setText(Html.fromHtml("<font></font>"
                                + addresses.get(0).getLatitude()));


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

    }

    private void gotoLocation (double latitude, double longitude){
        LatLng latLng = new LatLng(latitude, longitude);

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 18);
        mMap.moveCamera(cameraUpdate);
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.addMarker(new MarkerOptions().position(latLng).title("Lokasi kamu baby"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,18));
    }

    public void DetailReceiverActivity(){
        //get
        String locSender = editTxtLocSender.getText().toString();
        String longSender = editTxtLongSender.getText().toString();
        String latSender = editTxtLatSender.getText().toString();
        String snamaSender = editTxtNamaSender.getText().toString();
        String snoSender = editTxtNoSender.getText().toString();

        SharedPreferences preferences = getSharedPreferences(
                "com.example.raund.PREFS", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("LOC_SENDER", locSender);
        editor.putString("LONG_SENDER", longSender);
        editor.putString("LAT_SENDER", latSender);
        editor.putString("NAMA_SENDER", snamaSender);
        editor.putString("NO_SENDER", snoSender);
        editor.apply();

        Intent intent = new Intent(this, DetailReceiverActivity.class);
        startActivity(intent);
    }

    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

    }

    @Override
    public boolean onSupportNavigateUp(){
        onBackPressed();
        return true;
    }

    @Override
    public void onBackPressed(){
        super.onBackPressed();
    }


}