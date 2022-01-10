package com.example.raund;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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

public class DataTravelActivity extends AppCompatActivity implements OnMapReadyCallback  {

    private Button nextBtn;
    private GoogleMap mMap;
    EditText travelCurLoc;
    EditText latLoc;
    EditText longLoc;
    EditText travelDestination;
    EditText latDes;
    EditText longDes;
    EditText tglBerangkat;
    Button btnGetLoc;
    Button button;
    SearchView svLoc;
    FusedLocationProviderClient fusedLocationProviderClient;
//    MarkerOptions place_1, place_2;
//    Polyline curPolyLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_travel);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        tglBerangkat = findViewById(R.id.tglBerangkat);
        travelCurLoc = findViewById(R.id.travelCurLoc);
        latLoc = findViewById(R.id.latLoc);
        longLoc = findViewById(R.id.longLoc);
        latDes = findViewById(R.id.latDes);
        longDes = findViewById(R.id.longDes);
        travelDestination = findViewById(R.id.travelDestination);


        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        //get location dari searchview
        svLoc = findViewById(R.id.svLoc);

        svLoc.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                mMap.clear();
                String location = svLoc.getQuery().toString();


                List<Address> addressList = null;
                if(addressList != null || !location.equals("") ){
                    Geocoder geocoder = new Geocoder(DataTravelActivity.this);
                    try {
                        addressList = geocoder.getFromLocationName(location, 1);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    travelDestination.setText(Html.fromHtml("<font></font>") + addressList.get(0).getSubAdminArea());

                    Address address = addressList.get(0);
                    latDes.setText(String.valueOf(address.getLatitude()));
                    longDes.setText(String.valueOf(address.getLongitude()));
                    String slatloc = latLoc.getText().toString();
                    String slongloc = longLoc.getText().toString();

                    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                    mMap.addMarker(new MarkerOptions().position(latLng).title("Lokasi tujuan kamu"));
                    mMap.addMarker(new MarkerOptions().position(new LatLng(Double.valueOf(slatloc),Double.valueOf(slongloc))).title("Lokasi kamu"));

                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,8));

//                    place_1 = new MarkerOptions().position(new LatLng(Double.valueOf(slatLoc),Double.valueOf(slongLoc))).title("Location 1");
//                    place_2 = new MarkerOptions().position(latLng).title("Location 2");
//                    String url = getUrl(place_1.getPosition(), place_2.getPosition(), "driving");
//                    new FetchURL(DataTravelActivity.this).execute(url,"driving");
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });


        //Initialize fusedLocationProviderClient
        btnGetLoc= findViewById(R.id.getLocBtn);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        btnGetLoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Check permission
                if (ActivityCompat.checkSelfPermission(DataTravelActivity.this
                        , Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    //When Permission Granted
                    getLocation();
                } else {
                    //When Permission Denied
                    ActivityCompat.requestPermissions(DataTravelActivity.this
                            , new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);

                }
            }
        });

        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String sCurLoc = travelCurLoc.getText().toString();
                String sDestination = travelDestination.getText().toString();

                if(sCurLoc.equals("") && sDestination.equals("")){
                    Toast.makeText(getApplicationContext(), "Isi lokasi dulu...", Toast.LENGTH_SHORT).show();
                }else{
                    DisplayTrack(sCurLoc, sDestination);
                }

            }
        });

        nextBtn = findViewById(R.id.nextBtn);
        nextBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TravelListActivity();
            }
        });

    }

//    private String getUrl(LatLng position, LatLng position1, String directionMode) {
//        String str_origin = "origin=" + position.latitude + "," + position.longitude;
//        String str_dest = "destination=" + position1.latitude + "," + position1.longitude;
//        String mode = "mode=" + directionMode;
//        String parameters = str_origin + "&" + str_dest + "&" + mode;
//        String output = "json";
//        String url = "https://maps.googleapis.com/maps/api/directions/" + output + "?" + parameters + "&key=" + getString(R.string.google_maps_key);
//        return url;
//    }

    // get location dari button
    private void getLocation() {
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
                        Geocoder geocoder = new Geocoder(DataTravelActivity.this,
                                Locale.getDefault());
                        List<Address> addresses = geocoder.getFromLocation(
                                location.getLatitude(), location.getLongitude(), 1
                        );
                        //Set Address
                        travelCurLoc.setText(Html.fromHtml("<font></font>"
                                + addresses.get(0).getSubAdminArea()));
                        longLoc.setText(Html.fromHtml("<font></font>"
                                + addresses.get(0).getLongitude()));
                        latLoc.setText(Html.fromHtml("<font></font>"
                                + addresses.get(0).getLatitude()));


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    private void gotoLocation(double latitude, double longitude) {
        LatLng latLng = new LatLng(latitude, longitude);

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(latLng, 18);
        mMap.moveCamera(cameraUpdate);
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        mMap.addMarker(new MarkerOptions().position(latLng).title("Lokasi kamu baby"));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,18));
    }



    private void DisplayTrack(String sCurLoc, String sDestination) {
        try {
            Uri uri = Uri.parse("https://www.google.com/maps/dir/?api=1&origin=" + sCurLoc + "&destination="
                    + sDestination);

            Intent intent = new Intent(Intent.ACTION_VIEW,uri);

            intent.setPackage("com.google.android.apps.maps");

            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);



        }catch (ActivityNotFoundException e){
            Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.google.android.apps.maps");
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            Toast.makeText(getApplicationContext(), sCurLoc
                    , Toast.LENGTH_SHORT).show();
        }
    }

    public void TravelListActivity(){

        //get
        String currentLoc = travelCurLoc.getText().toString();
        String currentLat = latLoc.getText().toString();
        String currentLong = longLoc.getText().toString();
        String destLoc = travelDestination.getText().toString();
        String destLat = latDes.getText().toString();
        String destLong = longDes.getText().toString();
        String tglGo = tglBerangkat.getText().toString();

        SharedPreferences preferences = getSharedPreferences(
                "com.example.raund.PREFS", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();

        editor.putString("CURRENT_LOC", currentLoc);
        editor.putString("CURRENT_LAT", currentLat);
        editor.putString("CURRENT_LONG", currentLong);
        editor.putString("DEST_LOC", destLoc);
        editor.putString("DEST_LAT", destLat);
        editor.putString("DEST_LONG", destLong);
        editor.putString("TGL_BRNGKT", tglGo);
        editor.apply();

        Intent intent = new Intent(this, TravelListActivity.class);
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


//    @Override
//    public void onTaskDone(Object... values) {
//        if(curPolyLine!=null){
//            curPolyLine.remove();
//            curPolyLine = mMap.addPolyline((PolylineOptions) values[0]);
//        }
//    }
}